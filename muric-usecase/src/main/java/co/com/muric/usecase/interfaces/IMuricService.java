package co.com.muric.usecase.interfaces;

import co.com.muric.entities.dto.MuricResponseDTO;

public interface IMuricService {
    MuricResponseDTO generateAvro (String source, String type);
}
