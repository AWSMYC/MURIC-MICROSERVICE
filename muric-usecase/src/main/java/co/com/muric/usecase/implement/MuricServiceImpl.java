package co.com.muric.usecase.implement;

import co.com.muric.entities.dto.Avro;
import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.entities.util.StaticVariables;
import co.com.muric.infrastructure.api.interfaces.ISuperintendenciaAPI;
import co.com.muric.infrastructure.db.interfaces.IMuricRepository;
import co.com.muric.usecase.interfaces.IMuricService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MuricServiceImpl implements IMuricService {
    private static final Logger logger = LogManager.getLogger(MuricServiceImpl.class);

    private IMuricRepository muricRepository;
    private ISuperintendenciaAPI superintendenciaAPI;

    @Override
    public MuricResponseDTO generateAvro(String source, String type) {
        try {
            Avro avroData;
            switch (source.toUpperCase()) {
                case StaticVariables.SOURCE_FILE:
                    avroData = ProcessFile.generateAvroFromFiles();
                    if (null!=avroData) {
                        break;
                    }
                    logger.error("Error al obtener la información desde archivos: Respuesta inválida.");
                    break;
                case StaticVariables.SOURCE_DB:
                    avroData = ProcessFile.generateAvroFromFiles();
                    if (null!=avroData) {
                        break;
                    }
                    logger.error("Error al obtener la información desde base de datos: Respuesta inválida.");
                    break;
                default:
                    logger.error("Fuente no válida: {}", source);
                    avroData =  Avro.builder().build();
            }
        } catch (Exception e) {
            logger.error("Error inesperado: " + e.getMessage(), e);
            return MuricResponseDTO.builder()
                    .codeRespose(500)
                    .msgRespose("")
                    .build();
        }
        return MuricResponseDTO.builder().build();
    }

    private MuricResponseDTO generateAvroFromDataBase() {
        muricRepository.findData();
        superintendenciaAPI.sendAvro(Avro.builder().build());
        return MuricResponseDTO.builder().build();
    }

    private MuricResponseDTO sendAvro(){
        superintendenciaAPI.sendAvro(Avro.builder().build());
        return MuricResponseDTO.builder().build();
    }


}
