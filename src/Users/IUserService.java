package Users;

import Bank.IBank;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public interface IUserService {
    IUser createUser(String name, String surname, IBank bank) throws SQLException;
}
