package co.com.muric.web.util;

import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.entities.util.StaticVariables;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.MessageFormat;

public class ValidateRequest {
    public static boolean isInvalidType(String type) {
        return type == null || type.isBlank();
    }

    public static boolean isInvalidTypeInputData(String type) {
        return !type.equalsIgnoreCase(StaticVariables.TYPE_FILE) && !type.equalsIgnoreCase(StaticVariables.TYPE_DATABASE);
    }

    public static boolean isFileTypeAndSourceBlank(String type, String source) {
        return type.equalsIgnoreCase(StaticVariables.TYPE_FILE) && (source == null || source.isBlank());
    }

    public static ResponseEntity<MuricResponseDTO> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(MuricResponseDTO.builder()
                        .resposeCode(status.value())
                        .responseType(status.toString())
                        .resposeMessage(message)
                        .build());
    }

    public static ResponseEntity<MuricResponseDTO> handleException(Exception e, String type, String source) {
        if (type == null && source == null) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, StaticVariables.INVALID_SOURCE_TYPE_PARAM);
        }
        if (e.getMessage().equalsIgnoreCase(StaticVariables.SOURCE_BLANK)) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, StaticVariables.INVALID_SOURCE_PARAM);
        }
        if (e.getMessage().equalsIgnoreCase(StaticVariables.TYPE_BLANK)) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, StaticVariables.INVALID_TYPE_PARAM);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MuricResponseDTO.builder()
                        .resposeCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .responseType(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .resposeMessage(MessageFormat.format(StaticVariables.PROCESS_GENERIC_ERROR, e))
                        .build());
    }
}