package co.com.muric.entities.model.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class MuricField {

    @Getter
    @Setter
    @Builder(toBuilder = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MuricFieldNameType {
        private String name;
        private String type;
    }

    @Getter
    @Setter
    @Builder(toBuilder = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MuricFieldNameTypeSubType {
        private String name;
        private MuricFieldLogicalType type;
        @Getter
        @Setter
        @Builder(toBuilder = true)
        @JsonIgnoreProperties(ignoreUnknown = true)
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MuricFieldLogicalType {
            private String type;
            private Date logicalType;
        }
    }

    @Getter
    @Setter
    @Builder(toBuilder = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MuricFieldTypeSymbol {
        private String name;
        private MuricFieldNameTypeEnum type;
        @Getter
        @Setter
        @Builder(toBuilder = true)
        @JsonIgnoreProperties(ignoreUnknown = true)
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MuricFieldNameTypeEnum {
            private String type;
            private String name;
            private List<String> symbols;
        }
    }

}