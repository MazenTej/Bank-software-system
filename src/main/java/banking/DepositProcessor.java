package banking;

public class DepositProcessor {
    public Parsing parsing;
    private Bank bank;


    public DepositProcessor(Bank bank) {
        this.bank = bank;
        parsing = new Parsing();
    }


    public void depositAmount(String command) {
        String id = parsing.getSecondWord(command);
        double amountDeposited = Double.parseDouble(parsing.getThirdWord(command));
        bank.depositAmount(id, amountDeposited);


    }


}
