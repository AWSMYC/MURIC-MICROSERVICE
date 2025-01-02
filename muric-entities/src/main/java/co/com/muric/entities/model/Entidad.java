package co.com.muric.entities.model;

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
import java.util.List;

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

    @Column(name = "TIPO_ENTIDAD")
    @NotNull(message = "El tipo de entidad no puede ser nulo")
    @Min(value = 1, message = "El tipo de entidad debe ser al menos 1")
    @Max(value = 100, message = "El tipo de entidad no puede exceder 100")
    @Size(max = 200)
    private Integer tipoEntidad;

    @Column(name = "CODIGO_ENTIDAD")
    @NotNull(message = "El codigo de entidad no puede ser nulo")
    @Min(value = 1, message = "El codigo de entidad debe ser al menos 1")
    @Max(value = 100, message = "El codigo de entidad no puede exceder 100")
    @Size(max = 200)
    private String codigoEntidad;

    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE_ENTIDAD", length = 200)
    private String nombreEntidad;

    @Column(name = "TIPO_REPORTE")
    @NotNull(message = "El tipo de reporte no puede ser nulo")
    @Min(value = 1, message = "El tipo de reporte debe ser al menos 1")
    @Max(value = 100, message = "El tipo de reporte no puede exceder 100")
    @Size(max = 200)
    private Integer tipoReporte;

    @Column(name = "FECHA_CORTE")
    @CreatedDate
    private LocalDateTime fechaCorte;

    @Column(name = "FECHA_GENERACION")
    @CreatedDate
    private LocalDateTime fechaGeneracion;

    @Size(min = 1, max = 500)
    @Column(name = "COMENTARIOS",  length = 500)
    private String comentarios;

    @Size(min = 1, max = 200)
    @Column(name = "FIRMA",  length = 200)
    private String firma;

    @Size(max = 200)
    @Column(name = "PALABRA_CLAVE", length = 200)
    private String palabraClave;

    @OneToMany(mappedBy = "entidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovimientosCartera> movimientosCartera;

    @OneToMany(mappedBy = "entidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AtributosCredito> atributosCredito;

}
