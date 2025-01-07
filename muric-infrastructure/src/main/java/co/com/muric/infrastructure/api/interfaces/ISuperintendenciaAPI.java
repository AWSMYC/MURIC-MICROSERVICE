package co.com.muric.infrastructure.api.interfaces;

import co.com.muric.entities.dto.Avro;
import co.com.muric.entities.dto.MuricResponseDTO;

public interface ISuperintendenciaAPI {
    MuricResponseDTO sendAvro(Avro avro);
    MuricResponseDTO superFinancieraAuth();
}
