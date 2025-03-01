package co.com.muric.web.rest;

import co.com.muric.entities.util.StaticVariables;
import co.com.muric.usecase.interfaces.IMuricService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/muric", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MuricController {
    private IMuricService muricService;
    @GetMapping(value = "/generateAvro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> generateAvro(@RequestParam(name = "source", required = false) String source, @RequestParam(name = "type", required = false) String type) {
        try {
            if (type.isBlank()) {
               return ResponseEntity.badRequest().body(StaticVariables.TYPE_INVALID_TYPE_PARAM);
            } else if(type.equalsIgnoreCase(StaticVariables.SOURCE_FILE) && source.isBlank()){
               return ResponseEntity.badRequest().body(StaticVariables.SOURCE_INVALID_TYPE_PARAM);
            } else {
                return ResponseEntity.ok(muricService.generateAvro(source, type));
            }
        } catch (Exception e) {
            if (null==type && null==source){
                return ResponseEntity.badRequest().body(StaticVariables.SOURCE_TYPE_INVALID_PARAM);
            }
            if (e.getMessage().equalsIgnoreCase(StaticVariables.SOURCE_BLANK)) {
                return ResponseEntity.badRequest().body(StaticVariables.SOURCE_INVALID_TYPE_PARAM);
            }
            if (e.getMessage().equalsIgnoreCase(StaticVariables.TYPE_BLANK)) {
                return ResponseEntity.badRequest().body(StaticVariables.TYPE_INVALID_TYPE_PARAM);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MessageFormat.format(StaticVariables.PROCESS_FAIL_ERROR,e));
        }
    }

    @GetMapping(value = "/health-check", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getHealth() {
        return ResponseEntity.ok(200);
    }
}