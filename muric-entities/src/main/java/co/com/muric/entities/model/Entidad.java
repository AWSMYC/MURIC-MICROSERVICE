package co.com.muric.entities.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ENTIDAD")
@EntityListeners(AuditingEntityListener.class)
public class Entidad {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIPO_ENTIDAD", nullable = false)
    @NotNull(message = "El tipo de entidad no puede ser nulo")
    @Min(value = 1, message = "El tipo de entidad debe ser al menos 1")
    @Max(value = 100, message = "El tipo de entidad no puede exceder 100")
    @Size(max = 200)
    private Integer tipoEntidad;

    @Column(name = "CODIGO_ENTIDAD", nullable = false)
    @NotNull(message = "El codigo de entidad no puede ser nulo")
    @Min(value = 1, message = "El codigo de entidad debe ser al menos 1")
    @Max(value = 100, message = "El codigo de entidad no puede exceder 100")
    @Size(max = 200)
    private String codigoEntidad;

    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE_ENTIDAD", nullable = false, length = 200)
    private String nombreEntidad;

    @Column(name = "TIPO_REPORTE", nullable = false)
    @NotNull(message = "El tipo de reporte no puede ser nulo")
    @Min(value = 1, message = "El tipo de reporte debe ser al menos 1")
    @Max(value = 100, message = "El tipo de reporte no puede exceder 100")
    @Size(max = 200)
    @Size(max = 200)
    private Integer tipoReporte;

    @Column(name = "FECHA_CORTE", nullable = false)
    @CreatedDate
    private LocalDateTime fechaCorte;

    @Column(name = "FECHA_GENERACION", nullable = false)
    @CreatedDate
    private LocalDateTime fechaGeneracion;

    @Size(min = 1, max = 500)
    @Column(name = "COMENTARIOS", nullable = false,  length = 500)
    private String comentarios;

    @Size(min = 1, max = 200)
    @Column(name = "FIRMA", nullable = false,  length = 200)
    private String firma;

    @Size(max = 200)
    @Column(name = "PALABRA_CLAVE", nullable = false, length = 200)
    private String palabraClave;
}
