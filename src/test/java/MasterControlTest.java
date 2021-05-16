import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MasterControlTest {

    List<String> input;

    MasterControl masterControl;
    CreateValidator createValidator;
    DepositValidator depositValidator;
    CreateProcessor createProcessor;
    DepositProcessor depositProcessor;


    @BeforeEach
    void setUp() {
        input = new ArrayList<>();


        Bank bank = new Bank();
        createValidator = new CreateValidator(bank);
        depositValidator = new DepositValidator(bank);
        createProcessor = new CreateProcessor(bank);
        depositProcessor = new DepositProcessor(bank);


        masterControl = new MasterControl(bank, new CommandValidator(createValidator, depositValidator), new CommandProcessor(createProcessor, depositProcessor), new CommandStorage());

    }

    @Test
    void typo_in_create_command_is_invalid() {
        input.add("creat checking 12345678 1.0");

        List<String> actual = masterControl.start(input);
        assertSingleCommand("creat checking 12345678 1.0", actual);
    }

    @Test
    void typo_in_deposit_command_is_invalid() {

        input.add("depositt 12345678 100");

        List<String> actual = masterControl.start(input);
        assertSingleCommand("depositt 12345678 100", actual);

    }

    private void assertSingleCommand(String command, List<String> actual) {
        assertEquals(1, actual.size());
        assertEquals(command, actual.get(0));
    }
}
