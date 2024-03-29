package banking;

public class WithdrawValidator {
    public Parsing parsing;
    private Bank bank;


    public WithdrawValidator(Bank bank) {
        this.bank = bank;
        parsing = new Parsing();
    }


    public boolean validateWithdraw(String command) {
        boolean result = false;
        if (parsing.checkCommandLength(command, 3) && isIdValid(command) && parsing.checkStringDouble(getWithdrawAmount(command)) && checkValidWithdraw(command)) {
            result = true;
        }
        return result;
    }

    private boolean isIdValid(String command) {
        boolean result = false;
        if (parsing.checkIdLength(getWithdrawId(command)) && parsing.checkIdHasOnlyNumbers(getWithdrawId(command)) && bank.accountExistsWithId(getWithdrawId(command))) {
            result = true;
        }
        return result;

    }

    public boolean checkValidWithdraw(String command) {
        boolean result = false;
        String id = parsing.getSecondWord(command);
        String withdraw_amount = parsing.getThirdWord(command);
        boolean isValid = bank.isValidWithdrawFromAccount(id, withdraw_amount);
        if (isValid) {
            result = true;
        }
        return result;
    }

    public String getWithdrawId(String command) {
        String withdrawId = parsing.getSecondWord(command);
        return withdrawId;
    }

    public String getWithdrawAmount(String command) {
        String withdrawAmount = parsing.getThirdWord(command);
        return withdrawAmount;
    }
}


