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

    @Size(max = 50)
    @Column(name = "TIPO_IDENTIFICACION", length = 50)
    private String tipoIdentificacion;

    @Size(max = 50)
    @Column(name = "NUMERO_INDENTIFICACION", length = 50)
    private String numeroIdentificacion;

    @Size(max = 50)
    @Column(name = "FECHA_CORTE", length = 50)
    private String fechaCorte;

    @Size(max = 50)
    @Column(name = "CALIFICACION_CREDITO", length = 50)
    private String calificacionCredito;

    @Size(max = 50)
    @Column(name = "ESTADO", length = 50)
    private String estado;

    @Size(max = 50)
    @Column(name = "PERIODO_GRACIA", length = 50)
    private String periodoGracia;

    @Size(max = 50)
    @Column(name = "DIAS_MORA", length = 50)
    private String diasMora;

    @Size(max = 50)
    @Column(name = "TASA_INTERES", length = 50)
    private String tasaInteres;

    @Size(max = 50)
    @Column(name = "SPREAD_TASA_INTERES", length = 50)
    private String spreadTasaInteres;

    @Size(max = 50)
    @Column(name = "SALDO_CAPITAL", length = 50)
    private String saldoCapital;

    @Size(max = 50)
    @Column(name = "SALDO_INTERES", length = 50)
    private String saldoInteres;

    @Size(max = 50)
    @Column(name = "SALDO_OTROS", length = 50)
    private String saldoOtros;

    @Size(max = 50)
    @Column(name = "MODELO_PROVISIONES", length = 50)
    private String modeloProvisiones;

    @Size(max = 50)
    @Column(name = "PROVISION_PROCICLIICA", length = 50)
    private String provisionProciclica;

    @Size(max = 50)
    @Column(name = "PROVISION_CONTRACICLICA", length = 50)
    private String provisionContraclica;

    @Size(max = 50)
    @Column(name = "PROVISION_ADICIONAL_POLITICA_ENTIDAD", length = 50)
    private String provisionAdicionalPoliticaEntidad;

    @Size(max = 50)
    @Column(name = "PROVISION_OTROS", length = 50)
    private String provisionOtros;

    @Size(max = 50)
    @Column(name = "CUOTA_ESPERADA_CAPITAL", length = 50)
    private String cuotaEsperadaCapital;

    @Size(max = 50)
    @Column(name = "CUOTA_ESPERADA_INTERES", length = 50)
    private String cuotaEsperadaInteres;

    @Size(max = 50)
    @Column(name = "CUOTA_RECIBIDA_CAPITAL", length = 50)
    private String cuotaRecibidaCapital;

    @Size(max = 50)
    @Column(name = "CUOTA_RECIBIDA_INTERES", length = 50)
    private String cuotaRecibidaInteres;

    @Size(max = 50)
    @Column(name = "VALOR_GARANTIA", length = 50)
    private String valorGarantia;

    @Size(max = 50)
    @Column(name = "FECHA_GARANTIA", length = 50)
    private String fechaGarantia;

    @Size(max = 50)
    @Column(name = "PROBABILIDAD_INCUMPLIMIENTO_CREDITO", length = 50)
    private String probabilidadIncumplimientoCredito;

    @Size(max = 50)
    @Column(name = "PERDIDA_DADO_INCUMPLIMIENTO", length = 50)
    private String perdidaDadoIncumplimientoCredito;

}
