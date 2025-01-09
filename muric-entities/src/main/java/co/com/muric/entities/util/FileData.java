package co.com.muric.entities.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class FileData {
    private String identificacionCreditoEntidad;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String claveAtributo;
    private String valorAtributo;
    private String tipoEntidad;
    private String codigoEntidad;
    private String nombreEntidad;
    private LocalDate fechaCorte;
    private LocalDate fechaGeneracion;
    private String comentarios;
    private String firma;
    private String palabraClave;
    private String modalidad;
    private String codigoProducto;
    private String calidadDeudor;
    private LocalDate fechaDesembolso;
    private LocalDate fechaVencimiento;
    private Double valorDesembolsado;
    private String frecuenciaPagoCapital;
    private String frecuenciaPagoIntereses;
    private String tipoTasa;
    private String tipoGarantia;
    private String moneda;
    private String estadoRegistro;
    private String calificacionCredito;
    private String estado;
    private Integer periodoGracia;
    private Integer diasMora;
    private Double tasaInteres;
    private Double spreadTasaInteres;
    private Double saldoCapital;
    private Double saldoIntereses;
    private Double saldoOtros;
    private String modeloProvisiones;
    private Double provisionProciclica;
    private Double provisionContraciclica;
    private Double provisionAdicionalPoliticaEntidad;
    private Double provisionOtros;
    private Double cuotaEsperadaCapital;
    private Double cuotaEsperadaIntereses;
    private Double cuotaRecibidaCapital;
    private Double cuotaRecibidaIntereses;
    private Double valorGarantia;
    private LocalDate fechaGarantia;
    private Double probabilidadIncumplimientoCredito;
    private Double perdidaDadoIncumplimiento;
}