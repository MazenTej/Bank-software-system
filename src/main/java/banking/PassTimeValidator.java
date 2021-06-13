package banking;

public class PassTimeValidator {
    public Parsing parsing;

    public PassTimeValidator() {
        parsing = new Parsing();
    }

    public boolean validatePassTime(String command) {
        boolean result = false;
        if (parsing.checkCommandLength(command, 2) && checkNumberOfMonths(command)) {
            result = true;
        }
        return result;
    }

    private boolean checkNumberOfMonths(String command) {
        boolean result = false;
        try {
            Integer months = Integer.parseInt(parsing.getSecondWord(command));
            if (checkNumberOfMonthsInRange(months)) {
                result = true;
            }
        } catch (Exception e) {
            return false;
        }
        return result;
    }

    private boolean checkNumberOfMonthsInRange(Integer months) {
        if (months >= 1 && months <= 60) {
            return true;
        } else {
            return false;
        }
    }


}
