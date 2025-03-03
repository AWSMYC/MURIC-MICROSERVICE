package co.com.muric.usecase.implement;

import co.com.muric.entities.dto.Avro;
import co.com.muric.entities.model.database.MuricField;
import co.com.muric.entities.model.excel.AtributoCreditoDeuda;
import co.com.muric.entities.model.excel.InformacionCredito;
import co.com.muric.entities.model.excel.MovimientoCartera;
import co.com.muric.entities.model.excel.UnifiedCreditInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

@Service
public class ProcessFile {

    private static final Logger logger = LogManager.getLogger(ProcessFile.class);

    public static Avro generateAvroFromDataBase() {
        //muricRepository.findData();
        //superintendenciaAPI.sendAvro(Avro.builder().build());
        return Avro.builder().build();
    }

    public static Avro avroMapper(List<InformacionCredito> informacionCreditoList,
                                   List<AtributoCreditoDeuda> atributoCreditoDeudaList,
                                   List<MovimientoCartera> movimientoCarteraList) {
        List<Object> creditoFields = new ArrayList<>();
        List<Object> movimientoFields = new ArrayList<>();
        List<Object> demograficoFields = new ArrayList<>();
        return Avro.builder()
                .type(null)
                .name(null)
                .tipoEntidad(null)
                .codigoEntidad(null)
                .fechaCorte(MuricField.MuricFieldNameTypeSubType.builder()
                        .name(null)
                        .type(MuricField.MuricFieldNameTypeSubType.MuricFieldLogicalType.builder().build())
                        .build())
                .fechaGeneracion(MuricField.MuricFieldNameTypeSubType.builder()
                        .name(null)
                        .type(MuricField.MuricFieldNameTypeSubType.MuricFieldLogicalType.builder().build())
                        .build())
                .comentarios(null)
                .firma(null)
                .palabraClave(null)
                .creditoFields(creditoFields)
                .movimientoFields(movimientoFields)
                .demograficoFields(demograficoFields)
                .build();
    }

    public static List<UnifiedCreditInformation> agruparCreditos(
            List<InformacionCredito> informacionCreditoList,
            List<AtributoCreditoDeuda> atributoCreditoDeudaList,
            List<MovimientoCartera> movimientoCarteraList) {

        Map<String, UnifiedCreditInformation> creditosMap = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(3); // Pool de 3 hilos

        Function<Object, String> generarClave = obj -> {
            if (obj instanceof InformacionCredito) {
                InformacionCredito ic = (InformacionCredito) obj;
                return ic.getIdentificacionCreditoEntidad() + "-" + ic.getTipoIdentificacion() + "-" + ic.getNumeroIdentificacion();
            } else if (obj instanceof AtributoCreditoDeuda) {
                AtributoCreditoDeuda ad = (AtributoCreditoDeuda) obj;
                return ad.getIdentificacionCreditoEntidad() + "-" + ad.getTipoIdentificacion() + "-" + ad.getNumeroIdentificacion();
            } else if (obj instanceof MovimientoCartera) {
                MovimientoCartera mc = (MovimientoCartera) obj;
                return mc.getIdentificacionCreditoEntidad() + "-" + mc.getTipoIdentificacion() + "-" + mc.getNumeroIdentificacion();
            }
            return "";
        };

        // Ejecutar los tres for en paralelo con CompletableFuture
        CompletableFuture<Void> futureInformacionCredito = CompletableFuture.runAsync(() -> {
            for (InformacionCredito ic : informacionCreditoList) {
                String clave = generarClave.apply(ic);
                creditosMap.computeIfAbsent(clave, k -> new UnifiedCreditInformation());
                UnifiedCreditInformation unifiedCreditInformation = creditosMap.get(clave);
                unifiedCreditInformation.setIdentificacionCreditoEntidad(ic.getIdentificacionCreditoEntidad());
                unifiedCreditInformation.setTipoIdentificacion(ic.getTipoIdentificacion());
                unifiedCreditInformation.setNumeroIdentificacion(ic.getNumeroIdentificacion());
                unifiedCreditInformation.setModalidad(ic.getModalidad());
                unifiedCreditInformation.setCodigoProducto(ic.getCodigoProducto());
                unifiedCreditInformation.setCalidadDeudor(ic.getCalidadDeudor());
                unifiedCreditInformation.setFechaDesembolso(ic.getFechaDesembolso());
                unifiedCreditInformation.setFechaVencimiento(ic.getFechaVencimiento());
                unifiedCreditInformation.setValorDesembolsado(ic.getValorDesembolsado());
                unifiedCreditInformation.setFrecuenciaPagoCapital(ic.getFrecuenciaPagoCapital());
                unifiedCreditInformation.setFrecuenciaPagoIntereses(ic.getFrecuenciaPagoIntereses());
                unifiedCreditInformation.setTipoTasa(ic.getTipoTasa());
                unifiedCreditInformation.setTipoGarantia(ic.getTipoGarantia());
                unifiedCreditInformation.setMoneda(ic.getMoneda());
                unifiedCreditInformation.setEstadoRegistro(ic.getEstadoRegistro());
            }
        }, executor);

        CompletableFuture<Void> futureAtributoCreditoDeuda = CompletableFuture.runAsync(() -> {
            for (AtributoCreditoDeuda ad : atributoCreditoDeudaList) {
                String clave = generarClave.apply(ad);
                creditosMap.computeIfAbsent(clave, k -> new UnifiedCreditInformation());
                UnifiedCreditInformation unifiedCreditInformation = creditosMap.get(clave);
                unifiedCreditInformation.setClaveAtributo(ad.getClaveAtributo());
                unifiedCreditInformation.setValorAtributo(ad.getValorAtributo());
            }
        }, executor);

        CompletableFuture<Void> futureMovimientoCartera = CompletableFuture.runAsync(() -> {
            for (MovimientoCartera mc : movimientoCarteraList) {
                String clave = generarClave.apply(mc);
                creditosMap.computeIfAbsent(clave, k -> new UnifiedCreditInformation());
                UnifiedCreditInformation unifiedCreditInformation = creditosMap.get(clave);
                unifiedCreditInformation.setFechaCorte(mc.getFechaCorte());
                unifiedCreditInformation.setCalificacionCredito(mc.getCalificacionCredito());
                unifiedCreditInformation.setEstado(mc.getEstado());
                unifiedCreditInformation.setPeriodoGracia(mc.getPeriodoGracia());
                unifiedCreditInformation.setDiasMora(mc.getDiasMora());
                unifiedCreditInformation.setTasaInteres(mc.getTasaInteres());
                unifiedCreditInformation.setSpreadTasaInteres(mc.getSpreadTasaInteres());
                unifiedCreditInformation.setSaldoCapital(mc.getSaldoCapital());
                unifiedCreditInformation.setSaldoIntereses(mc.getSaldoIntereses());
                unifiedCreditInformation.setSaldoOtros(mc.getSaldoOtros());
                unifiedCreditInformation.setModeloProvisiones(mc.getModeloProvisiones());
                unifiedCreditInformation.setProvisionProciclica(mc.getProvisionProciclica());
                unifiedCreditInformation.setProvisionContraciclica(mc.getProvisionContraciclica());
                unifiedCreditInformation.setProvisionAdicionalPoliticaEntidad(mc.getProvisionAdicionalPoliticaEntidad());
                unifiedCreditInformation.setProvisionOtros(mc.getProvisionOtros());
                unifiedCreditInformation.setCuotaEsperadaCapital(mc.getCuotaEsperadaCapital());
                unifiedCreditInformation.setCuotaEsperadaIntereses(mc.getCuotaEsperadaIntereses());
                unifiedCreditInformation.setCuotaRecibidaCapital(mc.getCuotaRecibidaCapital());
                unifiedCreditInformation.setCuotaRecibidaIntereses(mc.getCuotaRecibidaIntereses());
                unifiedCreditInformation.setValorGarantia(mc.getValorGarantia());
                unifiedCreditInformation.setFechaGarantia(mc.getFechaGarantia());
                unifiedCreditInformation.setProbabilidadIncumplimientoCredito(mc.getProbabilidadIncumplimientoCredito());
                unifiedCreditInformation.setPerdidaDadoIncumplimiento(mc.getPerdidaDadoIncumplimiento());
            }
        }, executor);

        // Esperar que todas las tareas terminen
        CompletableFuture.allOf(futureInformacionCredito, futureAtributoCreditoDeuda, futureMovimientoCartera).join();

        // Apagar el pool de hilos
        executor.shutdown();

        return new ArrayList<>(creditosMap.values());
    }

}
