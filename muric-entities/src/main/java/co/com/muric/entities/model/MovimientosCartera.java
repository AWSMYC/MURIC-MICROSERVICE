package co.com.muric.entities.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "movimientos_cartera")
@EntityListeners(AuditingEntityListener.class)
public class MovimientosCartera {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificacion_credito_entidad")
    private String identificacionCreditoEntidad;

    @Column(name = "tipo_identificacion")
    private MuricEnums tipoIdentificacion;

    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;

    @Column(name = "fecha_corte")
    @CreatedDate
    private LocalDateTime fechaCorte;

    @Column(name = "calificacion_credito")
    private MuricEnums calificacionCredito;

    @Column(name = "estado")
    private MuricEnums estado;

    @Column(name = "periodo_gracia")
    private MuricEnums periodoGracia;

    @Column(name = "dias_mora")
    @NotNull(message = "El tipo de reporte no puede ser nulo")
    @Min(value = 1, message = "El tipo de reporte debe ser al menos 1")
    @Max(value = 100, message = "El tipo de reporte no puede exceder 100")
    private Integer diasMora;

    @Column(name = "tasa_interes", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double tasaInteres;

    @Column(name = "spread_tasa_interes", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double spreadTasaInteres;

    @Column(name = "saldo_capital", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double saldoCapital;

    @Column(name = "saldo_intereses", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double saldoInteres;

    @Column(name = "saldo_otros", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double saldoOtros;

    @Column(name = "modelo_provisiones", length = 50)
    private MuricEnums modeloProvisiones;

    @Column(name = "provision_prociclica", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double provisionProciclica;

    @Column(name = "provision_contraciclica", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double provisionContraclica;

    @Column(name = "provision_adicional_politica_entidad", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double provisionAdicionalPoliticaEntidad;

    @Column(name = "provision_otros", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double provisionOtros;

    @Column(name = "cuota_esperada_capital", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double cuotaEsperadaCapital;

    @Column(name = "cuota_esperada_intereses", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double cuotaEsperadaInteres;

    @Column(name = "cuota_recibida_capital", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double cuotaRecibidaCapital;

    @Column(name = "cuota_recibida_intereses", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double cuotaRecibidaInteres;

    @Column(name = "valor_garantia")
    private MuricEnums valorGarantia;

    @Column(name = "fecha_garantia")
    @CreatedDate
    private LocalDateTime fechaGarantia;

    @Column(name = "probabilidad_incumplimiento_credito", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double probabilidadIncumplimientoCredito;

    @Column(name = "perdida_dado_incumplimiento", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double perdidaDadoIncumplimientoCredito;

    @Column(name = "estado_registro")
    private MuricEnums estadoRegistro;

    @OneToMany(mappedBy = "movimientosCartera", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AtributosCredito> atributosCredito;

    @OneToOne(mappedBy = "movimientosCartera", cascade = CascadeType.ALL, optional = true)
    private InsumosCredito insumosCredito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidad_id")
    private Entidad entidad;

}
