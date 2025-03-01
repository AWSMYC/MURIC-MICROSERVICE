package co.com.muric.usecase.implement;

import co.com.muric.entities.dto.Avro;
import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.entities.util.StaticVariables;
import co.com.muric.infrastructure.api.interfaces.ISuperintendenciaAPI;
import co.com.muric.infrastructure.db.interfaces.IMuricRepository;
import co.com.muric.usecase.interfaces.IMuricService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.text.MessageFormat;

@Service
public class MuricServiceImpl implements IMuricService {
    private static final Logger logger = LogManager.getLogger(MuricServiceImpl.class);

    private IMuricRepository muricRepository;
    private ISuperintendenciaAPI superintendenciaAPI;

    @Override
    public MuricResponseDTO generateAvro(String source, String type) {
        try {
            Avro avroData;
            switch (type.toUpperCase()) {
                case StaticVariables.SOURCE_FILE:
                    avroData = ProcessFile.generateAvroFromFiles(source);
                    if (null!=avroData) {
                        return MuricResponseDTO.builder()
                                .resposeCode(HttpStatus.BAD_REQUEST.value())
                                .responseType(HttpStatus.BAD_REQUEST.toString())
                                .resposeMessage(MessageFormat.format(StaticVariables.INVALID_INPUT_DATA,type))
                                .build();
                    }
                    logger.error(StaticVariables.PROCESS_FILE_ERROR);
                    break;
                case StaticVariables.SOURCE_DB:
                    avroData = ProcessFile.generateAvroFromDataBase();
                    if (null!=avroData) {
                        break;
                    }
                    logger.error(StaticVariables.PROCESS_DATABASE_ERROR);
                    break;
                default:
                    if (!type.equalsIgnoreCase(StaticVariables.SOURCE_FILE) && !type.equalsIgnoreCase(StaticVariables.SOURCE_DB)) {
                        logger.error(MessageFormat.format(StaticVariables.INVALID_INPUT_DATA,type));
                        return MuricResponseDTO.builder()
                                .resposeCode(HttpStatus.BAD_REQUEST.value())
                                .responseType(HttpStatus.BAD_REQUEST.toString())
                                .resposeMessage(MessageFormat.format(StaticVariables.INVALID_INPUT_DATA,type))
                                .build();
                    }
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format(StaticVariables.PROCESS_GENERIC_ERROR,e));
            return MuricResponseDTO.builder()
                    .resposeCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .responseType(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                    .resposeMessage(MessageFormat.format(StaticVariables.PROCESS_GENERIC_ERROR,e))
                    .build();
        }
        return MuricResponseDTO.builder().build();
    }

    private MuricResponseDTO sendAvro(){
        superintendenciaAPI.sendAvro(Avro.builder().build());
        return MuricResponseDTO.builder().build();
    }


}
