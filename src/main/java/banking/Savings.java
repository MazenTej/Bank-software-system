package banking;

public class Savings extends Account {
    protected Integer months = 1;

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

    public void setMonths(Integer newMonths) {
        months = newMonths;
    }

    @Override
    public void passTime(int i) {
        setMonths(i);
        calculateApr();
    }

    @Override
    public boolean isValidWithdraw(String withdraw_amount) {
        boolean result = false;
        double amount = Double.parseDouble(withdraw_amount);
        if (months != 0) {
            if (amount <= 1000 && amount >= 0) {
                result = true;
                setMonths(0);
            }
        } else {
            result = false;
        }
        return result;
    }
}
