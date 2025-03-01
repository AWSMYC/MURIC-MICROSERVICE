package co.com.muric.entities.model.excel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class AtributoCreditoDeuda {
    private String identificacionCreditoEntidad;
    private Integer tipoIdentificacion;
    private String numeroIdentificacion;
    private Integer claveAtributo;
    private String valorAtributo;
}