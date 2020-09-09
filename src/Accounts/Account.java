package Accounts;

import ATM.Impl.ATM;

import java.math.BigDecimal;

public class Account implements IAccount{
    private String accountName;
    private int accountNumber;
    private double balance;
    private int bankId;

    public Account(int accountNumber, String accountName, double balance, int bankId) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankId=bankId;
    }

    @Override
    public int getAccountNumber() {
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
    public int getBankId() {
        return this.bankId;
    }


}
