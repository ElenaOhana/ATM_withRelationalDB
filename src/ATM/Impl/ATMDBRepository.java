package ATM.Impl;

import ATM.Abstract.IATM;
import ATM.Abstract.IATMRepository;

public class ATMDBRepository implements IATMRepository { //todo connect to DB and via this connection send requests to insert/remove data.

    @Override
    public boolean contain(IATM atm) {
        return false;
    }

    @Override
    public boolean add(IATM atm) {
        return false;
    }

    @Override
    public boolean remove(IATM atm) {
        return false;
    }
}
