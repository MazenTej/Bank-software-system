package banking;

public class CommandValidator {
    public Parsing parsing;
    private CreateValidator createValidator;
    private DepositValidator depositValidator;
    private WithdrawValidator withdrawValidator;
    private TransferValidator transferValidator;
    private PassTimeValidator passTimeValidator;

    CommandValidator(CreateValidator createValidator, DepositValidator depositValidator, WithdrawValidator withdrawValidator, TransferValidator transferValidator, PassTimeValidator passTimeValidator) {
        this.createValidator = createValidator;
        this.depositValidator = depositValidator;
        this.withdrawValidator = withdrawValidator;
        this.transferValidator = transferValidator;
        this.passTimeValidator = passTimeValidator;
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
            } else if (parsing.getFirstWord(command).equalsIgnoreCase("transfer")) {
                return transferValidator.validateTransfer(command);
            } else if (parsing.getFirstWord(command).equalsIgnoreCase("pass")) {
                return passTimeValidator.validatePassTime(command);
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
