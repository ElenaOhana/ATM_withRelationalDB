package Accounts;

public interface IAccount {
    String getAccountNumber();
    double getBalance();
    String getAccountName();
    void setAccountName(String accName);
    boolean withdraw(double withdrSum);
    void insertMoney(double insertSum);
}
