package banking;

public class WithdrawProcessor {
    public Parsing parsing;
    private Bank bank;

    public WithdrawProcessor(Bank bank) {
        this.bank = bank;
        parsing = new Parsing();
    }

    public void withdrawAmount(String command) {
        String id = parsing.getSecondWord(command);
        double amountWithdrawn = Double.parseDouble(parsing.getThirdWord(command));
        bank.withdrawAmount(id, amountWithdrawn);
    }
}
