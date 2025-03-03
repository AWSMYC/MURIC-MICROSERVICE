package co.com.muric.usecase.implement;

import co.com.muric.entities.model.excel.AtributoCreditoDeuda;
import co.com.muric.entities.model.excel.InformacionCredito;
import co.com.muric.entities.model.excel.MovimientoCartera;
import co.com.muric.entities.model.excel.UnifiedCreditInformation;
import co.com.muric.entities.util.StaticVariables;
import co.com.muric.usecase.util.FileDataSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class ProcessData {

    private static final Logger logger = LogManager.getLogger(ProcessData.class);

    public static Object generateUnifiedCreditInformation(String source) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try  {
            CompletableFuture<List<InformacionCredito>> futureInformacionCredito = fetchAsync(() -> FileDataSource.readSheetInformacionCredito(source), executor);
            CompletableFuture<List<AtributoCreditoDeuda>> futureAtributoCreditoDeuda = fetchAsync(() -> FileDataSource.readSheetAtributoCreditoDeuda(source), executor);
            CompletableFuture<List<MovimientoCartera>> futureMovimientoCartera = fetchAsync(() -> FileDataSource.readSheetMovimientoCartera(source), executor);
            CompletableFuture.allOf(futureInformacionCredito, futureAtributoCreditoDeuda, futureMovimientoCartera).join();
            Map<String, Object> a = generarReporte(futureInformacionCredito.get(), futureAtributoCreditoDeuda.get(), futureMovimientoCartera.get());
            ObjectMapper objectMapper = new ObjectMapper();
            Object at = objectMapper.writeValueAsString(a);
            return at;
        } catch (Exception e) {
            logger.error(MessageFormat.format(StaticVariables.PROCESS_FILE_ERROR, e));
            throw new IOException(MessageFormat.format(StaticVariables.PROCESS_FILE_ERROR, e));
        }
    }


        private static Map<String, Object> generarReporte(
                List<InformacionCredito> informacionCreditoList,
                List<AtributoCreditoDeuda> atributoCreditoDeudaList,
                List<MovimientoCartera> movimientoCarteraList) {

            ExecutorService executor = Executors.newFixedThreadPool(3);

            CompletableFuture<List<Map<String, Object>>> creditosFuture = CompletableFuture.supplyAsync(() ->
                    informacionCreditoList.stream().map(ProcessData::mapearCredito).collect(Collectors.toList()), executor);

            CompletableFuture<List<Map<String, Object>>> movimientosFuture = CompletableFuture.supplyAsync(() ->
                    movimientoCarteraList.stream().map(ProcessData::mapearMovimiento).collect(Collectors.toList()), executor);

            CompletableFuture<List<Map<String, Object>>> demograficosFuture = CompletableFuture.supplyAsync(() ->
                    atributoCreditoDeudaList.stream().map(ProcessData::mapearDemografico).collect(Collectors.toList()), executor);

            CompletableFuture.allOf(creditosFuture, movimientosFuture, demograficosFuture).join();

            executor.shutdown();

            try {
                return Map.of(
                        "tipo_entidad", 1,
                        "codigo_entidad", 100,
                        "fecha_corte", 20210101,
                        "fecha_generacion", 20210301,
                        "comentarios", "Registro de crédito",
                        "firma", "FirmaDigitalEjemplo",
                        "palabra_clave", "Confidencial",
                        "creditos", creditosFuture.get(),
                        "movimientos", movimientosFuture.get(),
                        "demograficos", demograficosFuture.get()
                );
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Error generando el reporte", e);
            }
        }

        private static Map<String, Object> mapearCredito(InformacionCredito ic) {
            Map<String, Object> map = new HashMap<>();
            map.put("identificacion_credito_entidad", ic.getIdentificacionCreditoEntidad());
            map.put("tipo_identificacion", "_" + ic.getTipoIdentificacion());
            map.put("numero_identificacion", ic.getNumeroIdentificacion());
            map.put("modalidad", "_" + ic.getModalidad());
            map.put("codigo_producto", "_" + ic.getCodigoProducto());
            map.put("calidad_deudor", "_" + ic.getCalidadDeudor());
            map.put("fecha_desembolso", ic.getFechaDesembolso());
            map.put("fecha_vencimiento", ic.getFechaVencimiento());
            map.put("valor_desembolsado", ic.getValorDesembolsado());
            map.put("frecuencia_pago_capital", "_" + ic.getFrecuenciaPagoCapital());
            map.put("frecuencia_pago_intereses", "_" + ic.getFrecuenciaPagoIntereses());
            map.put("tipo_tasa", ic.getTipoTasa());
            map.put("tipo_garantia", "_" + ic.getTipoGarantia());
            map.put("moneda", ic.getMoneda());
            map.put("estado_registro", ic.getEstadoRegistro());
            return map;
        }

        private static Map<String, Object> mapearMovimiento(MovimientoCartera mc) {
            Map<String, Object> map = new HashMap<>();
            map.put("identificacion_credito_entidad", mc.getIdentificacionCreditoEntidad());
            map.put("tipo_identificacion", "_" + mc.getTipoIdentificacion());
            map.put("numero_identificacion", mc.getNumeroIdentificacion());
            map.put("fecha_corte", mc.getFechaCorte());
            map.put("calificacion_credito", mc.getCalificacionCredito());
            map.put("estado", "_" + mc.getEstado());
            map.put("periodo_gracia", "_" + mc.getPeriodoGracia());
            map.put("dias_mora", mc.getDiasMora());
            map.put("tasa_interes", mc.getTasaInteres());
            map.put("spread_tasa_interes", mc.getSpreadTasaInteres());
            map.put("saldo_capital", mc.getSaldoCapital());
            map.put("saldo_intereses", mc.getSaldoIntereses());
            map.put("saldo_otros", mc.getSaldoOtros());
            map.put("modelo_provisiones", "_" + mc.getModeloProvisiones());
            map.put("provision_prociclica", mc.getProvisionProciclica());
            map.put("provision_contraciclica", mc.getProvisionContraciclica());
            map.put("provision_adicional_politica_entidad", mc.getProvisionAdicionalPoliticaEntidad());
            map.put("provision_otros", mc.getProvisionOtros());
            map.put("cuota_esperada_capital", mc.getCuotaEsperadaCapital());
            map.put("cuota_esperada_intereses", mc.getCuotaEsperadaIntereses());
            map.put("cuota_recibida_capital", mc.getCuotaRecibidaCapital());
            map.put("cuota_recibida_intereses", mc.getCuotaRecibidaIntereses());
            map.put("fecha_garantia", mc.getFechaGarantia());
            map.put("valor_garantia", mc.getValorGarantia());
            map.put("probabilidad_incumplimiento_credito", mc.getProbabilidadIncumplimientoCredito());
            map.put("perdida_dado_incumplimiento", mc.getPerdidaDadoIncumplimiento());
            map.put("estado_registro", "N000001");
            return map;
        }

        private static Map<String, Object> mapearDemografico(AtributoCreditoDeuda ad) {
            return Map.of(
                    "identificacion_credito_entidad", ad.getIdentificacionCreditoEntidad(),
                    "tipo_identificacion", "_" + ad.getTipoIdentificacion(),
                    "numero_identificacion", ad.getNumeroIdentificacion(),
                    "clave_atributo", ad.getClaveAtributo(),
                    "valor_atributo", ad.getValorAtributo()
            );
        }


    public static <T> CompletableFuture<T> fetchAsync(DataSupplier<T> supplier, ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return supplier.get();
            } catch (IOException e) {
                throw new RuntimeException(MessageFormat.format(StaticVariables.PROCESS_FILE_ERROR, e));
            }
        }, executor);
    }

    @FunctionalInterface
    private interface DataSupplier<T> {
        T get() throws IOException;
    }


}
