package ATM.Abstract;

import Card.ICard;

//IATMService создает ATM-ки, руководит чтобы не было одной и той же на одном и том же месте, чтобы не быдо 2ух одинаковых id, проверяет баланс - чтобы был не отрицательный, и прочее.
public interface IATMService {
    //IATM  createATM(String position, int id, int balance, String bankId);
    void insertCard(ICard ICard);
    ICard withdrawCard();
    boolean insertPIN();
    boolean insertMoney(double insertMoney);
    int withdrawCard(int withdrawMoney);
    int getBalance();
}
