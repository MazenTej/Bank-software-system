package bamking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandStorageTest {
    CommandStorage commandStorage;


    @BeforeEach
    void setUp() {
        commandStorage = new CommandStorage();
    }

    @Test
    void commands_is_initially_empty() {
        assertEquals(commandStorage.commands.size(), 0);

    }

    @Test
    void adding_one_command_and_returning_it_is_successful() {
        commandStorage.addInvalidCommand("creeeta checking 123 1.0");
        assertEquals(commandStorage.getInvalidCommands(), commandStorage.commands);
        assertEquals(commandStorage.commands.size(), 1);
        assertEquals(commandStorage.commands.get(0), "creeeta checking 123 1.0");
    }

    @Test
    void adding_two_commands_and_returning_them_is_successful() {
        commandStorage.addInvalidCommand("deepotst 12345 45");
        commandStorage.addInvalidCommand("deespkt 542 5421");
        assertEquals(commandStorage.getInvalidCommands(), commandStorage.commands);

        assertEquals(commandStorage.commands.size(), 2);
        assertEquals(commandStorage.commands.get(0), "deepotst 12345 45");
        assertEquals(commandStorage.commands.get(1), "deespkt 542 5421");

    }

    @Test
    void adding_three_commands_and_returning_them_is_successful() {
        commandStorage.addInvalidCommand("deepotst 12345 45");
        commandStorage.addInvalidCommand("deespkt 542 5421");
        commandStorage.addInvalidCommand("creeeta checking 123 1.0");

        assertEquals(commandStorage.getInvalidCommands(), commandStorage.commands);


        assertEquals(commandStorage.commands.size(), 3);
        assertEquals(commandStorage.commands.get(0), "deepotst 12345 45");
        assertEquals(commandStorage.commands.get(1), "deespkt 542 5421");
        assertEquals(commandStorage.commands.get(2), "creeeta checking 123 1.0");


    }
}
