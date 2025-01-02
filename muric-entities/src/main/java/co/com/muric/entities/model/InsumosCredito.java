package co.com.muric.entities.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

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

    @Size(max = 200)
    @Column(name = "IDENTIFICACION_CREDITO_ENTIDAD", length = 200)
    private String identificacionCreditoEntidad;

    @Size(max = 200)
    @Column(name = "TIPO_IDENTIFICACION", length = 200)
    private String tipoIdentificacion;

    @Size(max = 200)
    @Column(name = "NUMERO_INDENTIFICACION", length = 200)
    private String numeroIdentificacion;

    @Size(max = 200)
    @Column(name = "MODALIDAD", length = 200)
    private String modalidad;

    @Size(max = 200)
    @Column(name = "CODIGO_PRODUCTO", length = 200)
    private String codigoProducto;

    @Size(max = 200)
    @Column(name = "CALIDAD_DEUDOR", length = 200)
    private String calidadDeudor;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_DESEMBOLSO")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaDesembolso;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_VENCIMIENTO")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaVencimiento;

    @Column(name = "VALOR_DESEMBOLSADO", nullable = false, precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener hasta 12 dígitos enteros y 2 decimales")
    private Double valorDesembolsado;

    @Size(max = 200)
    @Column(name = "FRECUENCIA_PAGO_CAPITAL", length = 200)
    private String frecuenciaPagoCapital;

    @Size(max = 200)
    @Column(name = "FRECUENCIA_PAGO_INTERESES", length = 200)
    private String frecuenciaPagoIntereses;

    @Size(max = 200)
    @Column(name = "TIPO_TASA", length = 200)
    private String tipoTasa;

    @Size(max = 200)
    @Column(name = "TIPO_GARANTIA", length = 200)
    private String tipoGarantia;

    @Size(max = 200)
    @Column(name = "MONEDA", length = 200)
    private String moneda;

    @Size(max = 200)
    @Column(name = "ESTADO_REGISTRO", length = 200)
    private String estadoRegistro;
}
