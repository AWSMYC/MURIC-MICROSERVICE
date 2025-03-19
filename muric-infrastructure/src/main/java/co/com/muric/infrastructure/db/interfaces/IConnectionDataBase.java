package co.com.muric.infrastructure.db.interfaces;

import co.com.muric.entities.model.database.DataSource;
import co.com.muric.entities.model.database.ResultSetModel;

import java.util.List;

public interface IConnectionDataBase {
    ResultSetModel executeQuery(DataSource dataSource);
}
