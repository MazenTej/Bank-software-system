package bamking;

public class Cd extends Account {

    public Cd(String id, double apr, double amount_cd) {
        super(id, apr, amount_cd);

    }

    @Override
    public boolean isValidDepositWith(String deposit_amount) {
        return false;
    }


}
