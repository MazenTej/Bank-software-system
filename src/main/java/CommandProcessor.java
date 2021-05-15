public class CommandProcessor {
    private Bank bank;
    private CreateProcessor createProcessor;


    public CommandProcessor(CreateProcessor createProcessor) {
        this.createProcessor = createProcessor;
    }


    public void process(String command) {
        String firstWord = getFirstWord(command);
        if (firstWord.equalsIgnoreCase("create")) {
            createProcessor.createAccount(command);
        }

    }


    protected String getFirstWord(String command) {
        return command.substring(0, command.indexOf(' '));
    }


}