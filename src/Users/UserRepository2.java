package Users;

import Bank.IBank;
import DB.JDBC.JDBConnector;

import java.sql.*;
import java.util.Date;
import java.util.List;
//atm2 - transaction(both methods: createUser(...) & insertClientToBank(....))
public class UserRepository2 implements IUserRepository
{
    final static private String insertClient = "INSERT INTO `client` (`first_name`,`last_name`) VALUES (?,?)";
    final static private String insertClientBank = "INSERT INTO `bank_client` (`bank_id`,`client_id`) VALUES (?,?)";
    JDBConnector connector;
    public UserRepository2(JDBConnector connector) {
        this.connector = connector;
    }

    private void insertClientToBank(Connection conn, Savepoint save, int clientID, int bankID) throws SQLException
    {
        try(PreparedStatement insertClientBank = conn.prepareStatement(UserRepository2.insertClientBank))// why doesn't need the second param *Statement.RETURN_GENERATED_KEYS*?
        {
            insertClientBank.setInt(1,bankID);
            insertClientBank.setInt(2,clientID);
            int row = insertClientBank.executeUpdate();
            if(row==0)
            {
                conn.rollback(save);
            }
        }
    }

    @Override
    public IUser createUser(String name, String surname, IBank bank, Connection connection) throws SQLException
    {
        Connection conn = connector.getConnection();
        conn.setAutoCommit(false);
        int id=0;
        try
        {
            Savepoint save1 = conn.setSavepoint();
            try(PreparedStatement insertClient = conn.prepareStatement(UserRepository2.insertClient, Statement.RETURN_GENERATED_KEYS))
            {
                insertClient.setString(1,name);
                insertClient.setString(2, surname);

                int row = insertClient.executeUpdate();
                if(row==0)
                {
                    throw new SQLException("Creating user failed, no rows affected.");
                }
                try (ResultSet generatedKeys = insertClient.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        id = generatedKeys.getInt(1);
                        insertClientToBank(conn, save1, id, bank.getBankID());
                        conn.commit();
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw new SQLException(e.getMessage());
        }
        finally {
            conn.setAutoCommit(true);
        }

        return null;
    }

    @Override
    public List<IUser> getListUserByBankId(int bankId) throws SQLException {
        return null;
    }

    @Override
    public IUser getUserById(int userId) throws SQLException {
        return null;
    }

    @Override
    public void deleteUser(IUser user) throws SQLException {

    }
}
