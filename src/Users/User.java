package Users;

public class User implements IUser {
    private String name;
    private String surname;
    private int userId;
    private int bankId;

    public User(String name, String surname, int userId, int bankId) {
        this.name = name;
        this.surname = surname;
        this.userId = userId;
        this.bankId = bankId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public int getBankId() {
        return bankId;
    }


}
