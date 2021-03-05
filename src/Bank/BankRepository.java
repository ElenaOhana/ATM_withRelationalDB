package Bank;

import DB.JDBC.JDBConnector;

import java.sql.*;

public class BankRepository implements IBankRepository {
    JDBConnector connector;
    final String queryTempInsertBank = "INSERT INTO `banks` (`name`) VALUES (?)";
    final String queryTempSelectByName = "SELECT * FROM `banks` WHERE `name` = ?";
    final String getQueryTempSelectById = "SELECT * FROM `banks` WHERE `id` = ?";

    public BankRepository(JDBConnector connector) {
        this.connector =connector;
    }

    @Override
    public IBank createBank(String bankName) throws SQLException
    {
        Connection conn = connector.getConnection();
        int id=0;
        try(PreparedStatement ps = conn.prepareStatement(queryTempInsertBank, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, bankName);//
            int row = ps.executeUpdate();
            if(row==0)
                throw new SQLException("Creating user failed, no rows affected.");
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);//Statement.RETURN_GENERATED_KEYS helps me to check the query
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        return new Bank(id, bankName);
    }

    @Override
    public IBank getBankByName(String name) throws SQLException {
        Connection conn = connector.getConnection();
        try(PreparedStatement ps = conn.prepareStatement(queryTempSelectByName)){
            ps.setString(1, name);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return new Bank(resultSet.getInt("id"), resultSet.getString("name"));
                }
            }
        }
        return null;
    }

    @Override
    public IBank getBankById(int id) throws SQLException {
        Connection conn = connector.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(getQueryTempSelectById)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return new Bank(resultSet.getInt("id"), resultSet.getString("name"));
                }
            }
        }
        return null;
    }
}
