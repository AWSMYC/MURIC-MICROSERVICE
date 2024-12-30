package co.com.muric.usecase.implement;

import co.com.muric.entities.dto.MuricResponseDTO;

public interface IMuricService {
    MuricResponseDTO generateAvro (String source);
}
