package banking;

public class DepositValidator {
    public Parsing parsing;
    private Bank bank;


    public DepositValidator(Bank bank) {
        this.bank = bank;
        parsing = new Parsing();
    }


    public boolean validateDeposit(String command) {
        boolean result = false;
        if (parsing.checkCommandLength(command, 3) && isIdValid(command) && parsing.checkStringDouble(getDepositAmount(command)) && checkValidDeposit(command)) {
            result = true;
        }
        return result;

    }

    private boolean isIdValid(String command) {
        boolean result = false;
        if (parsing.checkIdLength(getId(command)) && parsing.checkIdHasOnlyNumbers(getId(command)) && bank.accountExistsWithId(getId(command))) {
            result = true;


        }
        return result;
    }


    public boolean checkValidDeposit(String command) {
        boolean result = false;
        String id = parsing.getSecondWord(command);
        String deposit_amount = parsing.getThirdWord(command);
        boolean isValid = bank.isValidDepositInAccount(id, deposit_amount);
        if (isValid) {
            result = true;
        }
        return result;
    }

    public String getDepositAmount(String command) {
        String depositAmount = parsing.getThirdWord(command);
        return depositAmount;
    }

    public String getId(String command) {
        String id = parsing.getSecondWord(command);
        return id;
    }
}
