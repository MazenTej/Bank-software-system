package banking;

public class CommandProcessor {
    public Parsing parsing;
    private CreateProcessor createProcessor;
    private DepositProcessor depositProcessor;
    private WithdrawProcessor withdrawProcessor;
    private TransferProcessor transferProcessor;
    private PassTimeProcessor passTimeProcessor;


    CommandProcessor(CreateProcessor createProcessor, DepositProcessor depositProcessor, WithdrawProcessor withdrawProcessor, TransferProcessor transferProcessor, PassTimeProcessor passTimeProcessor) {
        this.createProcessor = createProcessor;
        this.depositProcessor = depositProcessor;
        this.withdrawProcessor = withdrawProcessor;
        this.transferProcessor = transferProcessor;
        this.passTimeProcessor = passTimeProcessor;
        parsing = new Parsing();
    }


    public void process(String command) {
        String firstWord = parsing.getFirstWord(command);
        if (firstWord.equalsIgnoreCase("create")) {
            createProcessor.createAccount(command);
        } else if (firstWord.equalsIgnoreCase("deposit")) {
            depositProcessor.depositAmount(command);
        } else if (firstWord.equalsIgnoreCase("withdraw")) {
            withdrawProcessor.withdrawAmount(command);
        } else if (firstWord.equalsIgnoreCase("transfer")) {
            transferProcessor.transferAmount(command);
        } else if (firstWord.equalsIgnoreCase("pass")) {
            passTimeProcessor.passTime(command);
        }

    }


}