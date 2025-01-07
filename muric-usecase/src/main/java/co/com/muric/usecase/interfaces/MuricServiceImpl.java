package co.com.muric.usecase.interfaces;

import co.com.muric.entities.dto.Avro;
import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.infrastructure.api.interfaces.ISuperintendenciaAPI;
import co.com.muric.infrastructure.db.interfaces.IMuricRepository;
import co.com.muric.usecase.implement.AvroMapper;
import co.com.muric.usecase.implement.IMuricService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MuricServiceImpl implements IMuricService {
    private static final Logger logger = LogManager.getLogger(MuricServiceImpl.class);

    private IMuricRepository muricRepository;
    private ISuperintendenciaAPI superintendenciaAPI;


    @Override
    public MuricResponseDTO generateAvro(String source) {
        MuricResponseDTO muricResponseDTO;
        switch (source) {
            case "FILE" -> muricResponseDTO = generateAvroFromFiles();
            case "DATABASE" -> muricResponseDTO = generateAvroFromDataBase();
            default -> muricResponseDTO = MuricResponseDTO.builder().build();
        }
        return muricResponseDTO;
    }

    private MuricResponseDTO generateAvroFromFiles(){
        AvroMapper.generateAvroStructure();
        return MuricResponseDTO.builder().build();
    }

    private MuricResponseDTO generateAvroFromDataBase() {
        muricRepository.findData();
        AvroMapper.generateAvroStructure();
        return MuricResponseDTO.builder().build();
    }

    private MuricResponseDTO sendAvro(){
        superintendenciaAPI.sendAvro();
        return MuricResponseDTO.builder().build();
    }

}
