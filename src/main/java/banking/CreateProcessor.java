package banking;

public class CreateProcessor {
    public Parsing parsing;
    private Bank bank;

    public CreateProcessor(Bank bank) {
        this.bank = bank;
        parsing = new Parsing();
    }


    public void createAccount(String command) {
        String accountType = parsing.getSecondWord(command);
        if (accountType.equalsIgnoreCase("checking")) {
            createChecking(command);
        } else if (accountType.equalsIgnoreCase("savings")) {
            createSavings(command);
        } else if (accountType.equalsIgnoreCase("cd")) {
            createCd(command);
        }


    }

    public void createChecking(String command) {
        String id = parsing.getThirdWord(command);
        double apr = Double.parseDouble(parsing.getFourthWord(command));
        bank.addCheckingAccount(id, apr);

    }

    public void createSavings(String command) {
        String id = parsing.getThirdWord(command);
        double apr = Double.parseDouble(parsing.getFourthWord(command));
        bank.addSavingsAccount(id, apr);


    }

    public void createCd(String command) {
        String id = parsing.getThirdWord(command);
        double apr = Double.parseDouble(parsing.getFourthWord(command));
        double amount = Double.parseDouble(parsing.getFifthWord(command));
        bank.addCdAccount(id, apr, amount);


    }


}
