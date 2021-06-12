package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class CommandStorage {

    public Parsing parsing;
    protected Map<String, List<String>> valid_commands;
    protected List<String> invalid_commands;
    private Bank bank;
    private List<String> output;


    CommandStorage(Bank bank) {
        parsing = new Parsing();
        this.bank = bank;
        invalid_commands = new ArrayList<String>();
        valid_commands = new LinkedHashMap<>();
        output = new ArrayList<String>();


    }

    public void addInvalidCommand(String command) {
        invalid_commands.add(command);
    }

    public List<String> getInvalidCommands() {
        return invalid_commands;
    }

    public void addValidCommand(String command) {
        if (getCommandWord(command).equalsIgnoreCase("deposit") || getCommandWord(command).equalsIgnoreCase("withdraw")) {
            addDepositWithdrawToTransactionHistory(command);

        } else if (getCommandWord(command).equalsIgnoreCase("transfer")) {
            addTransferToTransactionHistory(command);
        }

    }

    public void addDepositWithdrawToTransactionHistory(String command) {
        if (!valid_commands.containsKey(getId(command))) {
            valid_commands.put(getId(command), new ArrayList<String>());
        }
        valid_commands.get(getId(command)).add(command);
    }

    public void addTransferToTransactionHistory(String command) {
        if (!valid_commands.containsKey(getFromId(command))) {
            valid_commands.put(getFromId(command), new ArrayList<String>());
        }
        valid_commands.get(getFromId(command)).add(command);
        if (!valid_commands.containsKey(getToId(command))) {
            valid_commands.put(getToId(command), new ArrayList<String>());
        }
        valid_commands.get(getToId(command)).add(command);

    }

    public List<String> getOutput() {
        Iterator<String> it = bank.getAccounts().keySet().iterator();
        while ((it.hasNext())) {
            String key = it.next();
            Account account = bank.retrieveAccount(key);
            String id = account.getId();
            String accountState = formatAccountState(key);
            output.add(accountState);
            if (valid_commands.get(id) != null) {
                for (String s : valid_commands.get(id)) {
                    output.add(s);
                }
            }
        }
        for (String s : invalid_commands) {
            output.add(s);
        }
        return output;

    }

    public String formatAccountState(String key) {
        Account account = bank.retrieveAccount(key);
        String accountType = bank.getAccountType(key);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        String apr = decimalFormat.format(account.getApr());
        String amount = decimalFormat.format(account.getAmount());
        String id = account.getId();
        String accountState = (accountType + " " + id + " " + amount + " " + apr);

        return accountState;
    }

    public Map getValidCommands() {
        return valid_commands;
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

