package co.com.muric.entities.model.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "informacion_creditos")
public class InformacionCreditos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificacionCreditoEntidad;
    private Integer tipoIdentificacion;
    private String numeroIdentificacion;
    private Integer modalidad;
    private Integer codigoProducto;
    private Integer calidadDeudor;
    private Integer fechaDesembolso;
    private Integer fechaVencimiento;
    private Float valorDesembolsado;
    private Integer frecuenciaPagoCapital;
    private Integer frecuenciaPagoIntereses;
    private String tipoTasa;
    private Integer tipoGarantia;
    private String moneda;
    private String estadoRegistro;

    // Relación con MovimientosCartera
    @OneToMany(mappedBy = "informacionCreditos", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MovimientosCartera> movimientosCartera;

    // Relación con AtributosCreditosDeudas
    @OneToMany(mappedBy = "informacionCreditos", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AtributosCreditosDeudas> atributosCreditosDeudas;
}
