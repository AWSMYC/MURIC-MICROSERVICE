package co.com.muric.entities.util;

public class StaticVariables {
    // LOGS & MESSAGES VARIABLES
    public static final String INVALID_TYPE_PARAM ="El parámetro 'type' es obligatorios. El valor del parámetro type debe ser type=FILE or type=DATABASE\"";
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

    //

}