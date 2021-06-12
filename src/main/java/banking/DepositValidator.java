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
        if (parsing.checkCommandLength(command, 3)) {
            if (bank.accountExistsWithId(getId(command))) {
                if (parsing.checkStringDouble(getDepositAmount(command))) {
                    if (checkValidDeposit(command)) {
                        result = true;
                    }
                }
            }
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
