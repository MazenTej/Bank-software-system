package bamking;

public class CommandValidator {
    private CreateValidator createValidator;
    private DepositValidator depositValidator;

    CommandValidator(CreateValidator createValidator, DepositValidator depositValidator) {
        this.createValidator = createValidator;
        this.depositValidator = depositValidator;

    }


    public boolean validate(String command) {
        if (!checkForExtraSpaces(command)) {
            return false;
        } else {
            if (getFirstWord(command).equalsIgnoreCase("create")) {
                return createValidator.validateCreate(command);
            } else if (getFirstWord(command).equalsIgnoreCase("deposit")) {

                return depositValidator.validateDeposit(command);
            } else {
                return false;
            }
        }
    }

    protected String getFirstWord(String command) {
        return command.substring(0, command.indexOf(' '));
    }

    protected boolean checkForExtraSpaces(String command) {
        boolean result = true;
        String newString = command.replaceAll("\\s++$", "");
        if (newString.startsWith(" ") || newString.contains("  ")) {
            result = false;
        }
        return result;


    }


}
