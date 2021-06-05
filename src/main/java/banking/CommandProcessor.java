package banking;

public class CommandProcessor {
    public Parsing parsing;
    private CreateProcessor createProcessor;
    private DepositProcessor depositProcessor;


    CommandProcessor(CreateProcessor createProcessor, DepositProcessor depositProcessor) {
        this.createProcessor = createProcessor;
        this.depositProcessor = depositProcessor;
        parsing = new Parsing();
    }


    public void process(String command) {
        String firstWord = parsing.getFirstWord(command);
        if (firstWord.equalsIgnoreCase("create")) {
            createProcessor.createAccount(command);
        } else if (firstWord.equalsIgnoreCase("deposit")) {
            depositProcessor.depositAmount(command);
        }

    }


}