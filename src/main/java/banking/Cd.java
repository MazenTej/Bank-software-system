package banking;

public class Cd extends Account {
    protected Integer months;

    public Cd(String id, double apr, double amount_cd) {
        super(id, apr, amount_cd);
        months = 0;

    }

    @Override
    public boolean isValidDepositWith(String deposit_amount) {
        return false;
    }

    @Override
    public void passTime(int i) {
        months += i;
        for (int j = 1; j <= 4; j++) {
            calculateApr();
        }

    }

    @Override
    public boolean isValidWithdraw(String withdraw_amount) {
        double amount = Double.parseDouble(withdraw_amount);
        if (months >= 12 && amount >= getAmount()) {
            return true;
        } else {
            return false;
        }
    }


}
