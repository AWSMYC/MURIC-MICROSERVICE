package co.com.muric.entities.model.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class DataSource {
    private String host;
    private String user;
    private String password;
    private String database;
    private String schema;
    private List<String> tableNameList;
}