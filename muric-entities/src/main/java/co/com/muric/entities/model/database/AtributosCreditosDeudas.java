package co.com.muric.entities.model.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "atributos_creditos_deudas")
public class AtributosCreditosDeudas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificacionCreditoEntidad;
    private Integer tipoIdentificacion;
    private String numeroIdentificacion;
    private Integer claveAtributo;
    private String valorAtributo;

    // Relación con InformacionCreditos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "identificacion_credito_entidad", referencedColumnName = "identificacionCreditoEntidad"),
            @JoinColumn(name = "tipo_identificacion", referencedColumnName = "tipoIdentificacion"),
            @JoinColumn(name = "numero_identificacion", referencedColumnName = "numeroIdentificacion")
    })
    private InformacionCreditos informacionCreditos;
}
