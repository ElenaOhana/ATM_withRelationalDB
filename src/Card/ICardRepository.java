package Card;

import Accounts.IAccount;

import java.sql.SQLException;
import java.util.List;

public interface ICardRepository {
    ICard createCard(String clientName, int pin, IAccount account) throws SQLException;
    ICard getCardById(int cardId) throws SQLException;
    List<ICard> getListCardByAccountNum(int accNum) throws SQLException;
}
