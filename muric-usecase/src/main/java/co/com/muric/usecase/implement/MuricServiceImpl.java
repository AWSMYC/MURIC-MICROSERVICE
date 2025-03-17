package co.com.muric.usecase.implement;

import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.entities.util.StaticVariables;
import co.com.muric.infrastructure.api.interfaces.ISuperintendenciaAPI;
import co.com.muric.usecase.interfaces.IMuricService;
import co.com.muric.usecase.util.ResponseFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
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
            if (StaticVariables.TYPE_FILE.equalsIgnoreCase(type)) {
                ProcessData.generateAvroFormat(source);
                return MuricResponseDTO.builder()
                        .resposeCode(HttpStatus.OK.value())
                        .responseType(HttpStatus.OK.toString())
                        .resposeMessage(StaticVariables.PROCESS_FILE_OK)
                        .build();
            } else if (StaticVariables.TYPE_DATABASE.equalsIgnoreCase(type)) {
                Object avroData = generateAvroFromDataBase();
                if (avroData != null) {
                    return ResponseFormat.createSuccessResponse(StaticVariables.PROCESS_DATABASE_OK);
                } else {
                    logger.error(StaticVariables.PROCESS_DATABASE_ERROR);
                    return MuricResponseDTO.builder()
                            .resposeCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .responseType(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                            .resposeMessage(StaticVariables.PROCESS_DATABASE_ERROR)
                            .build();
                }
            } else {
                return ResponseFormat.createErrorResponse(StaticVariables.PROCESS_GENERIC_ERROR, source);
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format(StaticVariables.PROCESS_GENERIC_ERROR, type));
            return ResponseFormat.createErrorResponse(StaticVariables.PROCESS_GENERIC_ERROR, null);
        }
    }

    public static Object generateAvroFromDataBase() {
        //muricRepository.findData();
        //superintendenciaAPI.sendAvro(Avro.builder().build());
        return null;
    }

}
