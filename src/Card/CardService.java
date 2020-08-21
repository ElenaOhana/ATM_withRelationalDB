package Card;

public class CardService implements ICardService{

    @Override
    public ICard createCard(String name, String pin, String accountNumber) {
        ICard card = new Card(name, pin);
        return card;
    }
}
