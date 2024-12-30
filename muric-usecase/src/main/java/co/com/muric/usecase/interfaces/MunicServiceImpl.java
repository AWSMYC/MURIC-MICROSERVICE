package co.com.muric.usecase.interfaces;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MunicServiceImpl {
    private static final Logger logger = LogManager.getLogger(MunicServiceImpl.class);
}
