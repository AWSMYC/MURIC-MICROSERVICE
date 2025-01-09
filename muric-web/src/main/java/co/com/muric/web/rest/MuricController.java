package co.com.muric.web.rest;

import co.com.muric.usecase.interfaces.IMuricService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/muric", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MuricController {
    private IMuricService muricService;
    @GetMapping(value = "/generate-avro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> generateAvro(@RequestParam(name = "source") String source) {
        try {
            if (source == null || source.isBlank()) {
                return ResponseEntity.badRequest().body("El parámetro 'source' no puede estar vacío.");
            }
            return ResponseEntity.ok(muricService.generateAvro(source));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error procesando la solicitud: " + e.getMessage());
        }
    }

    @GetMapping(value = "/health-check", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getHealth() {
        return ResponseEntity.ok(200);
    }
}