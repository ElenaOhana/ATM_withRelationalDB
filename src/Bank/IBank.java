package Bank;

import ATM.Abstract.IATM;
import Accounts.AccountType;
import Accounts.IAccount;
import Card.ICard;
import Users.IUser;

public interface IBank {

    int getBankID();
    String getBankName();
}
