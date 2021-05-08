public class CommandValidator {
    Bank bank = new Bank();
    CreateValidator createValidator = new CreateValidator(bank);
    DepositValidator depositValidator = new DepositValidator(bank);


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
        String newString = command.replaceAll("\\s++$", "");
        if (newString.startsWith(" ") || newString.contains("  ")) {
            return false;
        } else {
            return true;
        }


    }


}
