package Bank;

import java.sql.SQLException;

public interface IBankService
{
    IBank createBank(String bankName) throws SQLException;
    IBank getBankByName(String name) throws SQLException;
    IBank getBankById(int id) throws SQLException;
}
