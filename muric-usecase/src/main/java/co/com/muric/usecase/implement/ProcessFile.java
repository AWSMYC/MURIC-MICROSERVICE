package co.com.muric.usecase.implement;

import co.com.muric.entities.dto.Avro;
import co.com.muric.entities.model.MuricField;
import co.com.muric.entities.util.InformacionCredito;
import co.com.muric.usecase.util.FileDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessFile {

    private static final Logger logger = LogManager.getLogger(ProcessFile.class);

    public static Avro generateAvroFromFiles(String source) throws IOException {
        try {
            FileDataSource.readSheetInformacionCredito(source);
            FileDataSource.readSheetAtributoCreditoDeuda(source);
            FileDataSource.readSheetMovimientoCartera(source);

        } catch (IOException io) {
            logger.error("Error al procesar el archivo: " + io.getMessage(), io);
        }
        return Avro.builder()
                .build();
    }

    public static Avro generateAvroFromDataBase() {
        //muricRepository.findData();
        //superintendenciaAPI.sendAvro(Avro.builder().build());
        return Avro.builder().build();
    }

    private static Avro avroMapper(List<InformacionCredito> fileDataList) {
        List<Object> creditoFields = new ArrayList<>();
        List<Object> movimientoFields = new ArrayList<>();
        List<Object> demograficoFields = new ArrayList<>();
        return Avro.builder()
                .type(null)
                .name(null)
                .tipoEntidad(null)
                .codigoEntidad(null)
                .fechaCorte(MuricField.MuricFieldNameTypeSubType.builder()
                        .name(null)
                        .type(MuricField.MuricFieldNameTypeSubType.MuricFieldLogicalType.builder().build())
                        .build())
                .fechaGeneracion(MuricField.MuricFieldNameTypeSubType.builder()
                        .name(null)
                        .type(MuricField.MuricFieldNameTypeSubType.MuricFieldLogicalType.builder().build())
                        .build())
                .comentarios(null)
                .firma(null)
                .palabraClave(null)
                .creditoFields(creditoFields)
                .movimientoFields(movimientoFields)
                .demograficoFields(demograficoFields)
                .build();
    }
}
