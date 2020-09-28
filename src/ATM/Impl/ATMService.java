package ATM.Impl;


import ATM.Abstract.IATM;
import ATM.Abstract.IATMService;
import Card.ICard;

public class ATMService implements IATMService {
    @Override
    public void insertCard(ICard ICard) {
    }

    @Override
    public ICard withdrawCard() {
        return null;
    }

    @Override
    public boolean insertPIN() {
        return false;
    }

    @Override
    public boolean insertMoney(double insertMoney) {
        return false;
    }

    @Override
    public int withdrawCard(int withdrawMoney) {
        return 0;
    }

    @Override
    public int getBalance() {
        return 0;
    }
   /* @Override
    public IATM createATM(String position, int id, int balance, String bankId) {
        IATM atm = new ATM(balance, position, id);
        return atm;
    }*/
}
