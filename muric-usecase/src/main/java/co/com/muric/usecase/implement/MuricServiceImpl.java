package co.com.muric.usecase.implement;

import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.entities.util.StaticVariables;
import co.com.muric.infrastructure.api.interfaces.ISuperintendenciaAPI;
import co.com.muric.usecase.interfaces.IMuricService;
import co.com.muric.usecase.util.ResponseFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class MuricServiceImpl implements IMuricService {

    private static final Logger logger = LogManager.getLogger(MuricServiceImpl.class);

    private final ISuperintendenciaAPI superintendenciaAPI;

    public MuricServiceImpl(ISuperintendenciaAPI superintendenciaAPI) {
        this.superintendenciaAPI = superintendenciaAPI;
    }

    @Override
    public MuricResponseDTO generateAvro(String source, String type) {
        try {
            Object avroData = null;
            switch (type) {
                case StaticVariables.TYPE_FILE:
                    avroData = ProcessData.generateAvroFormatFromFile(source);
                    if (avroData != null) {
                        return ResponseFormat.createSuccessResponse(StaticVariables.PROCESS_FILE_OK);
                    } else {
                        return ResponseFormat.createErrorResponse(MessageFormat.format(StaticVariables.PROCESS_FILE_ERROR, source));
                    }
                case StaticVariables.TYPE_DATABASE:
                    avroData = ProcessData.generateAvroFormatFromDataBase(source);
                    if (avroData != null) {
                        return ResponseFormat.createSuccessResponse(StaticVariables.PROCESS_DATABASE_OK);
                    } else {
                        return ResponseFormat.createErrorResponse(StaticVariables.PROCESS_DATABASE_ERROR);
                    }
                default:
                    logger.error(StaticVariables.PROCESS_GENERIC_ERROR);
                    return ResponseFormat.createErrorResponse(MessageFormat.format(StaticVariables.PROCESS_GENERIC_ERROR, source));
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format(StaticVariables.PROCESS_GENERIC_ERROR, type));
            return ResponseFormat.createErrorResponse(MessageFormat.format(StaticVariables.PROCESS_GENERIC_ERROR, e));
        }
    }

}