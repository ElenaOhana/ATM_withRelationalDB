package ATM.Impl;

import ATM.Abstract.IATM;
import ATM.Abstract.IATMRepository;
import Bank.IBank;
import DB.JDBC.JDBConnector;
import Users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ATMRepository implements IATMRepository { //todo connect to DB and via this connection send requests to insert/remove data.
    JDBConnector connector;
    final String queryTempInsertATM = "INSERT INTO `atm` (`bank_id`, `position`, `balance`) VALUES (?,?,?)";
    final String queryTempGetATMById = "SELECT * FROM `atm` WHERE `id` = ?";
    final String queryTempGetListATMByBankId = "SELECT * FROM `atm` WHERE `bank_id` = ?";

    public ATMRepository(JDBConnector connector){
        this.connector = connector;
    }

    @Override
    public IATM createATM(int currentAmount, String position, IBank bank) throws SQLException {
        Connection conn = connector.getConnection();
        int id=0;
        try(PreparedStatement ps = conn.prepareStatement(queryTempInsertATM, Statement.RETURN_GENERATED_KEYS))
        {
            ps.setInt(1, bank.getBankID());
            ps.setString(2,position);
            ps.setInt(3,currentAmount);
            int row = ps.executeUpdate();
            if(row==0)
                throw new SQLException("Creating atm failed, no rows affected.");
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating atm failed, no ID obtained.");
                }
            }
        }
        return new ATM(currentAmount, position, id, bank.getBankID());
    }

    @Override
    public IATM getATMById(int id) throws SQLException {
        Connection conn = connector.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(queryTempGetATMById)) {
            ps.setInt(1,id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    int bankId = resultSet.getInt("bank_id");
                    String position = resultSet.getString("position");
                    int balance = resultSet.getInt("balance");
                    IATM iatm = new ATM(balance, position, id, bankId);
                    return iatm;
                }
            }
        }
        return null;
    }

    @Override
    public List<IATM> getListATMByBankId(int bankId) throws SQLException {
        List<IATM> ATMList = new ArrayList<>();
        Connection connection = connector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(queryTempGetListATMByBankId)) {
            ps.setInt(1, bankId);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    //int bankId = resultSet.getInt("bank_id");
                    String position = resultSet.getString("position");
                    int balance = resultSet.getInt("balance");
                    int id = resultSet.getInt("id");
                    IATM atm = new ATM(balance, position, id, bankId);
                    ATMList.add(atm);
                }
            }
        }
        return ATMList;
    }

    @Override
    public List<IATM> getListATMByPosition(String position) throws SQLException {
        return null;
    }

    @Override
    public List<IATM> getListATMByBalance(int balance) throws SQLException {
        return null;
    }
   }
