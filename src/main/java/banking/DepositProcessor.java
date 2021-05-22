package banking;

public class DepositProcessor {
    private Bank bank;


    public DepositProcessor(Bank bank) {
        this.bank = bank;
    }


    public void depositAmount(String command) {
        String id = getSecondWord(command);
        double amountDeposited = Double.parseDouble(getThirdWord(command));
        bank.depositAmount(id, amountDeposited);


    }


    public String getThirdWord(String command) {
        String[] sp = command.split(" ");
        return sp[2];
    }


    public String getSecondWord(String command) {
        int start = command.indexOf(" ") + 1;
        int end = command.indexOf(" ", start + 1);
        return command.substring(start, end);
    }
}
