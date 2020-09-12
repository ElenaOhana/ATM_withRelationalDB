package Accounts;

public class AccountService implements IAccountService {

    @Override
    public boolean withdraw(IAccount accountWithdraw, IAccount accountInserted, double amount) {
        return false;
    }

    @Override
    public boolean insertMoney(IAccount accountInserte, IAccount accountWithdraw, double amount) {
        return false;
    }
}
