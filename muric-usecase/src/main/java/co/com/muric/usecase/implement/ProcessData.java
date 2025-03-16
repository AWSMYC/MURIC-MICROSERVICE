package co.com.muric.usecase.implement;

import co.com.muric.entities.model.avro.*;
import co.com.muric.entities.model.excel.AtributoCreditoDeuda;
import co.com.muric.entities.model.excel.InformacionCredito;
import co.com.muric.entities.model.excel.MovimientoCartera;
import co.com.muric.entities.util.StaticVariables;
import co.com.muric.usecase.util.FileDataSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class ProcessData {

    private static final Logger logger = LogManager.getLogger(ProcessData.class);

    public static Object generateAvroFormat(String source) throws IOException {
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

    private static Map<String, Object> generarReporte(List<InformacionCredito> informacionCreditoList, List<AtributoCreditoDeuda> atributoCreditoDeudaList, List<MovimientoCartera> movimientoCarteraList) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletableFuture<List<Map<String, Object>>> creditosFuture = CompletableFuture.supplyAsync(() -> informacionCreditoList.stream().map(ProcessData::mapearCredito).collect(Collectors.toList()), executor);
        CompletableFuture<List<Map<String, Object>>> movimientosFuture = CompletableFuture.supplyAsync(() -> movimientoCarteraList.stream().map(ProcessData::mapearMovimiento).collect(Collectors.toList()), executor);
        CompletableFuture<List<Map<String, Object>>> demograficosFuture = CompletableFuture.supplyAsync(() -> atributoCreditoDeudaList.stream().map(ProcessData::mapearDemografico).collect(Collectors.toList()), executor);
        CompletableFuture.allOf(creditosFuture, movimientosFuture, demograficosFuture).join();
        executor.shutdown();
        generarRUC(Map.of(
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
        ));
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


    private static RUC generarRUC(Map<String, Object> reporteData) {
        int tipoEntidad = (int) reporteData.get("tipo_entidad");
        int codigoEntidad = (int) reporteData.get("codigo_entidad");
        int fechaCorte = (int) reporteData.get("fecha_corte");
        int fechaGeneracion = (int) reporteData.get("fecha_generacion");
        String comentarios = (String) reporteData.get("comentarios");
        String firma = (String) reporteData.get("firma");
        String palabraClave = (String) reporteData.get("palabra_clave");

        LocalDate fechaCorteLocal = LocalDate.ofEpochDay(fechaCorte);
        LocalDate fechaGeneracionLocal = LocalDate.ofEpochDay(fechaGeneracion);


        List<Map<String, Object>> creditos = (List<Map<String, Object>>) reporteData.get("creditos");
        List<Map<String, Object>> movimientos = (List<Map<String, Object>>) reporteData.get("movimientos");
        List<Map<String, Object>> demograficos = (List<Map<String, Object>>) reporteData.get("demograficos");

        List<credito> creditosList = creditos.stream().map(creditoData -> {

            return credito.newBuilder()
                    .setIdentificacionCreditoEntidad((String) creditoData.get("identificacion_credito_entidad"))
                    .setTipoIdentificacion(convertirTipoGenerico(
                            (String) creditoData.get("tipo_identificacion"), co.com.muric.entities.model.avro.tipo_ident.class)
                    )
                    .setNumeroIdentificacion((String) creditoData.get("numero_identificacion"))
                    .setModalidad(convertirTipoGenerico(
                            (String) creditoData.get("modalidad"), co.com.muric.entities.model.avro.modalidad_c.class)
                    )
                    .setCodigoProducto(convertirTipoGenerico(
                            (String) creditoData.get("codigo_producto"), co.com.muric.entities.model.avro.productos_id.class)
                    )
                    .setCalidadDeudor(convertirTipoGenerico(
                            (String) creditoData.get("calidad_deudor"), co.com.muric.entities.model.avro.calidad_d.class)
                    )

                    .setFechaDesembolso(LocalDate.ofEpochDay(Integer.parseInt(creditoData.get("fecha_desembolso").toString())))
                    .setFechaVencimiento(LocalDate.ofEpochDay(Integer.parseInt(creditoData.get("fecha_vencimiento").toString())))
                    .setValorDesembolsado((float) creditoData.get("valor_desembolsado"))
                    .build();
        }).collect(Collectors.toList());

        List<movimiento> movimientosList = movimientos.stream().map(movimientoData -> {
            return movimiento.newBuilder()
                    .setIdentificacionCreditoEntidad((String) movimientoData.get("identificacion_credito_entidad"))
                    .setTipoIdentificacion(convertirTipoGenerico(
                            (String) movimientoData.get("tipo_identificacion"), co.com.muric.entities.model.avro.tipo_ident.class)
                    )
                    .setNumeroIdentificacion((String) movimientoData.get("numero_identificacion"))
                    .setFechaCorte(fechaCorteLocal)
                    .setCalificacionCredito(convertirTipoGenerico(
                            (String) movimientoData.get("calificacion_credito"), co.com.muric.entities.model.avro.calificacion_c.class)
                    )
                    .setEstado(convertirTipoGenerico(
                            (String) movimientoData.get("estado"), co.com.muric.entities.model.avro.estado.class)
                    )
                    .setPeriodoGracia(convertirTipoGenerico(
                            (String) movimientoData.get("periodo_gracia"), co.com.muric.entities.model.avro.periodo_g.class)
                    )
                    .setDiasMora((int) movimientoData.get("dias_mora"))
                    .setTasaInteres((float) movimientoData.get("tasa_interes"))
                    .setSaldoCapital((float) movimientoData.get("saldo_capital"))
                    .setSaldoIntereses((float) movimientoData.get("saldo_intereses"))
                    .build();
        }).collect(Collectors.toList());

        List<Demografico> demograficosList = demograficos.stream().map(demograficoData -> {
            return Demografico.newBuilder()
                    .setIdentificacionCreditoEntidad((String) demograficoData.get("identificacion_credito_entidad"))
                    .setTipoIdentificacion(convertirTipoGenerico(
                            (String) demograficoData.get("tipo_identificacion"), co.com.muric.entities.model.avro.tipo_ident.class)
                    )
                    .setNumeroIdentificacion((String) demograficoData.get("numero_identificacion"))
                    .setClaveAtributo((int) demograficoData.get("clave_atributo"))
                    .setValorAtributo((String) demograficoData.get("valor_atributo"))
                    .build();
        }).collect(Collectors.toList());

        String avroDirPath = "/Users/kristianhdez/Desktop/MURIC-MICROSERVICE/muric-entities/src/main/resources/avro";

        File avroDir = new File(avroDirPath);
        if (!avroDir.exists()) {
            avroDir.mkdirs();
        }

        File avroFile = new File(avroDir, "ruc_data.avro");
        RUC ruc = RUC.newBuilder()
                .setTipoEntidad(tipoEntidad)
                .setCodigoEntidad(codigoEntidad)
                .setFechaCorte(fechaCorteLocal)
                .setFechaGeneracion(fechaGeneracionLocal)
                .setComentarios(comentarios)
                .setFirma(firma)
                .setPalabraClave(palabraClave)
                .setCreditos(creditosList)
                .setMovimientos(movimientosList)
                .setDemograficos(demograficosList)
                .build();
        DatumWriter<RUC> datumWriter = new SpecificDatumWriter<>(RUC.class);
        try (DataFileWriter<RUC> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(ruc.getSchema(), avroFile);
            dataFileWriter.append(ruc);
            System.out.println("Archivo Avro creado exitosamente en: " + avroFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ruc;
    }

    private static <T extends Enum<T>> T convertirTipoGenerico(String valor, Class<T> enumClass) {
        try {
            String valorConPrefijo = "_" + valor;
            return Enum.valueOf(enumClass, valorConPrefijo);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor inválido para la enumeración " + enumClass.getSimpleName() + ": " + valor, e);
        }
    }


}
