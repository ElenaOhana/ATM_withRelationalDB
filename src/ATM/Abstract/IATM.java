package ATM.Abstract;

import Card.ICard;

public interface IATM {

    String getPosition();
    String getATM_id();
    void insertCard(ICard ICard);
    ICard withdrawCard();
    boolean insertPIN();
    boolean insertMoney(double insertMoney);
    int withdrawCard(int withdrawMoney);
    int getBalance();
}
