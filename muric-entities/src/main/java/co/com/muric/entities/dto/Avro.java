package co.com.muric.entities.dto;

import co.com.muric.entities.model.MuricEnums;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Avro {
    private String type;
    private String name;
    private Integer tipoEntidad;
    private Integer codigoEntidad;
    private LocalDateTime fechaCorte;
    private LocalDateTime fechaGeneracion;
    private String comentarios;
    private String firma;
    private String palabraClave;
    private List<Credito> creditos;
    private List<Movimiento> movimientos;
    private List<Demografico> demograficos;

    public static class Credito {
        private String name;
        private String identificacionCreditoEntidad;
        private MuricEnums tipoIdentificacion;
        private String numeroIdentificacion;
        private MuricEnums modalidad;
        private MuricEnums codigoProducto;
        private MuricEnums calidadDeudor;
        private LocalDateTime fechaDesembolso;
        private LocalDateTime fechaVencimiento; // Manejo de null
        private Double valorDesembolsado;
        private MuricEnums frecuenciaPagoCapital;
        private MuricEnums frecuenciaPagoIntereses;
        private MuricEnums tipoTasa;
        private MuricEnums tipoGarantia;
        private MuricEnums moneda;
        private MuricEnums estadoRegistro;
    }

    public static class Movimiento {
        private String identificacionCreditoEntidad;
        private MuricEnums tipoIdentificacion;
        private String numeroIdentificacion;
        private LocalDateTime fechaCorte;
        private MuricEnums calificacionCredito;
        private MuricEnums estado;
        private MuricEnums periodoGracia;
        private Integer diasMora;
        private Double tasaInteres;
        private Double spreadTasaInteres;
        private Double saldoCapital;
        private Double saldoIntereses;
        private Double saldoOtros;
        private MuricEnums modeloProvisiones;
        private Double provisionProciclica;
        private Double provisionContraciclica;
        private Double provisionAdicionalPoliticaEntidad;
        private Double provisionOtros;
        private Double provisionTotal;
        private Double cuotaEsperadaCapital;
        private Double cuotaEsperadaIntereses;
        private Double cuotaRecibidaCapital;
        private Double cuotaRecibidaIntereses;
        private Integer fechaGarantia; // Manejo de null
        private Double valorGarantia; // Manejo de null
        private Double probabilidadIncumplimientoCredito;
        private Double perdidaDadoIncumplimiento;
        private MuricEnums estadoRegistro;
    }

    public static class Demografico {
        private String identificacionCreditoEntidad;
        private MuricEnums tipoIdentificacion;
        private String numeroIdentificacion;
        private Integer claveAtributo;
        private String valorAtributo;
    }
}
