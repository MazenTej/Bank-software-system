package banking;

public class CreateProcessor {
    private Bank bank;

    public CreateProcessor(Bank bank) {
        this.bank = bank;
    }


    public void createAccount(String command) {
        String accountType = getSecondWord(command);
        if (accountType.equalsIgnoreCase("checking")) {
            createChecking(command);
        } else if (accountType.equalsIgnoreCase("savings")) {
            createSavings(command);
        } else if (accountType.equalsIgnoreCase("cd")) {
            createCd(command);
        }


    }

    public void createChecking(String command) {
        String id = getThirdWord(command);
        double apr = Double.parseDouble(getFourthWord(command));
        bank.addCheckingAccount(id, apr);

    }

    public void createSavings(String command) {
        String id = getThirdWord(command);
        double apr = Double.parseDouble(getFourthWord(command));
        bank.addSavingsAccount(id, apr);


    }

    public void createCd(String command) {
        String id = getThirdWord(command);
        double apr = Double.parseDouble(getFourthWord(command));
        double amount = Double.parseDouble(getFifthWord(command));
        bank.addCdAccount(id, apr, amount);


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

    public String getFourthWord(String command) {
        String[] sp = command.split(" ");
        return sp[3];
    }

    protected String getFifthWord(String command) {
        String[] sp = command.split(" ");
        return sp[4];

    }
}
