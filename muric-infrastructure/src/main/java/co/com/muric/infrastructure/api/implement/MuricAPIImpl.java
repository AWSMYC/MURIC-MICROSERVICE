package co.com.muric.infrastructure.api.implement;

import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.infrastructure.api.interfaces.IMuricAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MuricAPIImpl implements IMuricAPI {
    private static final Logger logger = LogManager.getLogger(MuricAPIImpl.class);

    @Override
    public MuricResponseDTO generateAvro() {
        return null;
    }
}
