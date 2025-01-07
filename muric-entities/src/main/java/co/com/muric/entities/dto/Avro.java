package co.com.muric.entities.dto;

import co.com.muric.entities.model.MuricField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Avro {
    private String type;
    private String name;
    private Integer tipoEntidad;
    private Integer codigoEntidad;
    private MuricField.MuricFieldNameTypeSubType fechaCorte;
    private MuricField.MuricFieldNameTypeSubType fechaGeneracion;
    private String comentarios;
    private String firma;
    private String palabraClave;
    private List<Object> creditoFields;
    private List<Object> movimientoFields;
    private List<Object> demograficoFields;

}
