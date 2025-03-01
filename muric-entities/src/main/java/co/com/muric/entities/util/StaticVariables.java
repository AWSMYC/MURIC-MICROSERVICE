package co.com.muric.entities.util;

public class StaticVariables {
    //MessageFormat.format(,);
    public static final String TYPE_INVALID_TYPE_PARAM ="El parámetro 'type' es obligatorios (el valor de type es FILE o DATABASE).";
    public static final String SOURCE_INVALID_TYPE_PARAM ="El parámetro 'source' es obligatorio si el valor de 'type' es FILE (el valor de source es el path url del archivo a procesar.";
    public static final String SOURCE_TYPE_INVALID_PARAM ="Los parámetros source y type son nulos - 400 Bad Request";
    public static final String PROCESS_FAIL_ERROR = "Error al procesar el archivo: {0}";
    public static final String SOURCE_FILE = "FILE";
    public static final String SOURCE_DB = "DATABASE";
    public static final String SOURCE_BLANK="Cannot invoke \"String.isBlank()\" because \"source\" is null";
    public static final String TYPE_BLANK="Cannot invoke \"String.isBlank()\" because \"type\" is null";
}