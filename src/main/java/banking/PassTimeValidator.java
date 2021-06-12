package banking;

public class PassTimeValidator {
    public Parsing parsing;
    private Bank bank;

    public PassTimeValidator(Bank bank) {
        this.bank = bank;
        parsing = new Parsing();
    }

    public boolean validatePassTime(String command) {
        boolean result = false;
        if (parsing.checkCommandLength(command, 2)) {
            if (checkNumberOfMonthsInteger(command)) {
                if (checkNumberOfMonthsInRange(command)) {
                    result = true;
                }
            }

        }
        return result;
    }

    private boolean checkNumberOfMonthsInRange(String command) {
        Integer months = Integer.parseInt(parsing.getSecondWord(command));
        if (months >= 1 && months <= 60) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkNumberOfMonthsInteger(String command) {
        boolean result;
        try {
            String str = parsing.getSecondWord(command);
            Integer.parseInt(str);
            result = true;

        } catch (Exception e) {
            result = false;
        }
        return result;
    }


}
