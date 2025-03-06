package co.com.muric.web.rest;

import co.com.muric.entities.util.StaticVariables;
import co.com.muric.usecase.interfaces.IMuricService;
import co.com.muric.web.util.ValidateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.MessageFormat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = StaticVariables.CROSSORIGINS, allowedHeaders = StaticVariables.ALLOWHEADERS)
@RequestMapping(value = StaticVariables.BASE_REST_PATH, produces = APPLICATION_JSON_VALUE)
public class MuricController {

    private final IMuricService muricService;

    public MuricController(IMuricService muricService) {
        this.muricService = muricService;
    }

    @GetMapping(value = StaticVariables.GENERATE_AVRO_REST_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> generateAvro(@RequestParam(name = StaticVariables.SOURCE_REQUESTPARAM, required = false) String source,
                                          @RequestParam(name = StaticVariables.TYPE_REQUESTPARAM, required = false) String type) {
        try {
            if (ValidateRequest.isInvalidType(type)) {
                return ValidateRequest.buildErrorResponse(HttpStatus.BAD_REQUEST, StaticVariables.INVALID_TYPE_PARAM);
            }
            if (ValidateRequest.isInvalidTypeInputData(type)) {
                return ValidateRequest.buildErrorResponse(HttpStatus.BAD_REQUEST,
                        MessageFormat.format(StaticVariables.INVALID_TYPE_INPUT_DATA, type));
            }
            if (ValidateRequest.isFileTypeAndSourceBlank(type, source)) {
                return ValidateRequest.buildErrorResponse(HttpStatus.BAD_REQUEST, StaticVariables.INVALID_SOURCE_PARAM);
            }
            return ResponseEntity.ok(muricService.generateAvro(source, type));
        } catch (Exception e) {
            return ValidateRequest.handleException(e, type, source);
        }
    }

    @GetMapping(value = StaticVariables.HEALHT_CHECK_AVRO_REST_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHealth() {
        return ResponseEntity.ok(200);
    }

}
