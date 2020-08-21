package ATM.Impl;
import ATM.Abstract.IATM;
import Card.ICard;

public class ATM implements IATM {
    int currentAmount;
    String position;
    String atmId;
    ICard ICard;

    public ATM(int currentAmount, String position, String atmId) {
        this.currentAmount = currentAmount;
        this.position = position;
        this.atmId = atmId;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public String getATM_id() {
        return atmId;
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
        return true; // todo check for bad case
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
