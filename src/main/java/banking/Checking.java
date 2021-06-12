package banking;

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

    @Override
    public void passTime(int i) {
        calculateApr();
    }

    @Override
    public boolean isValidWithdraw(String withdraw_amount) {
        boolean result = false;
        double amount = Double.parseDouble(withdraw_amount);
        if (amount >= 0 && amount <= 400) {
            result = true;
        }
        return result;
    }

    @Override
    public String getAccountType(String key) {
        return "Checking";
    }

    @Override
    public boolean isValidTransfer() {
        return true;
    }


}