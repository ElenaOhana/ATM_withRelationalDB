package Accounts;

public class AccountService implements IAccountService {
    @Override
    public IAccount createAccount(String accountName, String accountNumber, double amount, String bankId) {
        //IAccount account = new Account(accountName, accountNumber, amount);
        return null;
    }

    @Override
    public boolean withdraw(IAccount accountWithdraw, IAccount accountInserted, double amount) {
        return false;
    }

    @Override
    public boolean insertMoney(IAccount accountInserte, IAccount accountWithdraw, double amount) {
        return false;
    }
}
