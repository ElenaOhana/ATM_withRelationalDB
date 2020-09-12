import Accounts.AccountRepository;
import Accounts.IAccount;
import Accounts.IAccountRepository;
import Bank.BankServiceJDBC;
import Bank.IBank;
import DB.JDBC.ConnectorMariaDb;
import DB.JDBC.JDBConnector;
import Users.IUser;
import Users.UserRepositoryJDBC;

import java.sql.SQLException;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        // create our mysql database connection
        String host = "localhost";
        String dbname = "atm";
        String username = "root";
        String password = "Kinoshka12";

        try(JDBConnector connector = new ConnectorMariaDb(host, dbname, username, password))
        {
            BankServiceJDBC bankService = new BankServiceJDBC(connector);
            UserRepositoryJDBC repoUser =new UserRepositoryJDBC(connector);
            IAccountRepository repoAccount = new AccountRepository(connector);

            /*IUser user = userService.getUserById(2);
            user = userService.getUserById(99);
            user = userService.getUserById(2);*/
            //IBank bank = bankService.getBankById(10);
            //repoAccount.createAccount(null,1000,bank );
            //repoAccount.createAccount("no-name",10000,bank );
            IAccount account = repoAccount.getAccountById(2);
            IBank bank = bankService.getBankById(account.getBankId());
            List<IUser> listUsers = repoUser.getListUserByBankId(bank.getBankID());
            repoUser.deleteUser(listUsers.get(0));
            //System.out.println(bank.getBankName());

            //List<IUser> listUser = userService.getListUserByBankId(bank.getBankID());
            //userService.createUser("10","10",bank);
            //userService.createUser("20","20",bank);
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
