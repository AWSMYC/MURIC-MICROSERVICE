package co.com.muric.entities.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ATRIBUTOS_CREDITO")
@EntityListeners(AuditingEntityListener.class)
public class AtributosCredito {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "IDENTIFICACION_CREDITO_ENTIDAD", length = 50)
    private String identificacionCreditoEntidad;

    @Size(max = 50)
    @Column(name = "TIPO_IDENTIFICACION", length = 50)
    private String tipoIdentificacion;

    @Size(max = 50)
    @Column(name = "NUMERO_INDENTIFICACION", length = 50)
    private String numeroIdentificacion;

    @Size(max = 50)
    @Column(name = "CLAVE_ATRIBUTO", length = 50)
    private String claveAtributo;

    @Column(name = "VALOR_ATRIBUTO", nullable = false, precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double valorAtributo;

}
