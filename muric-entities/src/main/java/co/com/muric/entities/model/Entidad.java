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
@Table(name = "entidad")
@EntityListeners(AuditingEntityListener.class)
public class Entidad {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_entidad")
    @NotNull(message = "El tipo de entidad no puede ser nulo")
    @Min(value = 1, message = "El tipo de entidad debe ser al menos 1")
    @Max(value = 100, message = "El tipo de entidad no puede exceder 100")
    @Size(max = 200)
    private Integer tipoEntidad;

    @Column(name = "codigo_entidad")
    @NotNull(message = "El codigo de entidad no puede ser nulo")
    @Min(value = 1, message = "El codigo de entidad debe ser al menos 1")
    @Max(value = 100, message = "El codigo de entidad no puede exceder 100")
    @Size(max = 200)
    private String codigoEntidad;

    @Size(min = 1, max = 200)
    @Column(name = "nombre_entidad", length = 200)
    private String nombreEntidad;

    @Column(name = "tipo_reporte")
    @NotNull(message = "El tipo de reporte no puede ser nulo")
    @Min(value = 1, message = "El tipo de reporte debe ser al menos 1")
    @Max(value = 100, message = "El tipo de reporte no puede exceder 100")
    @Size(max = 200)
    private Integer tipoReporte;

    @Column(name = "fecha_corte")
    @CreatedDate
    private LocalDateTime fechaCorte;

    @Column(name = "fecha_generacion")
    @CreatedDate
    private LocalDateTime fechaGeneracion;

    @Size(min = 1, max = 500)
    @Column(name = "comentarios",  length = 500)
    private String comentarios;

    @Size(min = 1, max = 200)
    @Column(name = "firma",  length = 200)
    private String firma;

    @Size(max = 200)
    @Column(name = "palabra_clave", length = 200)
    private String palabraClave;

    @OneToMany(mappedBy = "entidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovimientosCartera> movimientosCartera;

    @OneToMany(mappedBy = "entidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AtributosCredito> atributosCredito;

}
