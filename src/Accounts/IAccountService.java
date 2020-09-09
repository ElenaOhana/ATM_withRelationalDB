package Accounts;

public interface IAccountService {
    boolean withdraw(IAccount accountWithdraw,IAccount accountInserted, double amount);
    boolean insertMoney(IAccount accountInserte, IAccount accountWithdraw,double amount);
}
