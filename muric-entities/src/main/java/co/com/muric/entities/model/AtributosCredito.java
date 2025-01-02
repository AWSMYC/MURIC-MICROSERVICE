package co.com.muric.entities.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Column(name = "TIPO_IDENTIFICACION")
    private MuricEnums tipoIdentificacion;

    @Size(max = 50)
    @Column(name = "NUMERO_INDENTIFICACION", length = 50)
    private String numeroIdentificacion;

    @Column(name = "CLAVE_ATRIBUTO")
    @NotNull(message = "El tipo de reporte no puede ser nulo")
    @Min(value = 1, message = "El tipo de reporte debe ser al menos 1")
    @Max(value = 100, message = "El tipo de reporte no puede exceder 100")
    @Size(max = 200)
    private String claveAtributo;

    @Column(name = "VALOR_ATRIBUTO", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double valorAtributo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimientos_cartera_id")
    private MovimientosCartera movimientosCartera;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidad_id")
    private Entidad entidad;

}
