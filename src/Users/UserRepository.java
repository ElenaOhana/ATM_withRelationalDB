package Users;

import Bank.IBank;
import DB.JDBC.JDBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    JDBConnector connector;
    final String queryTempInsertClient = "INSERT INTO `clients` (`bank_id`, `first_name`,`last_name`) VALUES (?,?,?)";
    final String queryTempGetListUserBankId = "SELECT * FROM `clients` WHERE `bank_id` = ?";
    final String queryTempGetUserById = "SELECT * FROM `clients` WHERE `id` = ?";
    final String queryTempDeleteUserById = "DELETE FROM `clients` WHERE `id` = ?";


    public UserRepository(JDBConnector connector) {
        this.connector = connector;
    }

    @Override
    public IUser createUser(String name, String surname, IBank bank) throws SQLException
    {
        Connection conn = connector.getConnection();
        int id=0;
        try(PreparedStatement ps = conn.prepareStatement(queryTempInsertClient, Statement.RETURN_GENERATED_KEYS))
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

    @Override
    public List<IUser> getListUserByBankId(int bankId) throws SQLException{
        List<IUser> list = new ArrayList<>();
        Connection conn = connector.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(queryTempGetListUserBankId)) {
            ps.setInt(1, bankId);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    //`bank_id`, `first_name`,`last_name`
                    String  name = resultSet.getString("first_name");
                    String  surname = resultSet.getString("last_name");
                    int id = resultSet.getInt("id");
                    //int _bankId = resultSet.getInt("bank_id");
                    IUser user = new  User(name, surname, id, bankId);
                    list.add(user);
                }
            }
        }
        return list;
    }

    @Override
    public IUser getUserById(int userId) throws SQLException {
        Connection connection = connector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(queryTempGetUserById)){
            ps.setInt(1, userId);
            try (ResultSet resultSet = ps.executeQuery())
            {
                if (resultSet.next()) {
                    String name = resultSet.getString("first_name");
                    String surname = resultSet.getString("last_name");
                    //int id = resultSet.getInt("id");
                    int bankId = resultSet.getInt("bank_id");
                    IUser user = new User(name, surname, userId, bankId);
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public void deleteUser(IUser user) throws SQLException {
        Connection connection = connector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(queryTempDeleteUserById)){
            ps.setInt(1, user.getUserId());
            int countRow = ps.executeUpdate();
            if(countRow==0)
            {
                throw new SQLException("Error delete user by id " + user.getUserId());
            }
        }
    }
}
