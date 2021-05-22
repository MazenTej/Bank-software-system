package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {
    protected List<String> commands;


    CommandStorage() {
        commands = new ArrayList<String>();
    }


    public void addInvalidCommand(String command) {
        commands.add(command);
    }

    public List<String> getInvalidCommands() {
        return commands;

    }
}
