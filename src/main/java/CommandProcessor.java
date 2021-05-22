public class CommandProcessor {
    private CreateProcessor createProcessor;
    private DepositProcessor depositProcessor;


    CommandProcessor(CreateProcessor createProcessor, DepositProcessor depositProcessor) {
        this.createProcessor = createProcessor;
        this.depositProcessor = depositProcessor;
    }


    public void process(String command) {
        String firstWord = getFirstWord(command);
        if (firstWord.equalsIgnoreCase("create")) {
            createProcessor.createAccount(command);
        } else if (firstWord.equalsIgnoreCase("deposit")) {
            depositProcessor.depositAmount(command);
        }

    }


    protected String getFirstWord(String command) {
        return command.substring(0, command.indexOf(' '));
    }


}