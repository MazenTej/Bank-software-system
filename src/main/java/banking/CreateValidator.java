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
        if (getAccountType(command).equalsIgnoreCase("checking") || getAccountType(command).equalsIgnoreCase("savings")) {
            if (parsing.checkCommandLength(command, 4)) {
                return validateCommand(command);
            }
        } else if (getAccountType(command).equalsIgnoreCase("cd")) {
            if (parsing.checkCommandLength(command, 5)) {
                if (validateCommand(command)) {
                    if (parsing.checkStringDouble(getCdAmount(command))) {
                        if (checkCdAmountInRange(command)) {
                            result = true;
                        }
                    }
                }
            }
        }
        return result;


    }

    public boolean validateCommand(String command) {
        boolean result = false;
        if (parsing.checkIdLength(getId(command))) {
            if (parsing.checkIdHasOnlyNumbers(getId(command))) {
                if (bank.accountExistsWithId(getId(command))) {
                    return false;
                } else {
                    if (parsing.checkStringDouble(getApr(command))) {
                        if (parsing.checkAprRange(getApr(command))) {
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }


    public boolean checkCdAmountInRange(String command) {
        String str = parsing.getFifthWord(command);
        double apr = Double.parseDouble(str);
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
