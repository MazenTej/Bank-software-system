package banking;

public class CommandValidator {
    public Parsing parsing;
    private CreateValidator createValidator;
    private DepositValidator depositValidator;
    private WithdrawValidator withdrawValidator;

    CommandValidator(CreateValidator createValidator, DepositValidator depositValidator, WithdrawValidator withdrawValidator) {
        this.createValidator = createValidator;
        this.depositValidator = depositValidator;
        this.withdrawValidator = withdrawValidator;
        parsing = new Parsing();

    }


    public boolean validate(String command) {
        if (!checkForExtraSpaces(command)) {
            return false;
        } else {
            if (parsing.getFirstWord(command).equalsIgnoreCase("create")) {
                return createValidator.validateCreate(command);
            } else if (parsing.getFirstWord(command).equalsIgnoreCase("deposit")) {

                return depositValidator.validateDeposit(command);
            } else if (parsing.getFirstWord(command).equalsIgnoreCase("withdraw")) {
                return withdrawValidator.validateWithdraw(command);
            } else {
                return false;
            }
        }
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
