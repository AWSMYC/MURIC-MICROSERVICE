package co.com.muric.usecase.implement;

import co.com.muric.entities.dto.Avro;
import co.com.muric.entities.model.excel.AtributoCreditoDeuda;
import co.com.muric.entities.model.excel.InformacionCredito;
import co.com.muric.entities.model.excel.MovimientoCartera;
import co.com.muric.entities.model.excel.UnifiedCreditInformation;
import co.com.muric.entities.util.StaticVariables;
import co.com.muric.infrastructure.api.interfaces.ISuperintendenciaAPI;
import co.com.muric.infrastructure.db.interfaces.IMuricRepository;
import co.com.muric.usecase.interfaces.IMuricService;
import co.com.muric.usecase.util.FileDataSource;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MuricServiceImpl implements IMuricService {

    private static final Logger logger = LogManager.getLogger(MuricServiceImpl.class);

    private final IMuricRepository muricRepository;
    private final ISuperintendenciaAPI superintendenciaAPI;

    public MuricServiceImpl(IMuricRepository muricRepository, ISuperintendenciaAPI superintendenciaAPI) {
        this.muricRepository = muricRepository;
        this.superintendenciaAPI = superintendenciaAPI;
    }

    @Override
    public List<UnifiedCreditInformation> generateAvro(String source, String type) {
        try {
            if (StaticVariables.TYPE_FILE.equalsIgnoreCase(type)) {
                return processFile(source);
            } else if (StaticVariables.TYPE_DATABASE.equalsIgnoreCase(type)) {
                Avro avroData = ProcessFile.generateAvroFromDataBase();
                if (avroData != null) {
                    // return ResponseFormat.createSuccessResponse(StaticVariables.PROCESS_DATABASE_OK);
                } else {
                    // return ResponseFormat.createErrorResponse(StaticVariables.PROCESS_DATABASE_ERROR, source);
                }
            } else {
                // return ResponseFormat.createErrorResponse(StaticVariables.PROCESS_GENERIC_ERROR, source);
            }
        } catch (Exception e) {
            logger.error("Error en generateAvro: {}", e.getMessage(), e);
            // return ResponseFormat.createErrorResponse(StaticVariables.PROCESS_GENERIC_ERROR, null);
        }
        return null;
    }

    private List<UnifiedCreditInformation> processFile(String source) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try  {
            CompletableFuture<List<InformacionCredito>> futureInformacionCredito = fetchAsync(() -> FileDataSource.readSheetInformacionCredito(source), executor);
            CompletableFuture<List<AtributoCreditoDeuda>> futureAtributoCreditoDeuda = fetchAsync(() -> FileDataSource.readSheetAtributoCreditoDeuda(source), executor);
            CompletableFuture<List<MovimientoCartera>> futureMovimientoCartera = fetchAsync(() -> FileDataSource.readSheetMovimientoCartera(source), executor);
            CompletableFuture.allOf(futureInformacionCredito, futureAtributoCreditoDeuda, futureMovimientoCartera).join();
            List<UnifiedCreditInformation> unifiedCreditInformation = ProcessFile.agruparCreditos(futureInformacionCredito.get(), futureAtributoCreditoDeuda.get(), futureMovimientoCartera.get());
            return unifiedCreditInformation;
        } catch (Exception e) {
            logger.error("Error al procesar el archivo: {}", e.getMessage(), e);
            throw new IOException("Error al procesar el archivo", e);
        }
    }

    public static <T> CompletableFuture<T> fetchAsync(DataSupplier<T> supplier, ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return supplier.get();
            } catch (IOException e) {
                throw new RuntimeException("Error al leer el archivo", e);
            }
        }, executor);
    }

    @FunctionalInterface
    private interface DataSupplier<T> {
        T get() throws IOException;
    }

}
