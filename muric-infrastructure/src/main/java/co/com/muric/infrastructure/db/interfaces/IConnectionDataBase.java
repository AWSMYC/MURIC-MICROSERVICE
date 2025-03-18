package co.com.muric.infrastructure.db.interfaces;

import co.com.muric.entities.model.database.DataSource;

import java.sql.ResultSet;
import java.util.List;

public interface IConnectionDataBase {
    List<ResultSet> executeQuery(DataSource dataSource);
}
