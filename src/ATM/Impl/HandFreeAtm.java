package ATM.Impl;

import ATM.Abstract.IATM;
import Card.ICard;

public class HandFreeAtm implements IATM {

    @Override
    public String getPosition() {
        return null;
    }

    @Override
    public String getATM_id() {
        return null;
    }

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
}
