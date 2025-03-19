package co.com.muric.usecase.util.test;

import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.usecase.util.ResponseFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ResponseFormatTest {

    @Test
    public void testCreateSuccessResponse() {
        String message = "Operation successful";
        MuricResponseDTO response = ResponseFormat.createSuccessResponse(message);
        assertThat(response).isNotNull();
        assertThat(response.getResposeCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getResponseType()).isEqualTo(HttpStatus.OK.toString());
        assertThat(response.getResposeMessage()).isEqualTo(message);
    }

    @Test
    public void testCreateErrorResponse() {
        String message = "Internal server error";
        MuricResponseDTO response = ResponseFormat.createErrorResponse(message);
        assertThat(response).isNotNull();
        assertThat(response.getResposeCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getResponseType()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        assertThat(response.getResposeMessage()).isEqualTo(message);
    }
}
