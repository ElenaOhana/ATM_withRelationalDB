package Bank;

public class Bank implements IBank{
    int bankID;
    String bankName;

    public Bank(int bankID, String bankName) {
        this.bankID = bankID;
        this.bankName = bankName;
    }

    public int getBankID() {
        return bankID;
    }

    public void setBankID(int bankID) {
        this.bankID = bankID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
