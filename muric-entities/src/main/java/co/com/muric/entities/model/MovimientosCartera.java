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
@Table(name = "MOVIMIENTOS_CARTERA")
@EntityListeners(AuditingEntityListener.class)
public class MovimientosCartera {
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

    @Column(name = "FECHA_CORTE")
    @CreatedDate
    private LocalDateTime fechaCorte;

    @Column(name = "CALIFICACION_CREDITO")
    private MuricEnums calificacionCredito;

    @Column(name = "ESTADO", length = 50)
    private MuricEnums estado;

    @Column(name = "PERIODO_GRACIA")
    private MuricEnums periodoGracia;

    @Column(name = "DIAS_MORA")
    @NotNull(message = "El tipo de reporte no puede ser nulo")
    @Min(value = 1, message = "El tipo de reporte debe ser al menos 1")
    @Max(value = 100, message = "El tipo de reporte no puede exceder 100")
    @Size(max = 200)
    private Integer diasMora;

    @Column(name = "TASA_INTERES", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double tasaInteres;

    @Column(name = "SPREAD_TASA_INTERES", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double spreadTasaInteres;

    @Column(name = "SALDO_CAPITAL", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double saldoCapital;

    @Column(name = "SALDO_INTERES", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double saldoInteres;

    @Column(name = "SALDO_OTROS", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double saldoOtros;

    @Column(name = "MODELO_PROVISIONES", length = 50)
    private MuricEnums modeloProvisiones;

    @Column(name = "PROVISION_PROCICLIICA", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double provisionProciclica;

    @Column(name = "PROVISION_CONTRACICLICA", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double provisionContraclica;

    @Column(name = "PROVISION_ADICIONAL_POLITICA_ENTIDAD", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double provisionAdicionalPoliticaEntidad;

    @Column(name = "PROVISION_OTROS", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double provisionOtros;

    @Column(name = "CUOTA_ESPERADA_CAPITAL", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double cuotaEsperadaCapital;

    @Column(name = "CUOTA_ESPERADA_INTERES", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double cuotaEsperadaInteres;

    @Column(name = "CUOTA_RECIBIDA_CAPITAL", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double cuotaRecibidaCapital;

    @Column(name = "CUOTA_RECIBIDA_INTERES", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double cuotaRecibidaInteres;

    @Column(name = "VALOR_GARANTIA")
    private MuricEnums valorGarantia;

    @Column(name = "FECHA_GARANTIA")
    @CreatedDate
    private LocalDateTime fechaGarantia;

    @Column(name = "PROBABILIDAD_INCUMPLIMIENTO_CREDITO", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double probabilidadIncumplimientoCredito;

    @Column(name = "PERDIDA_DADO_INCUMPLIMIENTO", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double perdidaDadoIncumplimientoCredito;

    @Size(max = 200)
    @Column(name = "ESTADO_REGISTRO", length = 200)
    private MuricEnums estadoRegistro;

    @OneToMany(mappedBy = "movimientosCartera", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AtributosCredito> atributosCredito;

    @OneToOne(mappedBy = "movimientosCartera", cascade = CascadeType.ALL, optional = true)
    private InsumosCredito insumosCredito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidad_id")
    private Entidad entidad;

}
