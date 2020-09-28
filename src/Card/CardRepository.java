package Card;

import Accounts.IAccount;
import DB.JDBC.JDBConnector;
import Users.IUser;
import Users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardRepository implements ICardRepository {
    JDBConnector jdbConnector;
    final String queryTempInsertCard = "INSERT INTO `cards` (`client_name`, `pin`, `account_number`) VALUES (?,?,?)";
    final String queryTempGetCardById = "SELECT * FROM `cards` WHERE `card_number` = ?";
    final String queryTempGetListCardByAccountNum = "SELECT * FROM `cards`  WHERE `account_number` = ?";

    public CardRepository (JDBConnector connector) {
        this.jdbConnector = connector;
    }

    @Override
    public ICard createCard(String clientName, int pin, IAccount account) throws SQLException {
        int id = 0;
        Connection connection = jdbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(queryTempInsertCard, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, clientName);
            ps.setInt(2, pin);
            ps.setInt(3, account.getAccountNumber());
            int row = ps.executeUpdate();
            if (row == 0) {
                throw new SQLException("Creating card failed, no rows affected.");
            }
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating card failed, no ID obtained.");
            }
        }
        return new Card(id, clientName, pin, account.getAccountNumber());
    }

    @Override
    public ICard getCardById(int cardId) throws SQLException {
        Connection connection = jdbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(queryTempGetCardById)) {
            ps.setInt(1, cardId);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String clientName = resultSet.getNString("client_name");
                    int pin = resultSet.getInt("pin");
                    int accNum = resultSet.getInt("account_number");
                    ICard card = new Card(cardId, clientName, pin, accNum);
                    return card;
                }
            }
        }
        return null;
    }

    @Override
    public List<ICard> getListCardByAccountNum(int accNum) throws SQLException{
        List<ICard> cardList = new ArrayList<>();
        Connection connection = jdbConnector.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(queryTempGetListCardByAccountNum)) {
            ps.setInt(1, accNum);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    String clientName = resultSet.getString("client_name");
                    int pin = resultSet.getInt("pin");
                    int cardNum = resultSet.getInt("card_number");
                    ICard card = new Card(cardNum, clientName, pin, accNum);
                    cardList.add(card);
                }
            }
        }
        return cardList;
    }
}
