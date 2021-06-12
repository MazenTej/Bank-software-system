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
        if (parsing.checkCommandLength(command, 4)) {
            if (bank.accountExistsWithId(getFromId(command))) {
                if (bank.accountExistsWithId(getToId(command))) {
                    if (checkTransferIdsAreDifferent(command)) {
                        if (parsing.checkStringDouble(getTransferAmount(command))) {
                            if (bank.isValidWithdrawFromAccount(getFromId(command), getTransferAmount(command))) {
                                if (bank.isValidDepositInAccount(getToId(command), getTransferAmount(command))) {
                                    if (bank.isValidTransferBetweenAccounts(getFromId(command))) {
                                        result = true;
                                    }
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
        if (getFromId(command).equalsIgnoreCase(getFromId(command))) {
            return false;
        } else {
            return true;
        }
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
