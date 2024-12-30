package co.com.muric.web.rest;

import co.com.muric.usecase.implement.IMuricService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/muric", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MuricController {

    private IMuricService municService;

    @GetMapping(value = "/generate-avro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity generateAvro(@RequestParam(name = "source") String source) {
        return ResponseEntity.ok(municService.generateAvro(source));
    }

    @GetMapping(value = "/health-check", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getHealth() {
        return ResponseEntity.ok(200);
    }
}