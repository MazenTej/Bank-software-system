import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandProcessorTest {
    Bank bank;
    CommandProcessor commandProcessor;
    CreateProcessor createProcessor;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        createProcessor = new CreateProcessor(bank);
        commandProcessor = new CommandProcessor(createProcessor);


    }

    @Test
    void process() {
        commandProcessor.process("create checking 12345678 1.0");
        Account actual = bank.getAccounts().get("12345678");
        assertTrue(bank.getAccounts().containsKey("12345678"));
        assertEquals(actual.getId(), "12345678");
        assertEquals(actual.getApr(), 1.0);
        assertEquals(actual.getAmount(), 0);
        assertTrue(actual instanceof Checking);

    }


}

