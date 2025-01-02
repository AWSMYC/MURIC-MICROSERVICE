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
@Table(name = "INSUMOS_CREDITO")
@EntityListeners(AuditingEntityListener.class)
public class InsumosCredito {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IDENTIFICACION_CREDITO_ENTIDAD")
    @NotNull(message = "El codigo de entidad no puede ser nulo")
    @Min(value = 1, message = "El codigo de entidad debe ser al menos 1")
    @Max(value = 100, message = "El codigo de entidad no puede exceder 100")
    @Size(max = 200)
    private String identificacionCreditoEntidad;

    @Column(name = "TIPO_IDENTIFICACION")
    private MuricEnums tipoIdentificacion;

    @Size(max = 200)
    @Column(name = "NUMERO_INDENTIFICACION", length = 200)
    private String numeroIdentificacion;

    @Column(name = "MODALIDAD")
    private MuricEnums modalidad;

    @Column(name = "CODIGO_PRODUCTO")
    private MuricEnums codigoProducto;

    @Column(name = "CALIDAD_DEUDOR")
    private MuricEnums calidadDeudor;

    @Column(name = "FECHA_DESEMBOLSO")
    @CreatedDate
    private LocalDateTime fechaDesembolso;

    @Column(name = "FECHA_VENCIMIENTO")
    @CreatedDate
    private LocalDateTime fechaVencimiento;

    @Column(name = "VALOR_DESEMBOLSADO", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double valorDesembolsado;

    @Column(name = "FRECUENCIA_PAGO_CAPITAL")
    private MuricEnums frecuenciaPagoCapital;

    @Column(name = "FRECUENCIA_PAGO_INTERESES", length = 200)
    private MuricEnums frecuenciaPagoIntereses;

    @Column(name = "TIPO_TASA")
    private MuricEnums tipoTasa;

    @Size(max = 200)
    @Column(name = "TIPO_GARANTIA", length = 200)
    private MuricEnums tipoGarantia;

    @Size(max = 200)
    @Column(name = "MONEDA", length = 200)
    private MuricEnums moneda;

    @Size(max = 200)
    @Column(name = "ESTADO_REGISTRO", length = 200)
    private MuricEnums estadoRegistro;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimientos_cartera_id")
    private MovimientosCartera movimientosCartera;

}
