package Bank;

import java.sql.SQLException;

public interface IBankService
{
    IBank createBank(String bankName) throws SQLException;
}
