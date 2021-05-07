public class CreateValidator {
    private Bank bank;

    public CreateValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validateCreate(String command) {
        String accountType = getSecondWord(command);
        if (accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings") || accountType.equalsIgnoreCase("cd")) {
            if (checkIdLength(command)) {
                if (checkNumbers(command)) {
                    if (bank.accountExistsWithId("12345678")) {
                        return false;
                    } else {
                        return true;
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


    public boolean checkNumbers(String command) {
        String id = getThirdWord(command);
        if (id.matches(".*[^0-9]+.*")) {
            return false;
        } else {
            return true;
        }

    }
}
