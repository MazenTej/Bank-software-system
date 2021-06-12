package banking;

public class Cd extends Account {
    protected Integer monthsPassed;

    public Cd(String id, double apr, double amount_cd) {
        super(id, apr, amount_cd);
        monthsPassed = 0;

    }

    @Override
    public boolean isValidDepositWith(String deposit_amount) {
        return false;
    }

    @Override
    public void passTime(int months) {
        monthsPassed += months;
        for (int j = 1; j <= 4; j++) {
            calculateApr();
        }

    }

    @Override
    public boolean isValidWithdraw(String withdraw_amount) {
        double amount = Double.parseDouble(withdraw_amount);
        if (monthsPassed >= 12 && amount >= getAmount()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getAccountType(String key) {
        return "Cd";
    }

    @Override
    public boolean isValidTransfer() {
        return false;
    }


}
