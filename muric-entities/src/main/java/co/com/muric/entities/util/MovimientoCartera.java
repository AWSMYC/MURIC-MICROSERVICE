package co.com.muric.entities.util;

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
    private Integer modalidad;
    private Integer codigoProducto;
    private Integer calidadDeudor;
    private Integer fechaDesembolso;
    private Integer fechaVencimiento;
    private Double valorDesembolsado;
    private Integer frecuenciaPagoCapital;
    private Integer frecuenciaPagoIntereses;
    private String tipoTasa;
    private Integer tipoGarantia;
    private String moneda;
    private String estadoRegistro;
}