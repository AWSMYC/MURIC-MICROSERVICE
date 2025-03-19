package co.com.muric.infrastructure.db.implement;

import co.com.muric.entities.model.database.DataSource;
import co.com.muric.entities.model.database.ResultSetModel;
import co.com.muric.entities.model.excel.AtributoCreditoDeuda;
import co.com.muric.entities.model.excel.InformacionCredito;
import co.com.muric.entities.model.excel.MovimientoCartera;
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
    public ResultSetModel executeQuery(DataSource dataSource) {
        List<InformacionCredito> informacionCreditoList = new ArrayList<>();
        List<AtributoCreditoDeuda > atributoCreditoDeudaList = new ArrayList<>();
        List< MovimientoCartera > movimientoCarteraList = new ArrayList<>();
        for (String tableName : dataSource.getTableNameList()) {
            String sql = MessageFormat.format(StaticVariables.SELECT_TABLE, dataSource.getSchema(), tableName);
            try (Connection connection = DriverManager.getConnection(dataSource.getHost(), dataSource.getUser(), dataSource.getPassword());
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    switch (tableName) {
                        case StaticVariables.INFORMACION_CREDITO_ENTIDAD_TABLE_NAME:
                            informacionCreditoList.add(InformacionCredito.builder().build());
                            String codigo_producto = resultSet.getString("codigo_producto");
                            System.out.println("---------------" + codigo_producto + "----------------");
                            break;
                        case StaticVariables.ATRIBUTOS_CREDITOS_TABLE_NAME:
                            atributoCreditoDeudaList.add(AtributoCreditoDeuda.builder().build());
                            String valor_atributo = resultSet.getString("valor_atributo");
                            System.out.println("---------------" + valor_atributo + "----------------");
                            break;
                        case StaticVariables.MOVIMIENTOS_CARTERA_TABLE_NAME:
                            movimientoCarteraList.add(MovimientoCartera.builder().build());
                            String estado = resultSet.getString("estado");
                            System.out.println("---------------" + estado + "----------------");
                            break;
                    }
                }
                logger.info(MessageFormat.format(StaticVariables.SELECT_TABLE_EXECUTE_SUCCESS, tableName));
            } catch (Exception e) {
                logger.error(MessageFormat.format(StaticVariables.RESULSET_ERROR, tableName, e.getMessage()));
                return null;
            }
        }
        return ResultSetModel.builder()
                .informacionCreditoList(informacionCreditoList)
                .atributoCreditoDeudaList(atributoCreditoDeudaList)
                .movimientoCarteraList(movimientoCarteraList)
                .build();
    }
}
