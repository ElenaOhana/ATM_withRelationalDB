package Bank;

import Accounts.AccountRepository;
import Accounts.IAccountRepository;
import DB.JDBC.ConnectorMariaDb;
import DB.JDBC.JDBConnector;
import Users.UserRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class BankRepositoryTest {
    @BeforeClass
    public static void connectionBeforeClass() {
        // create our mysql database connection
        String host = "localhost";
        String dbname = "atm";
        String username = "root";
        String password = "Kinoshka12";

        try (JDBConnector connector = new ConnectorMariaDb(host, dbname, username, password)) {
            BankRepository bankService = new BankRepository(connector);
            connector.getConnection();
            UserRepository repoUser = new UserRepository(connector);
            IAccountRepository repoAccount = new AccountRepository(connector);
            IBank bank = new Bank(10, "leumi");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createBank() {
       /* Connection conn = connector.getConnection();
        int id=0;
        try(PreparedStatement ps = conn.prepareStatement(queryTempInsertBank, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, bankName);//
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
        return new Bank(id, bankName);*/
    }
    @Test
    public void getBankByName() {
    }
    @Test
    public void getBankById() {
    }
}