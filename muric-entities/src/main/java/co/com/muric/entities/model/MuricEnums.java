package co.com.muric.entities.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MuricEnums {
    private String type;
    private String name;
    private List<String> symbols;
}