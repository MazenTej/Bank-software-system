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
        if (checkDepositCommandLength(command)) {
            if (checkDepositId(command)) {
                if (checkDepositAmountDouble(command)) {
                    if (checkValidDeposit(command)) {
                        result = true;
                    }
                }
            }
        }
        return result;

    }


    public boolean checkDepositId(String command) {
        boolean result = false;
        String id = parsing.getSecondWord(command);
        if (bank.accountExistsWithId(id)) {
            result = true;
        }
        return result;
    }


    public boolean checkDepositCommandLength(String command) {
        boolean result = true;
        String[] words = command.split("\\s+");
        if (words.length != 3) {
            result = false;
        }
        return result;

    }

    public boolean checkDepositAmountDouble(String command) {
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
}
