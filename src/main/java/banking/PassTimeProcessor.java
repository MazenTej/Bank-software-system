package banking;

public class PassTimeProcessor {
    public Parsing parsing;
    private Bank bank;

    public PassTimeProcessor(Bank bank) {
        this.bank = bank;
        parsing = new Parsing();
    }

    public void passTime(String command) {
        Integer months = Integer.parseInt(parsing.getSecondWord(command));
        bank.passTime(months);

    }
}
