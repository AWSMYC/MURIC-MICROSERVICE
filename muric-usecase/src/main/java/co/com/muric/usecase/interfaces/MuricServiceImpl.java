package co.com.muric.usecase.interfaces;

import co.com.muric.entities.dto.Avro;
import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.entities.model.MuricField;
import co.com.muric.infrastructure.api.interfaces.ISuperintendenciaAPI;
import co.com.muric.infrastructure.db.interfaces.IMuricRepository;
import co.com.muric.usecase.implement.IMuricService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MuricServiceImpl implements IMuricService {
    private static final Logger logger = LogManager.getLogger(MuricServiceImpl.class);

    private IMuricRepository muricRepository;
    private ISuperintendenciaAPI superintendenciaAPI;

    @Override
    public MuricResponseDTO generateAvro(String source) {
        MuricResponseDTO muricResponseDTO;
        try {
            switch (source.toUpperCase()) {
                case "FILE":
                    muricResponseDTO = generateAvroFromFiles();
                    if (muricResponseDTO != null && muricResponseDTO.getCodeRespose() == 200) {
                        break;
                    }
                    logger.error("Error al obtener la información desde archivos: Respuesta inválida.");
                    break;
                case "DATABASE":
                    muricResponseDTO = generateAvroFromDataBase();
                    if (muricResponseDTO != null && muricResponseDTO.getCodeRespose() == 200) {
                        break;
                    }
                    logger.error("Error al obtener la información desde base de datos: Respuesta inválida.");
                    break;
                default:
                    logger.error("Fuente no válida: {}", source);
                    muricResponseDTO = MuricResponseDTO.builder().codeRespose(400).msgRespose("Fuente no válida").build();
                    break;
            }
        } catch (Exception e) {
            logger.error("Error inesperado: " + e.getMessage(), e);
            muricResponseDTO = MuricResponseDTO.builder().codeRespose(500).msgRespose("Error inesperado al generar AVRO").build();
        }

        return muricResponseDTO;
    }


    private MuricResponseDTO generateAvroFromFiles(){
        superintendenciaAPI.sendAvro(avroMapper());
        return MuricResponseDTO.builder().build();
    }

    private MuricResponseDTO generateAvroFromDataBase() {
        muricRepository.findData();
        superintendenciaAPI.sendAvro(avroMapper());
        return MuricResponseDTO.builder().build();
    }

    private MuricResponseDTO sendAvro(){
        superintendenciaAPI.sendAvro(Avro.builder().build());
        return MuricResponseDTO.builder().build();
    }

    private Avro avroMapper() {
        List<Object> creditoFields = new ArrayList<>();
        List<Object> movimientoFields = new ArrayList<>();
        List<Object> demograficoFields = new ArrayList<>();
        return Avro.builder()
                .type(null)
                .name(null)
                .tipoEntidad(null)
                .codigoEntidad(null)
                .fechaCorte(MuricField.MuricFieldNameTypeSubType.builder()
                        .name(null)
                        .type(MuricField.MuricFieldNameTypeSubType.MuricFieldLogicalType.builder().build())
                        .build())
                .fechaGeneracion(MuricField.MuricFieldNameTypeSubType.builder()
                        .name(null)
                        .type(MuricField.MuricFieldNameTypeSubType.MuricFieldLogicalType.builder().build())
                        .build())
                .comentarios(null)
                .firma(null)
                .palabraClave(null)
                .creditoFields(creditoFields)
                .movimientoFields(movimientoFields)
                .demograficoFields(demograficoFields)
                .build();
    }
}
