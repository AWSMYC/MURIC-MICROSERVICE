package co.com.muric.usecase.util;

import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.usecase.implement.MuricServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

public class ResponseFormat {

    private static final Logger logger = LogManager.getLogger(MuricServiceImpl.class);

    public static MuricResponseDTO createSuccessResponse(String message) {
        return MuricResponseDTO.builder()
                .resposeCode(HttpStatus.OK.value())
                .responseType(HttpStatus.OK.toString())
                .resposeMessage(message)
                .build();
    }

    public static MuricResponseDTO createErrorResponse(String message) {
        return MuricResponseDTO.builder()
                .resposeCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .responseType(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .resposeMessage(message)
                .build();
    }

}
