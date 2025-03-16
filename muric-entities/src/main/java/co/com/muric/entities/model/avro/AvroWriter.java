package co.com.muric.entities.model.avro;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;

public class AvroWriter {
    public static void main(String[] args) {
        // Crear un objeto RUC con datos de ejemplo
        RUC ruc = RUC.newBuilder()
                .setTipoEntidad(1)
                .setCodigoEntidad(12345)
                .setFechaCorte(LocalDate.ofEpochDay(19523))  // Fecha en formato `int` (días desde epoch)
                .setFechaGeneracion(LocalDate.ofEpochDay(19523))
                .setComentarios("Datos de prueba")
                .setFirma("FirmaDigitalEjemplo")
                .setPalabraClave("clave123")
                .setCreditos(Collections.singletonList(
                        credito.newBuilder()
                                .setIdentificacionCreditoEntidad("CRD-001")
                                .setTipoIdentificacion(tipo_ident._11)
                                .setNumeroIdentificacion("123456789")
                                .setModalidad(modalidad_c._1)
                                .setCodigoProducto(productos_id._101)
                                .setCalidadDeudor(calidad_d._0)
                                .setFechaDesembolso(LocalDate.ofEpochDay(19523))
                                .setFechaVencimiento(LocalDate.ofEpochDay(19530))
                                .setValorDesembolsado(50000.75f)
                                .setFrecuenciaPagoCapital(frecuencia_p_c._0)
                                .setFrecuenciaPagoIntereses(frecuencia_p_i._0)
                                .setTipoTasa(tipo_t.ANA)
                                .setTipoGarantia(tipo_g._0)
                                .setMoneda(moneda.COP)
                                .setEstadoRegistro(estado_reg.N000001)
                                .build()
                ))
                .setMovimientos(Collections.singletonList(
                        movimiento.newBuilder()
                                .setIdentificacionCreditoEntidad("CRD-001")
                                .setTipoIdentificacion(tipo_ident._11)
                                .setNumeroIdentificacion("123456789")
                                .setFechaCorte(LocalDate.ofEpochDay(19523))
                                .setCalificacionCredito(calificacion_c.A)
                                .setEstado(estado._0)
                                .setPeriodoGracia(periodo_g._0)
                                .setDiasMora(0)
                                .setTasaInteres(3.5f)
                                .setSpreadTasaInteres(0.5f)
                                .setSaldoCapital(45000.00f)
                                .setSaldoIntereses(500.00f)
                                .setSaldoOtros(100.00f)
                                .setModeloProvisiones(modelo._0)
                                .setProvisionProciclica(50.0f)
                                .setProvisionContraciclica(25.0f)
                                .setProvisionAdicionalPoliticaEntidad(10.0f)
                                .setProvisionOtros(5.0f)
                                .setProvisionTotal(90.0f)
                                .setCuotaEsperadaCapital(1000.0f)
                                .setCuotaEsperadaIntereses(200.0f)
                                .setCuotaRecibidaCapital(800.0f)
                                .setCuotaRecibidaIntereses(180.0f)
                                .setFechaGarantia(LocalDate.ofEpochDay(19530))
                                .setValorGarantia(10000.0f)
                                .setProbabilidadIncumplimientoCredito(0.02f)
                                .setPerdidaDadoIncumplimiento(0.5f)
                                .setEstadoRegistro(estado_reg.N000001)
                                .build()
                ))
                .setDemograficos(Collections.singletonList(
                        Demografico.newBuilder()
                                .setIdentificacionCreditoEntidad("CRD-001")
                                .setTipoIdentificacion(tipo_ident._11)
                                .setNumeroIdentificacion("123456789")
                                .setClaveAtributo(1)
                                .setValorAtributo("EjemploAtributo")
                                .build()
                ))
                .build();

        String avroDirPath = "/Users/kristianhdez/Desktop/MURIC-MICROSERVICE/muric-entities/src/main/resources/avro";

        // Crear el directorio si no existe
        File avroDir = new File(avroDirPath);
        if (!avroDir.exists()) {
            avroDir.mkdirs();
        }

        // Definir la ruta del archivo Avro
        File avroFile = new File(avroDir, "ruc_data.avro");

        // Escribir en archivo Avro
        DatumWriter<RUC> datumWriter = new SpecificDatumWriter<>(RUC.class);
        try (DataFileWriter<RUC> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(ruc.getSchema(), avroFile);
            dataFileWriter.append(ruc);
            System.out.println("Archivo Avro creado exitosamente en: " + avroFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
