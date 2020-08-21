package Accounts;

public class AccountService implements IAccountService {
    @Override
    public IAccount createAccount(String accountName, String accountNumber, double amount, String bankId) {
        IAccount account = new Account(accountName, accountNumber, amount);
        return account;
    }
}
