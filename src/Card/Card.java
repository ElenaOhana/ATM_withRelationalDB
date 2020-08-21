package Card;

public class Card implements ICard {
    private String name;
    private String pin;

    public Card(String name, String pin) {
        this.name =name;
        this.pin = pin;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPin() {
        return pin;
    }

}
