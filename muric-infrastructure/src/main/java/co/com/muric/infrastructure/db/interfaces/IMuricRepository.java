package co.com.muric.infrastructure.db.interfaces;

import co.com.muric.entities.dto.MuricResponseDTO;

public interface IMuricRepository {
    MuricResponseDTO findData ();
}
