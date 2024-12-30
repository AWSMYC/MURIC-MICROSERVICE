package co.com.muric.infrastructure.api.implement;

import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.infrastructure.api.interfaces.ISuperintendenciaAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SuperintendenciaAPIImpl implements ISuperintendenciaAPI {
    private static final Logger logger = LogManager.getLogger(SuperintendenciaAPIImpl.class);

    @Override
    public MuricResponseDTO sendAvro() {
        return null;
    }
}
