package banking;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommandStorage {

    public Parsing parsing;
    protected Map<String, List<String>> validCommands;
    protected List<String> invalidCommands;


    CommandStorage() {
        parsing = new Parsing();
        invalidCommands = new ArrayList<String>();
        validCommands = new LinkedHashMap<>();


    }

    public void addInvalidCommand(String command) {
        invalidCommands.add(command);
    }

    public List<String> getInvalidCommands() {
        return invalidCommands;
    }

    public void addTransactionHistory(String command) {
        if (getCommandWord(command).equalsIgnoreCase("deposit") || getCommandWord(command).equalsIgnoreCase("withdraw")) {
            addDepositWithdrawToTransactionHistory(command);
        } else if (getCommandWord(command).equalsIgnoreCase("transfer")) {
            addTransferToTransactionHistory(command);
        }
    }

    public void addDepositWithdrawToTransactionHistory(String command) {
        if (!validCommands.containsKey(getId(command))) {
            validCommands.put(getId(command), new ArrayList<String>());
        }
        validCommands.get(getId(command)).add(command);
    }

    public void addTransferToTransactionHistory(String command) {
        if (!validCommands.containsKey(getFromId(command))) {
            validCommands.put(getFromId(command), new ArrayList<String>());
        }
        validCommands.get(getFromId(command)).add(command);
        if (!validCommands.containsKey(getToId(command))) {
            validCommands.put(getToId(command), new ArrayList<String>());
        }
        validCommands.get(getToId(command)).add(command);

    }


    public String getId(String command) {
        String id = parsing.getSecondWord(command);
        return id;
    }

    public String getCommandWord(String command) {
        String commandWord = parsing.getFirstWord(command);
        return commandWord;
    }

    public String getToId(String command) {
        String toId = parsing.getThirdWord(command);
        return toId;
    }

    public String getFromId(String command) {
        String fromId = parsing.getSecondWord(command);
        return fromId;
    }
}

