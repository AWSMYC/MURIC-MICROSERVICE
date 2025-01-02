package co.com.muric.entities.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "insumos_credito")
@EntityListeners(AuditingEntityListener.class)
public class InsumosCredito {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identificacion_credito_entidad")
    @NotNull(message = "El codigo de entidad no puede ser nulo")
    @Min(value = 1, message = "El codigo de entidad debe ser al menos 1")
    @Max(value = 100, message = "El codigo de entidad no puede exceder 100")
    private String identificacionCreditoEntidad;

    @Column(name = "tipo_identificacion")
    private MuricEnums tipoIdentificacion;

    @Column(name = "numero_identificacion")
    private String numeroIdentificacion;

    @Column(name = "modalidad")
    private MuricEnums modalidad;

    @Column(name = "codigo_producto")
    private MuricEnums codigoProducto;

    @Column(name = "calidad_deudor")
    private MuricEnums calidadDeudor;

    @Column(name = "fecha_desembolso")
    @CreatedDate
    private LocalDateTime fechaDesembolso;

    @Column(name = "fecha_vencimiento")
    @CreatedDate
    private LocalDateTime fechaVencimiento;

    @Column(name = "valor_desembolsado", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double valorDesembolsado;

    @Column(name = "frecuencia_pago_capital")
    private MuricEnums frecuenciaPagoCapital;

    @Column(name = "frecuencia_pago_intereses")
    private MuricEnums frecuenciaPagoIntereses;

    @Column(name = "tipo_tasa")
    private MuricEnums tipoTasa;

    @Column(name = "tipo_garantia")
    private MuricEnums tipoGarantia;

    @Column(name = "moneda")
    private MuricEnums moneda;

    @Column(name = "estado_registro")
    private MuricEnums estadoRegistro;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimientos_cartera_id")
    private MovimientosCartera movimientosCartera;

}
