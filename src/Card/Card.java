package Card;

public class Card implements ICard {
    private int id;
    private String clientName;
    private int pin;
    private int accountId;

    public Card(int id, String clientName, int pin, int accountId) {
        this.id = id;
        this.clientName = clientName;
        this.pin = pin;
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return clientName;
    }

    @Override
    public int getPin() {
        return pin;
    }

    @Override
    public int getacountNumber() {
        return accountId;
    }

    @Override
    public int getcardNumber() {
        return id;
    }


}
