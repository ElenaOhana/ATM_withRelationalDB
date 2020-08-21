package Bank;

import DB.JDBC.JDBConnector;

import java.sql.*;

public class BankServiceJDBC implements IBankService {
    JDBConnector connector;
    final String queryTemplInsertBank = "INSERT INTO `banks` (`name`) VALUES (?)";
    public BankServiceJDBC(JDBConnector connector)
    {
        this.connector =connector;
    }

    @Override
    public IBank createBank(String bankName) throws SQLException
    {
        Connection conn = connector.getConnection();
        int id=0;
        try(PreparedStatement ps = conn.prepareStatement(queryTemplInsertBank, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, bankName);
            int row = ps.executeUpdate();
            if(row==0)
                throw new SQLException("Creating user failed, no rows affected.");
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }

        return new Bank(id, bankName);
    }
}
