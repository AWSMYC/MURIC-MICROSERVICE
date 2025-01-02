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
@Table(name = "ENTIDAD")
@EntityListeners(AuditingEntityListener.class)
public class Entidad {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "TIPO_ENTIDAD", length = 50)
    private String tipoEntidad;

    @Size(max = 50)
    @Column(name = "CODIGO_ENTIDAD", length = 50)
    private String codigoEntidad;

    @Size(max = 50)
    @Column(name = "NOMBRE_ENTIDAD", length = 50)
    private String nombreEntidad;

    @Size(max = 50)
    @Column(name = "TIPO_REPORTE", length = 50)
    private String tipoReporte;

    @Size(max = 50)
    @Column(name = "FECHA_CORTE", length = 50)
    private String fechaCorte;

    @Size(max = 50)
    @Column(name = "FECHA_GENERACION", length = 50)
    private String fechaGeneracion;

    @Size(max = 50)
    @Column(name = "COMENTARIOS", length = 50)
    private String comentarios;

    @Size(max = 50)
    @Column(name = "FIRMA", length = 50)
    private String firma;

    @Size(max = 50)
    @Column(name = "PALABRA_CLAVE", length = 50)
    private String palabraClave;
}
