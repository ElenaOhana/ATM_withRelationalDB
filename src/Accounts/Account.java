package Accounts;

import ATM.Impl.ATM;

import java.math.BigDecimal;

public class Account implements IAccount{
    private String accountName;
    private String accountNumber;
    private double balance;
    ATM atm;

    public Account(String accountName, String accountNumber, double balance) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getAccountName() {
        return accountName;
    }

    @Override
    public void setAccountName(String accName) { // Если присоединяется к счету еще один член семьи.
        this.accountName = accName;
    }
    /*@Override
    public boolean withdraw(double balance) {
        if (this.balance < balance)
            return false;
        else
            return true;
    }*/
    @Override
    public boolean withdraw(double withdrawSum) {
        if (balance < withdrawSum)
            return false;
        else
            return true;
    }

    @Override
    public void insertMoney(double insertSum) {
        if (atm.insertMoney(insertSum)) {
            balance = balance + insertSum;
        }
        else
            try {
                throw new Exception("ATM can't receive money at that moment.");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
