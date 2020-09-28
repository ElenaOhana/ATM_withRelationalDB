package ATM.Impl;
import ATM.Abstract.IATM;
import Card.ICard;

public class ATM implements IATM {
    int currentAmount;
    String position;
    int atmId;
    private int bankId;
    ICard iCard;

    public ATM(int currentAmount, String position, int atmId, int bankId) {
        this.currentAmount = currentAmount;
        this.position = position;
        this.atmId = atmId;
        this.bankId = bankId;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public int getATM_id() {
        return atmId;
    }
}
