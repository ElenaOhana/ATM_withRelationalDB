package Users;

import Bank.IBank;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IUserRepository {
    IUser createUser(String name, String surname, IBank bank, Connection connection) throws SQLException;
    List<IUser> getListUserByBankId(int bankId) throws SQLException;
    IUser getUserById(int userId) throws SQLException;
    void deleteUser(IUser user) throws SQLException;
}
