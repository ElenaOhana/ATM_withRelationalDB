package Accounts;

public interface IAccountService {
    IAccount createAccount(String accountName, String accountNumber, double amount, String bankId);
}
