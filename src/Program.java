import ATM.Abstract.IATMRepository;
import ATM.Abstract.IATMService;
import ATM.Impl.ATMDBRepository;
import ATM.Impl.ATMService;
import Bank.BankServiceJDBC;
import Bank.IBank;
import DB.JDBC.ConnectorMariaDb;
import DB.JDBC.JDBConnector;
import Users.UserServiceJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program
{
    public static void main(String[] args) {
        // create our mysql database connection
        String host = "localhost";
        String dbname = "atm";
        String username = "root";
        String password = "Kinoshka12";

        try(JDBConnector connector = new ConnectorMariaDb(host, dbname, username, password))
        {
            BankServiceJDBC bankService = new BankServiceJDBC(connector);
            UserServiceJDBC userService =new UserServiceJDBC(connector);

            IBank bank = bankService.createBank("Test bank3");
            userService.createUser("1","1",bank);
            userService.createUser("2","2",bank);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
        //IATMService atmServiceLiumi = new ATMService();
        //IATMRepository atmRepo = new ATMDBRepository();

        //Bank bank = new BankLeumi();
        //IATM atm = bank.createAtm("tama", "rd2", 10000, "LU232323");
    }
}
