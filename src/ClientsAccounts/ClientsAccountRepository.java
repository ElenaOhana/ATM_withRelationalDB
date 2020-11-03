package ClientsAccounts;

import Accounts.IAccount;
import Accounts.IAccountRepository;
import Bank.IBank;
import DB.JDBC.JDBConnector;
import Users.IUser;
import Users.IUserRepository;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

public class ClientsAccountRepository implements IClientsAccountsRepository
{
    IAccountRepository accounts;
    IUserRepository users;
    JDBConnector connector;
    final static private String insertClientAccountTemp = "INSERT INTO `accounts_clients` (`account_id`,`client_id`) VALUES (?,?)";

    public ClientsAccountRepository(IAccountRepository accounts, IUserRepository users, JDBConnector connector)
    {
        this.accounts = accounts;
        this.users = users;
        this.connector = connector;
    }

    //transaction
    private void insertClientToAccount(Connection conn, int clientId, int accountId) throws SQLException {
        try (PreparedStatement insertClientAccount = conn.prepareStatement(insertClientAccountTemp))
        {
            insertClientAccount.setInt(1, clientId);
            insertClientAccount.setInt(2, accountId);
            int row = insertClientAccount.executeUpdate();
            if (row == 0) {
                throw new SQLException("error insert to accounts_clients");
            }
        }
    }

    @Override
    public List<IAccount> getListAccountsByClient(IUser client) {
        return null;
    }

    @Override
    public List<IUser> getListUsersByAccount(IAccount account) {
        return null;
    }

    @Override
    public List<IAccount> getListAccountsByClient(int idClient) {
        return null;
    }

    @Override
    public List<IUser> getListUsersByAccount(int idAccount) throws SQLException
    {
        return null;
    }

    @Override
    public Pair<IUser, IAccount> createUserWithAccount(String name, String surname, IBank bank) throws SQLException {
        Connection conn = connector.getConnection();
        return createUserWithAccount(name, surname, bank, conn);
    }

    @Override
    // For multithreading
    public Pair<IUser, IAccount> createUserWithAccount(String name, String surname, IBank bank, Connection conn) throws SQLException {
        conn.setAutoCommit(false);
        IUser user = null;
        IAccount account = null;
        Savepoint savePoint = null;
        try
        {
            savePoint = conn.setSavepoint();
            user = users.createUser(name,surname,bank,conn);
            account = accounts.createAccount(null,0,bank,conn);
            insertClientToAccount(conn, user.getUserId(), account.getAccountNumber());
            conn.commit();
        }
        catch (Exception e)
        {
            conn.rollback(savePoint);
            throw new SQLException(e.getMessage());
        }
        finally
        {
            conn.setAutoCommit(true);
        }
        return new Pair<>(user, account);
    }
}
