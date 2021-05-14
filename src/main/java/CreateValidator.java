public class CreateValidator {
    private Bank bank;

    public CreateValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validateCreate(String command) {

        String accountType = getSecondWord(command);
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
        String id = getThirdWord(command);
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


    public String getSecondWord(String command) {
        int start = command.indexOf(" ") + 1;
        int end = command.indexOf(" ", start + 1);
        return command.substring(start, end);
    }

    public String getThirdWord(String command) {
        String[] sp = command.split(" ");
        return sp[2];
    }


    public boolean checkIdLength(String command) {
        if (getThirdWord(command).length() != 8) {
            return false;
        } else {
            return true;
        }
    }


    public boolean checkIdHasOnlyNumbers(String command) {
        String id = getThirdWord(command);
        if (id.matches(".*[^0-9]+.*")) {
            return false;
        } else {
            return true;
        }

    }

    public String getFourthWord(String command) {
        String[] sp = command.split(" ");
        return sp[3];
    }

    public boolean checkAprDouble(String command) {
        boolean result;

        try {
            String str = getFourthWord(command);
            Double.parseDouble(str);
            result = true;

        } catch (Exception e) {
            result = false;
        }
        return result;

    }


    public boolean checkAprRange(String command) {
        String str = getFourthWord(command);
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

    public String getFifthWord(String command) {
        String[] sp = command.split(" ");
        return sp[4];

    }


    public boolean checkCdAmountDouble(String command) {
        boolean result;

        try {
            String str = getFifthWord(command);
            Double.parseDouble(str);
            result = true;

        } catch (Exception e) {
            result = false;
        }
        return result;
    }


    public boolean checkCdAmountInRange(String command) {
        String str = getFifthWord(command);
        double apr = Double.parseDouble(str);
        if (apr < 1000 || apr > 10000) {
            return false;
        } else {
            return true;
        }

    }
}