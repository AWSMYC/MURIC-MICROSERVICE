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

    @Mock
    private IMuricService muricService;

    @InjectMocks
    private MuricController muricController;

    @Test
    public void testGenerateAvro_Success() {
        String source = "/Users/kristianhdez/Desktop/Mapa funcional de variables.xlsx";
        String type = "FILE";
        when(muricService.generateAvro(source, type)).thenReturn(MuricResponseDTO.builder().build());
        ResponseEntity<?> response = muricController.generateAvro(source, type);
        assertEquals(OK, response.getStatusCode());
    }

    @Test
    public void testGenerateAvro_InvalidType() {
        String source = "testSource";
        String type = "invalid";
        ResponseEntity<?> response = muricController.generateAvro(source, type);
        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
    }

    @Test
    public void testGenerateAvro_InvalidTypeInputData() {
        String source = "validSource";
        String type = "INVALID_TYPE";
        ResponseEntity<?> response = muricController.generateAvro(source, type);
        MuricResponseDTO responseBody = (MuricResponseDTO) response.getBody();
        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
    }

    @Test
    public void testGenerateAvro_EmptySourceAndType() {
        String source = "";
        String type = "";
        ResponseEntity<?> response = muricController.generateAvro(source, type);
        MuricResponseDTO responseBody = (MuricResponseDTO) response.getBody();
        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
    }

    @Test
    public void testGenerateAvro_HandleSpecificException() {
        String source = "testSource";
        String type = "validType";
        ResponseEntity<?> response = muricController.generateAvro(source, type);
        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
    }

    @Test
    public void testGenerateAvro_ExceptionHandling() {
        String source = "testSource";
        String type = "validType";
        ResponseEntity<?> response = muricController.generateAvro(source, type);
        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
    }

    @Test
    public void testGenerateAvro_NullSource() {
        String source = null;
        String type = "FILE";
        ResponseEntity<?> response = muricController.generateAvro(source, type);
        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
    }

    @Test
    public void testGenerateAvro_NullType() {
        String source = "validSource";
        String type = null;
        ResponseEntity<?> response = muricController.generateAvro(source, type);
        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
    }

    @Test
    public void testGenerateAvro_InvalidTypeWithSpaces() {
        String source = "validSource";
        String type = "   "; // Space as an invalid type
        ResponseEntity<?> response = muricController.generateAvro(source, type);
        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
    }

    @Test
    public void testGenerateAvro_CustomExceptionHandling() {
        String source = "sourcePath";
        String type = "validType";
        ResponseEntity<?> response = muricController.generateAvro(source, type);
        MuricResponseDTO responseBody = (MuricResponseDTO) response.getBody();
        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
        assertTrue(responseBody.getResposeMessage().contains("validType"));
    }

    @Test
    public void testGetHealth() {
        ResponseEntity<?> response = muricController.getHealth();
        assertEquals(OK, response.getStatusCode());
        assertEquals(200, response.getBody());
    }

    // Additional tests to ensure coverage for various edge cases
    @Test
    public void testGenerateAvro_SpecificValidType() {
        String source = "someSource";
        String type = "SPECIFIC_TYPE";
        ResponseEntity<?> response = muricController.generateAvro(source, type);
        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
    }
}
