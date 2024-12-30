package co.com.muric.infrastructure.db.implement;

import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.infrastructure.db.interfaces.IMuricRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MuricRepositoryImpl implements IMuricRepository {
    private static final Logger logger = LogManager.getLogger(MuricRepositoryImpl.class);

    @Override
    public MuricResponseDTO findData() {
        return null;
    }
}
