package ATM.Impl;

import ATM.Abstract.IATM;
import Card.ICard;

public class HandFreeAtm extends ATM implements IATM {

    public HandFreeAtm(int currentAmount, String position, int atmId) {
        super(currentAmount, position, atmId);
    }

    @Override
    public String getPosition() {
        return null;
    }

    @Override
    public int getATM_id() {
        return atmId;
    }
}
