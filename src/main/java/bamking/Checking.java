package bamking;

public class Checking extends Account {

    public Checking(String id, double apr) {
        super(id, apr);
    }

    @Override
    public boolean isValidDepositWith(String deposit_amount) {
        double amount = Double.parseDouble(deposit_amount);
        if (amount < 0 || amount > 1000) {
            return false;
        } else {
            return true;
        }
    }


}