package co.com.muric.entities.model.excel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class UnifiedCreditInformation {
    private String identificacionCreditoEntidad;
    private Integer tipoIdentificacion;
    private String numeroIdentificacion;

    // Campos de InformacionCredito
    private Integer modalidad;
    private Integer codigoProducto;
    private Integer calidadDeudor;
    private Integer fechaDesembolso;
    private Integer fechaVencimiento;
    private Float valorDesembolsado;
    private Integer frecuenciaPagoCapital;
    private Integer frecuenciaPagoIntereses;
    private String tipoTasa;
    private Integer tipoGarantia;
    private String moneda;
    private String estadoRegistro;

    // Campos de AtributoCreditoDeuda
    private Integer claveAtributo;
    private String valorAtributo;

    // Campos de MovimientoCartera
    private Integer fechaCorte;
    private String calificacionCredito;
    private Integer estado;
    private Integer periodoGracia;
    private Integer diasMora;
    private Float tasaInteres;
    private Float spreadTasaInteres;
    private Float saldoCapital;
    private Float saldoIntereses;
    private Float saldoOtros;
    private Integer modeloProvisiones;
    private Float provisionProciclica;
    private Float provisionContraciclica;
    private Float provisionAdicionalPoliticaEntidad;
    private Float provisionOtros;
    private Float cuotaEsperadaCapital;
    private Float cuotaEsperadaIntereses;
    private Float cuotaRecibidaCapital;
    private Float cuotaRecibidaIntereses;
    private Float valorGarantia;
    private Integer fechaGarantia;
    private Float probabilidadIncumplimientoCredito;
    private Float perdidaDadoIncumplimiento;
}
