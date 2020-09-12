package Accounts;

import Bank.IBank;

import java.sql.SQLException;
import java.util.List;

public interface IAccountRepository {
    //IAccount createAccount(String accountName, String accountNumber, double amount, String bankId);
    IAccount createAccount(String accountName, double amount, IBank bank) throws SQLException;//DB generate the id/accountNumber
    //поиск аккаунта по ид и по ид банка
    IAccount getAccountById(int id) throws SQLException;
    List<IAccount> getAccountByBankId(int bankId) throws SQLException;
}
