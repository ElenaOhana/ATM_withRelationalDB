package ATM.Abstract;

public interface IATMRepository {

    boolean contain(IATM atm);
    boolean add(IATM atm);
    boolean remove(IATM atm);
}
