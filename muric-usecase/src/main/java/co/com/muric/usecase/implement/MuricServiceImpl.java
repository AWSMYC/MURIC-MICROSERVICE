package co.com.muric.usecase.implement;

import co.com.muric.entities.dto.Avro;
import co.com.muric.entities.dto.MuricResponseDTO;
import co.com.muric.entities.model.excel.AtributoCreditoDeuda;
import co.com.muric.entities.model.excel.InformacionCredito;
import co.com.muric.entities.model.excel.MovimientoCartera;
import co.com.muric.entities.util.StaticVariables;
import co.com.muric.infrastructure.api.interfaces.ISuperintendenciaAPI;
import co.com.muric.infrastructure.db.interfaces.IMuricRepository;
import co.com.muric.usecase.interfaces.IMuricService;
import co.com.muric.usecase.util.FileDataSource;
import co.com.muric.usecase.util.ResponseFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

@Service
public class MuricServiceImpl implements IMuricService {

    private static final Logger logger = LogManager.getLogger(MuricServiceImpl.class);

    private IMuricRepository muricRepository;
    private ISuperintendenciaAPI superintendenciaAPI;

    @Override
    public MuricResponseDTO generateAvro(String source, String type) {
        try {
            Avro avroData = null;

            switch (type.toUpperCase()) {
                case StaticVariables.TYPE_FILE:
                    avroData = processFile(source);
                    break;
                case StaticVariables.TYPE_DATABASE:
                    avroData = ProcessFile.generateAvroFromDataBase();
                    break;
                default:
                    return ResponseFormat.createErrorResponse(StaticVariables.PROCESS_GENERIC_ERROR, source);
            }
            if (avroData != null) {
                return ResponseFormat.createSuccessResponse(type.equals(StaticVariables.TYPE_FILE) ? StaticVariables.PROCESS_FILE_OK : StaticVariables.PROCESS_DATABASE_OK);
            } else {
                return ResponseFormat.createErrorResponse(type.equals(StaticVariables.TYPE_FILE) ? StaticVariables.PROCESS_FILE_ERROR : StaticVariables.PROCESS_DATABASE_ERROR, source);
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format(StaticVariables.PROCESS_GENERIC_ERROR, e));
            return ResponseFormat.createErrorResponse(MessageFormat.format(StaticVariables.PROCESS_GENERIC_ERROR, e), null);
        }
    }

    private Avro processFile(String source) throws IOException {
        List<InformacionCredito> InformacionCreditoList = FileDataSource.readSheetInformacionCredito(source);
        List<AtributoCreditoDeuda> atributoCreditoDeudaList = FileDataSource.readSheetAtributoCreditoDeuda(source);
        List<MovimientoCartera> movimientoCarteraList = FileDataSource.readSheetMovimientoCartera(source);
        return ProcessFile.avroMapper(InformacionCreditoList, atributoCreditoDeudaList, movimientoCarteraList);
    }

}