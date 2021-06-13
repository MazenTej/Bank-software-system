package banking;

public class CreateValidator {
    public Parsing parsing;
    private Bank bank;

    public CreateValidator(Bank bank) {
        this.bank = bank;
        parsing = new Parsing();
    }

    public boolean validateCreate(String command) {
        boolean result = false;
        if ((getAccountType(command).equalsIgnoreCase("checking") || getAccountType(command).equalsIgnoreCase("savings")) && parsing.checkCommandLength(command, 4)) {
            return validateCommand(command);
        } else if (getAccountType(command).equalsIgnoreCase("cd") && parsing.checkCommandLength(command, 5) && validateCommand(command) && checkCdAmount(command)) {
            result = true;
        }
        return result;


    }

    private boolean checkCdAmount(String command) {
        boolean result = false;
        try {
            if (checkCdAmountInRange(command)) {
                result = true;
            }
        } catch (Exception e) {
            return false;
        }
        return result;


    }

    public boolean validateCommand(String command) {
        boolean result = false;
        if (bank.accountExistsWithId(getId(command))) {
            return false;
        } else if (checkId(command) && checkApr(getApr(command))) {
            result = true;
        }
        return result;
    }

    public boolean checkId(String command) {
        boolean result = false;
        if (parsing.checkIdLength(getId(command)) && parsing.checkIdHasOnlyNumbers(getId(command))) {
            result = true;

        }
        return result;
    }

    public boolean checkApr(String apr) {
        boolean result = false;
        try {
            if (parsing.checkAprRange(apr)) {
                result = true;
            }
        } catch (Exception e) {
            return false;
        }
        return result;

    }

    public boolean checkCdAmountInRange(String command) {
        double apr = Double.parseDouble(getCdAmount(command));
        if (apr < 1000 || apr > 10000) {
            return false;
        } else {
            return true;
        }
    }

    public String getAccountType(String command) {
        String accountType = parsing.getSecondWord(command);
        return accountType;
    }

    public String getCdAmount(String command) {
        String cdAmount = parsing.getFifthWord(command);
        return cdAmount;
    }

    public String getId(String command) {
        String id = parsing.getThirdWord(command);
        return id;
    }

    public String getApr(String command) {
        String apr = parsing.getFourthWord(command);
        return apr;
    }
}
