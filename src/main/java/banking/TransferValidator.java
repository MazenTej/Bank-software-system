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
        if (parsing.checkCommandLength(command, 4) && isIdValid(command) && parsing.checkStringDouble(getTransferAmount(command)) && bank.isValidWithdrawFromAccount(getFromId(command), getTransferAmount(command)) && bank.isValidDepositInAccount(getToId(command), getTransferAmount(command))) {
            result = true;
        }
        return result;
    }

    private boolean isIdValid(String command) {
        boolean result = false;
        if (parsing.checkIdLength(getFromId(command)) && parsing.checkIdLength(getToId(command)) && parsing.checkIdHasOnlyNumbers(getFromId(command)) && parsing.checkIdHasOnlyNumbers(getToId(command)) && bank.accountExistsWithId(getFromId(command)) && bank.accountExistsWithId(getToId(command)) && bank.isValidTransferBetweenAccount(getFromId(command)) && checkTransferIdsAreDifferent(command)) {
            result = true;
        }
        return result;

    }

    private boolean checkTransferIdsAreDifferent(String command) {
        if (getFromId(command).equals(getToId(command))) {
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
