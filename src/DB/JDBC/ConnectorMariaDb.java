package DB.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorMariaDb implements JDBConnector {
    String url;
    String login;
    String password;

    Connection connection;

    public ConnectorMariaDb(String host, String dbName, String login, String password) throws ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver"); // Ensure we have mariaDB Driver in classpath
        this.url = "jdbc:mariadb://" + host + "/" + dbName;
        this.login = login;
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connection==null)
        {
            connection = DriverManager.getConnection(url, login, password);
        }
        return connection;
    }

    @Override
    public Connection getNewConnection() throws SQLException {
        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
