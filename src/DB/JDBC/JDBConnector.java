package DB.JDBC;

import java.sql.Connection;
import java.sql.SQLException;

public interface JDBConnector extends AutoCloseable {
    Connection getConnection() throws SQLException;
}
