package co.com.muric.web.test;

import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.usecase.interfaces.IMuricService;
import co.com.muric.web.rest.MuricController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import static org.springframework.http.HttpStatus.*;

@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MuricControllerTest {

    private static final String SOURCE_PATH = "/Users/kristianhdez/Desktop/Mapa funcional de variables.xlsx";
    private static final String TYPE_FILE = "FILE";
    private static final String TEST_SOURCE = "testSource";
    private static final String GENERIC_VALID_TYPE =  "validType";
    private static final String GENERIC_INVALID=  "invalid";
    private static final String GENERIC_VALID_SOURCE = "validSource";
    private static final String GENERIC_SOURCE_PATH = "sourcePath";
    private static final String INVALID_TYPE = "INVALID_TYPE";
    private static final String EMPTY_TYPE = "";
    private static final String EMPTY_SOURCE = "";
    private static final String EMPTY_BLANK_SOURCE = "   ";
    private static final String SOME_SOURCE = "someSource";
    private static final String SPECIFIC_TYPE = "SPECIFIC_TYPE";

    @Mock
    private IMuricService muricService;

    @InjectMocks
    private MuricController muricController;

    @Test
    public void testGenerateAvro_Success() {
        when(muricService.generateAvro(SOURCE_PATH, TYPE_FILE)).thenReturn(MuricResponseDTO.builder().build());
        ResponseEntity<?> response = muricController.generateAvro(SOURCE_PATH, TYPE_FILE);
        assertEquals(OK, response.getStatusCode());
    }

    @Test
    public void testGenerateAvro_EmptySourceAndType() {
        ResponseEntity<?> response = muricController.generateAvro(EMPTY_SOURCE, EMPTY_TYPE);
        MuricResponseDTO responseBody = (MuricResponseDTO) response.getBody();
        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
    }

    @Test
    public void testGenerateAvro_NullSource() {
        ResponseEntity<?> response = muricController.generateAvro(null, TYPE_FILE);
        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
    }

    @Test
    public void testGenerateAvro_CustomExceptionHandling() {
        ResponseEntity<?> response = muricController.generateAvro(GENERIC_SOURCE_PATH, GENERIC_VALID_TYPE);
        MuricResponseDTO responseBody = (MuricResponseDTO) response.getBody();
        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
        assertTrue(responseBody.getResposeMessage().contains(GENERIC_VALID_TYPE));
    }

    @Test
    public void testGetHealth() {
        ResponseEntity<?> response = muricController.getHealth();
        assertEquals(OK, response.getStatusCode());
        assertEquals(200, response.getBody());
    }

}
