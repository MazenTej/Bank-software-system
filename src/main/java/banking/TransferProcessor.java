package banking;

public class TransferProcessor {
    public Parsing parsing;
    private Bank bank;


    public TransferProcessor(Bank bank) {
        this.bank = bank;
        parsing = new Parsing();
    }

    public void transferAmount(String command) {
        String fromId = parsing.getSecondWord(command);
        String toId = parsing.getThirdWord(command);
        double amountTransferred = Double.parseDouble(parsing.getFourthWord(command));
        bank.transferAmount(fromId, toId, amountTransferred);
    }
}
