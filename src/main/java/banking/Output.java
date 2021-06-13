package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Output {
    public Parsing parsing;
    private Bank bank;
    private CommandStorage commandStorage;
    private List<String> output;

    Output(Bank bank, CommandStorage commandStorage) {
        parsing = new Parsing();
        this.bank = bank;
        this.commandStorage = commandStorage;
        output = new ArrayList<String>();
    }

    public void addValidCommands() {
        Iterator<String> it = bank.getAccounts().keySet().iterator();
        while ((it.hasNext())) {
            String key = it.next();
            Account account = bank.retrieveAccount(key);
            String id = account.getId();
            String accountState = formatAccountState(key);
            output.add(accountState);
            if (commandStorage.validCommands.get(id) != null) {
                for (String s : commandStorage.validCommands.get(id)) {
                    output.add(s);
                }
            }
        }

    }

    public List<String> getOutput() {
        addValidCommands();
        for (String s : commandStorage.invalidCommands) {
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


}
