package co.com.muric.entities.model.excel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoCartera {
    private String identificacionCreditoEntidad;
    private Integer tipoIdentificacion;
    private String numeroIdentificacion;
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
    private Float provisionTotal;
    private Float cuotaEsperadaCapital;
    private Float cuotaEsperadaIntereses;
    private Float cuotaRecibidaCapital;
    private Float cuotaRecibidaIntereses;
    private Float valorGarantia;
    private Integer fechaGarantia;
    private Float probabilidadIncumplimientoCredito;
    private Float perdidaDadoIncumplimiento;
}