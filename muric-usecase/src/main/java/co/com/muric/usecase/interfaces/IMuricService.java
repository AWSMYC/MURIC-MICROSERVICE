package co.com.muric.usecase.interfaces;

import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.entities.model.excel.UnifiedCreditInformation;

import java.util.List;

public interface IMuricService {
    Object generateAvro (String source, String type);
}
