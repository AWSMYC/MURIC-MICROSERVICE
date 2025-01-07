package co.com.muric.infrastructure.api.implement;

import co.com.muric.entities.dto.Avro;
import co.com.muric.entities.dto.MuricResponseDTO;
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
    private static final String AUTH_API_URL = "https://apidev.superfinanciera.gov.co/v2/services/auth";
    private static final String AVRO_API_URL = "https://apidev.superfinanciera.gov.co/v2/services/muric";
    private static final String USER_NAME = "nombre_usuario";
    private static final String PASSWORD = "SmXpcm8wLjE5Nzgk";

    @Override
    public MuricResponseDTO sendAvro(Avro avro) {
        String authToken = null;
        Integer responseCode = null;
        try {
            String jsonBody = new ObjectMapper().writeValueAsString(avro);
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(jsonBody, mediaType);
            Request request = new Request.Builder()
                    .url(AVRO_API_URL)
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
                .codeRespose(responseCode)
                .msgRespose(authToken)
                .build();
    }

    @Override
    public MuricResponseDTO superFinancieraAuth() {
        String authToken = null;
        Integer responseCode = null;
        try {
            String jsonBody = String.format("{\"usuario\":\"%s\",\"contrasena\":\"%s=\"}", USER_NAME, PASSWORD);
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(jsonBody, mediaType);
            Request request = new Request.Builder()
                    .url(AUTH_API_URL)
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
                .codeRespose(responseCode)
                .msgRespose(authToken)
                .build();
    }

}