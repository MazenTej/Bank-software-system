public class CommandProcessor {
    private Bank bank;


    public CommandProcessor(Bank bank) {
        this.bank = bank;
    }


    public void createChecking(String command) {
        String accountType = getSecondWord(command);
        String id = getThirdWord(command);
        double apr = Double.parseDouble(getFourthWord(command));
        if (accountType.equalsIgnoreCase("checking")) {
            bank.addCheckingAccount(id, apr);
        }

    }

    public void createSavings(String command) {
        String accountType = getSecondWord(command);
        String id = getThirdWord(command);
        double apr = Double.parseDouble(getFourthWord(command));
        if (accountType.equalsIgnoreCase("savings")) {
            bank.addSavingsAccount(id, apr);
        }

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


}