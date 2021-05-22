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
    void create_checking_account_and_deposit_into_it_is_successful() {
        commandProcessor.process("create checking 12345678 1.0");
        commandProcessor.process("deposit 12345678 100");
        Account actual = bank.getAccounts().get("12345678");
        assertTrue(bank.getAccounts().containsKey("12345678"));
        assertEquals(actual.getId(), "12345678");
        assertEquals(actual.getApr(), 1.0);
        assertEquals(actual.getAmount(), 100);
        assertTrue(actual instanceof Checking);


    }

    @Test
    void create_two_checking_and_savings_accounts_and_deposit_into_each_of_them_is_successful() {
        commandProcessor.process("create checking 12345678 1.0");
        commandProcessor.process("deposit 12345678 100");
        commandProcessor.process("create savings 10101010 1.0");
        commandProcessor.process("deposit 10101010 200");


        Account actual1 = bank.getAccounts().get("12345678");
        assertTrue(bank.getAccounts().containsKey("12345678"));
        assertEquals(actual1.getId(), "12345678");
        assertEquals(actual1.getApr(), 1.0);
        assertEquals(actual1.getAmount(), 100);
        assertTrue(actual1 instanceof Checking);
        Account actual2 = bank.getAccounts().get("10101010");
        assertTrue(bank.getAccounts().containsKey("10101010"));
        assertEquals(actual2.getId(), "10101010");
        assertEquals(actual2.getApr(), 1.0);
        assertEquals(actual2.getAmount(), 200);
        assertTrue(actual2 instanceof Savings);


    }

    @Test
    void create_checking_account_and_deposit_twice_into_it_is_successful() {
        commandProcessor.process("create checking 12345678 1.0");
        commandProcessor.process("deposit 12345678 100");
        commandProcessor.process("deposit 12345678 200");

        Account actual = bank.getAccounts().get("12345678");
        assertTrue(bank.getAccounts().containsKey("12345678"));
        assertEquals(actual.getId(), "12345678");
        assertEquals(actual.getApr(), 1.0);
        assertEquals(actual.getAmount(), 300);
        assertTrue(actual instanceof Checking);


    }

    @Test
    void create_savings_account_and_deposit_twice_into_it_is_successful() {
        commandProcessor.process("create savings 12345678 1.0");
        commandProcessor.process("deposit 12345678 100");
        commandProcessor.process("deposit 12345678 200");

        Account actual = bank.getAccounts().get("12345678");
        assertTrue(bank.getAccounts().containsKey("12345678"));
        assertEquals(actual.getId(), "12345678");
        assertEquals(actual.getApr(), 1.0);
        assertEquals(actual.getAmount(), 300);
        assertTrue(actual instanceof Savings);

    }


}

