package co.com.muric.entities.util;

public class StaticVariables {
    // LOGS & MESSAGES VARIABLES
    public static final String INVALID_TYPE_PARAM ="El parámetro 'type' es obligatorios. El valor del parámetro type debe ser type=FILE or type=DATABASE";
    public static final String INVALID_SOURCE_PARAM ="El parámetro 'source' es obligatorio si el valor de 'type' es FILE (el valor de source es el path url del archivo a procesar.";
    public static final String SOURCE_REQUESTPARAM ="source";
    public static final String TYPE_REQUESTPARAM ="type";
    public static final String INVALID_SOURCE_TYPE_PARAM ="Los parámetros source y type son nulos - 400 Bad Request";
    public static final String PROCESS_FILE_ERROR = "Error al procesar el archivo : {0}";
    public static final String PROCESS_FILE_OK = "Archivo procesado correctamente";
    public static final String PROCESS_DATABASE_OK = "Base de datos procesada correctamente";
    public static final String PROCESS_GENERIC_ERROR = "Error en el proceso {0}";
    public static final String PROCESS_DATABASE_ERROR = "Error al procesar registros desde la base de datos";
    public static final String TYPE_FILE = "FILE";
    public static final String TYPE_DATABASE = "DATABASE";
    public static final String SOURCE_BLANK="Cannot invoke \"String.isBlank()\" because \"source\" is null";
    public static final String TYPE_BLANK="Cannot invoke \"String.isBlank()\" because \"type\" is null";
    public static final String BASE_REST_PATH="/muric";
    public static final String GENERATE_AVRO_REST_PATH="/generateAvro";
    public static final String HEALHT_CHECK_AVRO_REST_PATH="/healthCheck";
    public static final String CROSSORIGINS="*";
    public static final String ALLOWHEADERS="*";
    public static final String INVALID_TYPE_INPUT_DATA="Bad Request - El valor => {0} <= del parámetro type no es valido. El valor del parámetro type debe ser type=FILE or type=DATABASE";
    public static final String COMPONENT_SCARN="co.com.muric";

    // FILE VARIABLES
    public static final String SHEET_CREDIT_INFORMATION_NAME ="Información de los créditos";
    public static final String SHEET_CREDITS_DEBTS_NAME ="Atributos de los créditos y deu";
    public static final String SHEET_WALLET_MOVEMENTS_NAME ="Movimientos de cartera";

    // SHEET CREDIT INFORMATION FIELDS
    public static final String IDENTIFICACION_CREDITO_ENTIDAD ="identificacion_credito_entidad";
    public static final String TIPO_IDENTIFICACION ="tipo_identificacion";
    public static final String NUMERO_IDENTIFICACION ="numero_identificacion";
    public static final String MODALIDAD ="modalidad";
    public static final String CODIGO_PRODUCTO ="codigo_producto";
    public static final String CALIDAD_DEUDOR ="calidad_deudor";
    public static final String FECHA_DESEMBOLSO ="fecha_desembolso";
    public static final String FECHA_VENCIMIENTO ="fecha_vencimiento";
    public static final String VALOR_DESEMBOLSADO ="valor_desembolsado";
    public static final String FRECUENCIA_PAGO_CAPITAL ="frecuencia_pago_capital";
    public static final String FRECUENCIA_PAGO_INTERESES ="frecuencia_pago_intereses";
    public static final String TIPO_TASA ="tipo_tasa";
    public static final String TIPO_GARANTIA ="tipo_garantia";
    public static final String MONEDA ="moneda";
    public static final String ESTADO_REGISTRO ="estado_registro";

    // SHEET CREDITS DEBTIS FIELDS
    public static final String CLAVE_ATRIBUTO ="clave_atributo";
    public static final String VALOR_ATRIBUTO ="valor_atributo";

    // SHEET WALLET MOVEMENTS FIELDS
    public static final String FECHA_CORTE ="fecha_corte";
    public static final String CALIFICACION_CREDITO ="calificacion_credito";
    public static final String ESTADO ="estado";
    public static final String PERIODO_GRACIA ="periodo_gracia";
    public static final String DIAS_MORA ="dias_mora";
    public static final String TASA_INTERES ="tasa_interes";
    public static final String SPREAD_TASA_INTERES ="spread_tasa_interes";
    public static final String SALDO_CAPITAL ="saldo_capital";
    public static final String SALDO_INTERESES ="saldo_intereses";
    public static final String SALDO_OTROS ="saldo_otros";
    public static final String MODELO_PROVISIONES ="modelo_provisiones";
    public static final String PROVISION_PROCICLICA ="provision_prociclica";
    public static final String PROVISION_CONTRACICLICA ="provision_contraciclica";
    public static final String PROVISION_ADICIONAL_POLITICA_ENTIDAD ="provision_adicional_politica_entidad";
    public static final String PROVISION_OTROS ="provision_otros";
    public static final String CUOTA_ESPERADA_CAPITAL ="cuota_esperada_capital";
    public static final String CUOTA_ESPERADA_INTERESES ="cuota_esperada_intereses";
    public static final String CUOTA_RECIBIDA_CAPITAL ="cuota_recibida_capital";
    public static final String CUOTA_RECIBIDA_INTERESES ="cuota_recibida_intereses";
    public static final String VALOR_GARANTIA ="valor_garantia";
    public static final String FECHA_GARANTIA ="fecha_garantia";
    public static final String PROBABILIDAD_INCUMPLIMIENTO_CREDITO ="probabilidad_incumplimiento_credito";

}

