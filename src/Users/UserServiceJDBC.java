package Users;

import Bank.IBank;
import DB.JDBC.JDBConnector;

import java.sql.*;

public class UserServiceJDBC implements IUserService {

    JDBConnector connector;
    final String queryTemplInsertclient = "INSERT INTO `clients` (`bank_id`, `first_name`,`last_name`) VALUES (?,?,?)";

    public UserServiceJDBC(JDBConnector connector) {
        this.connector = connector;
    }

    @Override
    public IUser createUser(String name, String surname, IBank bank) throws SQLException
    {
        Connection conn = connector.getConnection();
        int id=0;
        try(PreparedStatement ps = conn.prepareStatement(queryTemplInsertclient, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setInt(1, bank.getBankID());
            ps.setString(2,name);
            ps.setString(3,surname);
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

        return new User (name, surname, id, bank.getBankID());
    }
}
