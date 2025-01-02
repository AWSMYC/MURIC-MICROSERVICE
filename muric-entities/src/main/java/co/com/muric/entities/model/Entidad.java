package co.com.muric.entities.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    @Size(max = 200)
    @Column(name = "TIPO_ENTIDAD", length = 200)
    private String tipoEntidad;

    @Size(max = 200)
    @Column(name = "CODIGO_ENTIDAD", length = 200)
    private String codigoEntidad;

    @Size(max = 200)
    @Column(name = "NOMBRE_ENTIDAD", length = 200)
    private String nombreEntidad;

    @Size(max = 200)
    @Column(name = "TIPO_REPORTE", length = 200)
    private String tipoReporte;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_CORTE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaCorte;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_GENERACION")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String fechaGeneracion;

    @Size(max = 500)
    @Column(name = "COMENTARIOS", length = 500)
    private String comentarios;

    @Size(max = 200)
    @Column(name = "FIRMA", length = 200)
    private String firma;

    @Size(max = 200)
    @Column(name = "PALABRA_CLAVE", length = 200)
    private String palabraClave;
}
