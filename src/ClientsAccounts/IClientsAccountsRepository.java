package ClientsAccounts;

import Accounts.IAccount;
import Bank.IBank;
import Users.IUser;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IClientsAccountsRepository
{
    List<IAccount> getListAccountsByClient(IUser client);
    List<IUser> getListUsersByAccount(IAccount account);

    List<IAccount> getListAccountsByClient(int idClient);
    List<IUser> getListUsersByAccount(int idAccount) throws SQLException;

    Pair<IUser, IAccount> createUserWithAccount(String name, String surname, IBank bank) throws SQLException;
    Pair<IUser, IAccount> createUserWithAccount(String name, String surname, IBank bank, Connection conn) throws SQLException;
}
