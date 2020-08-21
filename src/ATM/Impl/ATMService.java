package ATM.Impl;


import ATM.Abstract.IATM;
import ATM.Abstract.IATMService;

public class ATMService implements IATMService {
    @Override
    public IATM createATM(String position, String id, int balance, String bankId) {
        IATM atm = new ATM(balance, position, id);
        return atm;
    }
}
