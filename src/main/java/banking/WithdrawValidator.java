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
        if (checkWithdrawCommandLength(command)) {
            if (checkWithdrawIdExists(command)) {
                if (checkWithdrawAmountDouble(command)) {
                    if (checkValidWithdraw(command)) {
                        result = true;
                    }
                }

            }
        }
        return result;


    }

    public boolean checkWithdrawAmountDouble(String command) {
        boolean result;
        try {
            String str = parsing.getThirdWord(command);
            Double.parseDouble(str);
            result = true;

        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public boolean checkWithdrawIdExists(String command) {
        boolean result = false;
        String id = parsing.getSecondWord(command);
        if (bank.accountExistsWithId(id)) {
            result = true;
        }
        return result;
    }


    public boolean checkWithdrawCommandLength(String command) {
        boolean result = true;
        String[] words = command.split("\\s+");
        if (words.length != 3) {
            result = false;
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
}


