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
@Table(name = "INSUMOS_CREDITO")
@EntityListeners(AuditingEntityListener.class)
public class InsumosCredito {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "IDENTIFICACION_CREDITO_ENTIDAD", length = 50)
    private String identificacionCreditoEntidad;

    @Size(max = 50)
    @Column(name = "TIPO_IDENTIFICACION", length = 50)
    private String tipoIdentificacion;

    @Size(max = 50)
    @Column(name = "NUMERO_INDENTIFICACION", length = 50)
    private String numeroIdentificacion;

    @Size(max = 50)
    @Column(name = "MODALIDAD", length = 50)
    private String modalidad;

    @Size(max = 50)
    @Column(name = "CODIGO_PRODUCTO", length = 50)
    private String codigoProducto;

    @Size(max = 50)
    @Column(name = "CALIDAD_DEUDOR", length = 50)
    private String calidadDeudor;

    @Size(max = 50)
    @Column(name = "FECHA_DESEMBOLSO", length = 50)
    private String fechaDesembolso;

    @Size(max = 50)
    @Column(name = "FECHA_VENCIMIENTO", length = 50)
    private String fechaVencimiento;

    @Size(max = 50)
    @Column(name = "VALOR_DESEMBOLSADO", length = 50)
    private String valorDesembolsado;

    @Size(max = 50)
    @Column(name = "FRECUENCIA_PAGO_CAPITAL", length = 50)
    private String frecuenciaPagoCapital;

    @Size(max = 50)
    @Column(name = "FRECUENCIA_PAGO_INTERESES", length = 50)
    private String frecuenciaPagoIntereses;

    @Size(max = 50)
    @Column(name = "TIPO_TASA", length = 50)
    private String tipoTasa;

    @Size(max = 50)
    @Column(name = "TIPO_GARANTIA", length = 50)
    private String tipoGarantia;

    @Size(max = 50)
    @Column(name = "MONEDA", length = 50)
    private String moneda;

    @Size(max = 50)
    @Column(name = "ESTADO_REGISTRO", length = 50)
    private String esatdoRegistro;
}
