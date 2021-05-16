import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandProcessorTest {

    CommandProcessor commandProcessor;
    CreateProcessor createProcessor;
    DepositProcessor depositProcessor;
    Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        createProcessor = new CreateProcessor(bank);
        depositProcessor = new DepositProcessor(bank);
        commandProcessor = new CommandProcessor(createProcessor, depositProcessor);

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

    @Test
    void create_account_and_deposit_into_it_is_successful() {
        commandProcessor.process("create checking 12345678 1.0");
        commandProcessor.process("deposit 12345678 100");
        Account actual = bank.getAccounts().get("12345678");
        assertTrue(bank.getAccounts().containsKey("12345678"));
        assertEquals(actual.getId(), "12345678");
        assertEquals(actual.getApr(), 1.0);
        assertEquals(actual.getAmount(), 100);
        assertTrue(actual instanceof Checking);


    }


}

