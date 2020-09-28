package ATM.Abstract;

import Bank.IBank;

import java.sql.SQLException;
import java.util.List;

public interface IATMRepository {
    IATM createATM(int currentAmount, String position, IBank bank) throws SQLException;
    IATM getATMById(int id) throws SQLException;
    List<IATM> getListATMByBankId(int bankId) throws SQLException;
    List<IATM> getListATMByPosition(String position) throws SQLException;
    List<IATM> getListATMByBalance(int balance) throws SQLException;
}
