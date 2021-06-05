package banking;

public class CreateValidator {
    public Parsing parsing;
    private Bank bank;

    public CreateValidator(Bank bank) {
        this.bank = bank;
        parsing = new Parsing();
    }

    public boolean validateCreate(String command) {

        String accountType = parsing.getSecondWord(command);
        if (accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings")) {
            if (checkCommandLengthForSavingsAndChecking(command)) {
                return validateCommand(command);

            } else {
                return false;
            }
        } else if (accountType.equalsIgnoreCase("cd")) {
            if (checkCommandLengthForCd(command)) {
                if (validateCommand(command)) {
                    if (checkCdAmountDouble(command)) {
                        if (checkCdAmountInRange(command)) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }


    }

    public boolean validateCommand(String command) {
        String id = parsing.getThirdWord(command);
        if (checkIdLength(command)) {
            if (checkIdHasOnlyNumbers(command)) {
                if (bank.accountExistsWithId(id)) {
                    return false;
                } else {
                    if (checkAprDouble(command)) {
                        if (checkAprRange(command)) {
                            return true;

                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public boolean checkIdLength(String command) {
        if (parsing.getThirdWord(command).length() != 8) {
            return false;
        } else {
            return true;
        }
    }


    public boolean checkIdHasOnlyNumbers(String command) {
        String id = parsing.getThirdWord(command);
        if (id.matches(".*[^0-9]+.*")) {
            return false;
        } else {
            return true;
        }

    }


    public boolean checkAprDouble(String command) {
        boolean result;

        try {
            String str = parsing.getFourthWord(command);
            Double.parseDouble(str);
            result = true;

        } catch (Exception e) {
            result = false;
        }
        return result;

    }


    public boolean checkAprRange(String command) {
        String str = parsing.getFourthWord(command);
        double apr = Double.parseDouble(str);
        if (apr < 0 || apr > 10) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkCommandLengthForSavingsAndChecking(String command) {
        boolean result = true;
        String[] words = command.split("\\s+");
        if (words.length != 4) {
            result = false;
        }
        return result;
    }

    public boolean checkCommandLengthForCd(String command) {
        boolean result = true;
        String[] words = command.split("\\s+");
        if (words.length != 5) {
            result = false;
        }
        return result;
    }


    public boolean checkCdAmountDouble(String command) {
        boolean result;

        try {
            String str = parsing.getFifthWord(command);
            Double.parseDouble(str);
            result = true;

        } catch (Exception e) {
            result = false;
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
}
