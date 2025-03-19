package co.com.muric.infrastructure.db.implement;

import co.com.muric.entities.model.database.DataSource;
import co.com.muric.entities.model.database.ResultSetModel;
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
    private static final int THREAD_POOL_SIZE = 10;

    @Override
    public List<ResultSetModel> executeQuery(DataSource dataSource) {
        List<ResultSetModel> resultSetModelList = new ArrayList<>();
        List<Future<ResultSetModel>> futures = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (String tableName : dataSource.getTableNameList()) {
            Callable<ResultSetModel> task = () -> {
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
                    logger.info(MessageFormat.format(StaticVariables.SELECT_TABLE_EXECUTE_SUCCESS, tableName));
                    return new ResultSetModel(tableName, resultSet); // Devuelve el modelo con la tabla y el resultado
                } catch (Exception e) {
                    logger.error(MessageFormat.format(StaticVariables.SELECT_TABLE_EXECUTE_ERROR, tableName, e.getMessage()));
                    return null;
                }
            };
            futures.add(executorService.submit(task));
        }

        for (Future<ResultSetModel> future : futures) {
            try {
                ResultSetModel resultSetModel = future.get();
                if (resultSetModel != null) {
                    resultSetModelList.add(resultSetModel);
                }
            } catch (InterruptedException | ExecutionException e) {
                logger.error(MessageFormat.format(StaticVariables.SELECT_TABLE_EXECUTE_GENERIC_ERROR, e.getMessage()));
            }
        }

        executorService.shutdown();
        return resultSetModelList;
    }
}
