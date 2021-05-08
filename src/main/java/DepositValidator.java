public class DepositValidator {
    private Bank bank;

    public DepositValidator(Bank bank) {
        this.bank = bank;
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
        String id = getSecondWord(command);
        if (bank.accountExistsWithId(id)) {
            result = true;
        }
        return result;
    }


    public String getSecondWord(String command) {
        int start = command.indexOf(" ") + 1;
        int end = command.indexOf(" ", start + 1);
        return command.substring(start, end);
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
            String str = getThirdWord(command);
            Double.parseDouble(str);
            result = true;

        } catch (Exception e) {
            result = false;
        }
        return result;

    }


    public String getThirdWord(String command) {
        String[] sp = command.split(" ");
        return sp[2];
    }


    public boolean checkValidDeposit(String command) {
        String id = getSecondWord(command);
        String deposit_amount = getThirdWord(command);
        boolean isValid = bank.isValidDepositInAccount(id, deposit_amount);
        if (isValid) {
            return true;
        } else {
            return false;
        }

    }
}
