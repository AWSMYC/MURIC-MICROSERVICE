package co.com.muric.web.test;

import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.usecase.interfaces.IMuricService;
import co.com.muric.web.rest.MuricController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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
        assertEquals(BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGenerateAvro_ExceptionHandling() {
        String source = "testSource";
        String type = "validType";
        ResponseEntity<?> response = muricController.generateAvro(source, type);
        assertEquals(BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetHealth() {
        ResponseEntity<?> response = muricController.getHealth();
        assertEquals(OK, response.getStatusCode());
    }
}
