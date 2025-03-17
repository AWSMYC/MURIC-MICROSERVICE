package co.com.muric.infrastructure.api.implement;

import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.entities.util.StaticVariables;
import co.com.muric.infrastructure.api.interfaces.ISuperintendenciaAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SuperintendenciaAPIImpl implements ISuperintendenciaAPI {

    private static final Logger logger = LogManager.getLogger(SuperintendenciaAPIImpl.class);
    
    @Override
    public MuricResponseDTO sendAvro(Object avro) {
        String authToken = null;
        Integer responseCode = null;
        try {
            String jsonBody = new ObjectMapper().writeValueAsString(avro);
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(jsonBody, mediaType);
            Request request = new Request.Builder()
                    .url(StaticVariables.AVRO_API_URL)
                    .post(requestBody)
                    .addHeader("Content-Type", "application/json")
                    .build();
            try (Response response = new OkHttpClient().newCall(request).execute()) {
                responseCode = response.code();
                if (responseCode == 200 || responseCode == 201) {
                    String responseBody = response.body() != null ? response.body().string() : "";
                    if (!responseBody.isEmpty()) {
                        JsonNode node = new ObjectMapper().readTree(responseBody);
                        authToken = node.textValue();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error al intentar generar la autenticación: {}", e.getMessage(), e);
        }
        return MuricResponseDTO.builder()
                .resposeCode(responseCode)
                .resposeMessage(authToken)
                .build();
    }

    @Override
    public MuricResponseDTO superFinancieraAuth() {
        String authToken = null;
        Integer responseCode = null;
        try {
            String jsonBody = String.format("{\"usuario\":\"%s\",\"contrasena\":\"%s=\"}", StaticVariables.USER_NAME, StaticVariables.PASSWORD);
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(jsonBody, mediaType);
            Request request = new Request.Builder()
                    .url(StaticVariables.AUTH_API_URL)
                    .post(requestBody)
                    .addHeader("Content-Type", "application/json")
                    .build();
            try (Response response = new OkHttpClient().newCall(request).execute()) {
                responseCode = response.code();
                if (responseCode == 200 || responseCode == 201) {
                    String responseBody = response.body() != null ? response.body().string() : "";
                    if (!responseBody.isEmpty()) {
                        JsonNode node = new ObjectMapper().readTree(responseBody);
                        authToken = node.textValue();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error al intentar generar la autenticación: {}", e.getMessage(), e);
        }
        return MuricResponseDTO.builder()
                .resposeCode(responseCode)
                .resposeMessage(authToken)
                .build();
    }

}