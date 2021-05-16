import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandStorageTest {
    CommandStorage commandStorage;
    private List<String> commands = new ArrayList<String>();

    @BeforeEach
    void setUp() {
        commandStorage = new CommandStorage();
    }

    @Test
    void commands_is_initially_empty() {
        assertEquals(commands.size(), 0);

    }

    @Test
    void adding_and_returning_one_command_is_successful() {
        commands.add("creeeta checking 123 1.0");
        assertEquals(commands.get(0), "creeeta checking 123 1.0");
    }

    @Test
    void adding_and_returning_two_commands_is_successful() {
        commands.add("deepotst 12345 45");
        commands.add("deespkt 542 5421");
        assertEquals(commands.get(0), "deepotst 12345 45");
        assertEquals(commands.get(1), "deespkt 542 5421");
    }

    @Test
    void adding_and_returning_three_commands_is_successful() {
        commands.add("deepotst 12345 45");
        commands.add("deespkt 542 5421");
        commands.add("creeeta checking 123 1.0");

        assertEquals(commands.get(0), "deepotst 12345 45");
        assertEquals(commands.get(1), "deespkt 542 5421");
        assertEquals(commands.get(2), "creeeta checking 123 1.0");


    }
}
