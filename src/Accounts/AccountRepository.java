package Accounts;

import Bank.IBank;
import DB.JDBC.JDBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements IAccountRepository{

    JDBConnector jdbConnector;
    final String queryTempGetAccountById = "SELECT * FROM `accounts` WHERE `account_number` = ?";
    final String queryTempGetListAccountByBankId = "SELECT * `accounts` WHERE `bank_id` = ?";
    final String queryTempInsertAccount = "INSERT INTO `accounts` (`account_name`, `balance`, `bank_id`) VALUES  (?,?,?)";

    public AccountRepository(JDBConnector jdbConnector) {
        this.jdbConnector = jdbConnector;
    }

    @Override
    public IAccount createAccount(String accountName,  double amount, IBank bank, Connection conn) throws SQLException{
        int id = 0;
        Connection connection = conn==null?jdbConnector.getConnection(): conn;
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryTempInsertAccount, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(3, bank.getBankID());
            preparedStatement.setString(1, accountName);
            preparedStatement.setDouble(2, amount );
        int row = preparedStatement.executeUpdate();
            if (row == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    id = generatedKeys.getInt(1);
                else throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        return new Account(id,accountName,amount, bank.getBankID());
    }

    @Override
    public IAccount getAccountById(int id) throws SQLException {
        Connection connection = jdbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(queryTempGetAccountById)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()){
                if (resultSet.next()) {
                    String accountName = resultSet.getString("account_name");
                    double balance = resultSet.getDouble("balance");
                    int bankId = resultSet.getInt("bank_id");
                    IAccount account = new Account(id, accountName, balance, bankId);
                    return account;
                }
            }
        }
        return null;
    }

    @Override
    public List<IAccount> getAccountByBankId(int bankId) throws SQLException {
        List<IAccount> accountList = new ArrayList<>();
        Connection conn = jdbConnector.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(queryTempGetListAccountByBankId)) {
            ps.setInt(1, bankId);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int accountNumber = resultSet.getInt("account_number");
                    String accountName = resultSet.getString("account_name");
                    double balance = resultSet.getDouble("balance");
                    IAccount account = new Account(accountNumber, accountName, balance, bankId);
                    accountList.add(account);
                }
            }
        }
        return null;
    }
}
