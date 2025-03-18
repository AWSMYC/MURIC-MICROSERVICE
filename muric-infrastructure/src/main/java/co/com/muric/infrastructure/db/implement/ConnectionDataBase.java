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
                    logger.info(MessageFormat.format(StaticVariables.SELECT_TABLE_EXECUTE_SUCCESS, tableName));
                    return resultSet;
                } catch (Exception e) {
                    logger.error(MessageFormat.format(StaticVariables.SELECT_TABLE_EXECUTE_ERROR, tableName, e));
                    return null;
                }
            };
            futures.add(executorService.submit(task));
        }
        for (Future<ResultSet> future : futures) {
            try {
                ResultSet resultSet = future.get();
                if (resultSet != null) {
                    resultSets.add(resultSet);
                }
            } catch (InterruptedException | ExecutionException e) {
                logger.error(MessageFormat.format(StaticVariables.SELECT_TABLE_EXECUTE_GENERIC_ERROR, e));
            }
        }
        executorService.shutdown();
        return resultSets;
    }

}
