package banking;

public class Savings extends Account {

    public Savings(String id, double apr) {
        super(id, apr);
    }

    @Override
    public boolean isValidDepositWith(String deposit_amount) {
        double amount = Double.parseDouble(deposit_amount);
        if (amount < 0 || amount > 2500) {
            return false;
        } else {
            return true;
        }
    }
}
