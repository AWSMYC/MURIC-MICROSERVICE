package co.com.muric.infrastructure.db.implement;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MunicRepositoryImpl {
    private static final Logger logger = LogManager.getLogger(MunicRepositoryImpl.class);
}
