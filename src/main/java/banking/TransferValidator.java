package banking;

public class TransferValidator {
    public Parsing parsing;
    private Bank bank;


    public TransferValidator(Bank bank) {
        this.bank = bank;
        parsing = new Parsing();
    }

    public boolean validateTransfer(String command) {
        boolean result = false;
        if (checkTransferCommandLength(command)) {
            if (checkTransferFromIdExists(command)) {
                if (checkTransferToIdExists(command)) {
                    if (checkTransferIdsAreDifferent(command)) {
                        if (checkTransferAmountDouble(command)) {
                            if (bank.isValidWithdrawFromAccount(getFromId(command), getTransferAmount(command))) {
                                if (bank.isValidDepositInAccount(getToId(command), getTransferAmount(command))) {
                                    result = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean checkTransferIdsAreDifferent(String command) {
        String fromId = parsing.getSecondWord(command);
        String toId = parsing.getThirdWord(command);
        if (fromId == toId) {
            return false;
        } else {
            return true;
        }
    }


    public boolean checkTransferCommandLength(String command) {
        boolean result = true;
        String[] words = command.split("\\s+");
        if (words.length != 4) {
            result = false;
        }
        return result;

    }

    public boolean checkTransferFromIdExists(String command) {
        boolean result = false;
        String id = parsing.getSecondWord(command);
        if (bank.accountExistsWithId(id)) {
            result = true;
        }
        return result;
    }

    public boolean checkTransferToIdExists(String command) {
        boolean result = false;
        String id = parsing.getThirdWord(command);
        if (bank.accountExistsWithId(id)) {
            result = true;
        }
        return result;
    }


    public boolean checkTransferAmountDouble(String command) {
        boolean result;
        try {
            String str = parsing.getFourthWord(command);
            Double.parseDouble(str);
            result = true;

        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public String getFromId(String command) {
        String fromId = parsing.getSecondWord(command);
        return fromId;
    }

    public String getToId(String command) {
        String toId = parsing.getThirdWord(command);
        return toId;
    }

    public String getTransferAmount(String command) {
        String transferAmount = parsing.getFourthWord(command);
        return transferAmount;
    }

}
