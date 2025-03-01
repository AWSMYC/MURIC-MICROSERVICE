package co.com.muric.web.rest;

import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.entities.util.StaticVariables;
import co.com.muric.usecase.implement.MuricServiceImpl;
import co.com.muric.usecase.interfaces.IMuricService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.MessageFormat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = StaticVariables.CROSSORIGINS, allowedHeaders = StaticVariables.ALLOWHEADERS)
@RequestMapping(value = StaticVariables.BASE_REST_PATH, produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MuricController {

    @Autowired
    private IMuricService muricService;

    @GetMapping(value = StaticVariables.GENERATE_AVRO_REST_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> generateAvro(@RequestParam(name = StaticVariables.SOURCE_REQUESTPARAM, required = false) String source,
                                          @RequestParam(name = StaticVariables.TYPE_REQUESTPARAM, required = false) String type) {
        try {
            if (type.isBlank()) {
               return ResponseEntity.badRequest().body(MuricResponseDTO.builder()
                       .resposeCode(HttpStatus.BAD_REQUEST.value())
                       .responseType(HttpStatus.BAD_REQUEST.toString())
                       .resposeMessage(StaticVariables.INVALID_TYPE_PARAM)
                       .build());
            } else if(type.equalsIgnoreCase(StaticVariables.SOURCE_FILE) && source.isBlank()){
               return ResponseEntity.badRequest().body(MuricResponseDTO.builder()
                       .resposeCode(HttpStatus.BAD_REQUEST.value())
                       .responseType(HttpStatus.BAD_REQUEST.toString())
                       .resposeMessage(StaticVariables.INVALID_SOURCE_PARAM)
                       .build());
            } else {
                return ResponseEntity.ok(muricService.generateAvro(source, type));
            }
        } catch (Exception e) {
            if (null==type && null==source){
                return ResponseEntity.badRequest().body(MuricResponseDTO.builder()
                        .resposeCode(HttpStatus.BAD_REQUEST.value())
                        .responseType(HttpStatus.BAD_REQUEST.toString())
                        .resposeMessage(StaticVariables.INVALID_SOURCE_TYPE_PARAM)
                        .build());
            }
            if (e.getMessage().equalsIgnoreCase(StaticVariables.SOURCE_BLANK)) {
                return ResponseEntity.badRequest().body(MuricResponseDTO.builder()
                        .resposeCode(HttpStatus.BAD_REQUEST.value())
                        .responseType(HttpStatus.BAD_REQUEST.toString())
                        .resposeMessage(StaticVariables.INVALID_SOURCE_PARAM)
                        .build());
            }
            if (e.getMessage().equalsIgnoreCase(StaticVariables.TYPE_BLANK)) {
                return ResponseEntity.badRequest().body(MuricResponseDTO.builder()
                        .resposeCode(HttpStatus.BAD_REQUEST.value())
                        .responseType(HttpStatus.BAD_REQUEST.toString())
                        .resposeMessage(StaticVariables.INVALID_TYPE_PARAM)
                        .build());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MuricResponseDTO.builder()
                            .resposeCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .responseType(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                            .resposeMessage(MessageFormat.format(StaticVariables.PROCESS_GENERIC_ERROR,e))
                            .build());
        }
    }

    @GetMapping(value = StaticVariables.HEALHT_CHECK_AVRO_REST_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getHealth() {
        return ResponseEntity.ok(200);
    }
}