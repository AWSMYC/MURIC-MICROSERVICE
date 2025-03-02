package co.com.muric.usecase.implement;

import co.com.muric.entities.dto.Avro;
import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.entities.util.StaticVariables;
import co.com.muric.infrastructure.api.interfaces.ISuperintendenciaAPI;
import co.com.muric.infrastructure.db.interfaces.IMuricRepository;
import co.com.muric.usecase.interfaces.IMuricService;
import co.com.muric.usecase.util.FileDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
                case StaticVariables.TYPE_FILE:
                    FileDataSource.readSheetInformacionCredito(source);
                    FileDataSource.readSheetAtributoCreditoDeuda(source);
                    FileDataSource.readSheetMovimientoCartera(source);
                    avroData = ProcessFile.avroMapper(FileDataSource.readSheetInformacionCredito(source),
                            FileDataSource.readSheetAtributoCreditoDeuda(source),
                            FileDataSource.readSheetMovimientoCartera(source));
                    if (null!=avroData) {
                        return MuricResponseDTO.builder()
                                .resposeCode(HttpStatus.OK.value())
                                .responseType(HttpStatus.OK.toString())
                                .resposeMessage(StaticVariables.PROCESS_FILE_OK)
                                .build();
                    } else {
                        logger.error(MessageFormat.format(StaticVariables.PROCESS_FILE_ERROR, source));
                        return MuricResponseDTO.builder()
                                .resposeCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .responseType(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                                .resposeMessage(MessageFormat.format(StaticVariables.PROCESS_FILE_ERROR, source))
                                .build();
                    }
                case StaticVariables.TYPE_DATABASE:
                    avroData = ProcessFile.generateAvroFromDataBase();
                    if (null!=avroData) {
                        return MuricResponseDTO.builder()
                                .resposeCode(HttpStatus.OK.value())
                                .responseType(HttpStatus.OK.toString())
                                .resposeMessage(StaticVariables.PROCESS_DATABASE_OK)
                                .build();
                    } else {
                        logger.error(StaticVariables.PROCESS_DATABASE_ERROR);
                        return MuricResponseDTO.builder()
                                .resposeCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .responseType(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                                .resposeMessage(StaticVariables.PROCESS_DATABASE_ERROR)
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
