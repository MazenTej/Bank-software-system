public abstract class Account {
    protected String id;
    protected double apr;
    protected double amount;


    public Account(String id, double apr, double amount_cd) {
        this.id = id;
        this.apr = apr;
        amount = amount_cd;
    }


    public Account(String id, double apr) {
        this.id = id;
        this.apr = apr;
        amount = 0;
    }

    public String getId() {
        return id;
    }


    public double getApr() {
        return apr;
    }


    public double getAmount() {
        return amount;

    }


    public void setAmount(double newAmount) {
        amount = newAmount;
    }


    public void deposit(double amountDeposited) {
        setAmount(amount + amountDeposited);
    }

    public void withdraw(double amountWithdrawn) {
        setAmount(amount - amountWithdrawn);
        if (amount < 0) {
            setAmount(0.0);
        }
    }


    public abstract boolean isValidDepositWith(String deposit_amount);
}
