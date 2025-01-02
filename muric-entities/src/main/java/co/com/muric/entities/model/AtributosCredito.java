package co.com.muric.entities.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @Size(max = 50)
    @Column(name = "VALOR_ATRIBUTO", length = 50)
    private String valorAtributo;

}
