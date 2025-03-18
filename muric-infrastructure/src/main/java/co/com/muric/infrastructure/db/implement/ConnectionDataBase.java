package co.com.muric.infrastructure.db.implement;

import co.com.muric.entities.model.database.DataSource;
import co.com.muric.entities.util.StaticVariables;
import co.com.muric.infrastructure.db.interfaces.IConnectionDataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ConnectionDataBase implements IConnectionDataBase {

    private static final Logger logger = LogManager.getLogger(ConnectionDataBase.class);
    private static final int THREAD_POOL_SIZE = 10; // Ajusta según necesidad

    @Override
    public List<ResultSet> executeQuery(DataSource dataSource) {
        List<ResultSet> resultSets = new ArrayList<>();
        List<Future<ResultSet>> futures = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (String tableName : dataSource.getTableNameList()) {
            Callable<ResultSet> task = () -> {
                String sql = MessageFormat.format(StaticVariables.SELECT_TABLE, dataSource.getSchema(), tableName);
                try (Connection connection = DriverManager.getConnection(dataSource.getHost(), dataSource.getUser(), dataSource.getPassword());
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        if(tableName.equalsIgnoreCase("informacion_creditos")) {
                            String codigo_producto = resultSet.getString("codigo_producto");
                            System.out.println("---------------" + codigo_producto + "----------------");
                        } else if(tableName.equalsIgnoreCase("atributos_creditos")) {
                            String valor_atributo = resultSet.getString("valor_atributo");
                            System.out.println("---------------" + valor_atributo + "----------------");
                        } else if(tableName.equalsIgnoreCase("movimientos_cartera")) {
                            String estado = resultSet.getString("estado");
                            System.out.println("---------------" + estado + "----------------");
                        }
                    }
                    logger.info("Consulta ejecutada en tabla: " + tableName);
                    return resultSet;
                } catch (Exception e) {
                    logger.error("Error al ejecutar consulta en tabla: " + tableName, e);
                    return null;
                }
            };
            futures.add(executorService.submit(task));
        }

        for (Future<ResultSet> future : futures) {
            try {
                ResultSet resultSet = future.get(); // Espera que termine cada consulta
                if (resultSet != null) {
                    resultSets.add(resultSet);
                }
            } catch (InterruptedException | ExecutionException e) {
                logger.error("Error al obtener resultado de consulta", e);
            }
        }

        executorService.shutdown();
        return resultSets;
    }
}
