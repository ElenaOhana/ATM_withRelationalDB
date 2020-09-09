package Accounts;

public interface IAccountRepository {
    IAccount createAccount(String accountName, String accountNumber, double amount, String bankId);
    //поиск аккаунта по ид и по ид банка
}
