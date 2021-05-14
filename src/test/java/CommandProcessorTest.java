import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandProcessorTest {
    Bank bank;
    CommandProcessor commandProcessor;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandProcessor = new CommandProcessor(bank);


    }

    @Test
    void create_one_checking_account_is_successful() {
        commandProcessor.createAccount("create checking 12345678 1.0");
        Account actual = bank.getAccounts().get("12345678");
        assertTrue(bank.getAccounts().containsKey("12345678"));
        assertEquals(actual.getId(), "12345678");
        assertEquals(actual.getApr(), 1.0);
        assertEquals(actual.getAmount(), 0);
        assertTrue(actual instanceof Checking);
    }

    @Test
    void create_multiple_checking_accounts_is_successful() {
        commandProcessor.createAccount("create checking 12345678 1.0");
        commandProcessor.createAccount("create checking 10101010 2.0");
        commandProcessor.createAccount("create checking 12121212 3.6");
        Account actual1 = bank.getAccounts().get("12345678");
        Account actual2 = bank.getAccounts().get("10101010");
        Account actual3 = bank.getAccounts().get("12121212");
        assertTrue(bank.getAccounts().containsKey("12345678"));
        assertTrue(bank.getAccounts().containsKey("10101010"));
        assertTrue(bank.getAccounts().containsKey("12121212"));
        assertEquals(actual1.getId(), "12345678");
        assertEquals(actual3.getId(), "12121212");
        assertEquals(actual2.getId(), "10101010");
        assertEquals(actual1.getApr(), 1.0);
        assertEquals(actual2.getApr(), 2.0);
        assertEquals(actual3.getApr(), 3.6);
        assertEquals(actual1.getAmount(), 0);
        assertEquals(actual2.getAmount(), 0);
        assertEquals(actual3.getAmount(), 0);
        assertTrue(actual1 instanceof Checking);
        assertTrue(actual2 instanceof Checking);
        assertTrue(actual3 instanceof Checking);

    }

    @Test
    void create_one_savings_account_is_successful() {
        commandProcessor.createAccount("create savings 12345678 1.0");
        Account actual = bank.getAccounts().get("12345678");
        assertTrue(bank.getAccounts().containsKey("12345678"));
        assertEquals(actual.getId(), "12345678");
        assertEquals(actual.getApr(), 1.0);
        assertEquals(actual.getAmount(), 0);
        assertTrue(actual instanceof Savings);

    }

    @Test
    void create_multiple_savings_accounts_is_successful() {
        commandProcessor.createAccount("create savings 12345678 1.0");
        commandProcessor.createAccount("create savings 12121212 2.3");
        commandProcessor.createAccount("create savings 10101010 3.2");
        Account actual1 = bank.getAccounts().get("12345678");
        Account actual2 = bank.getAccounts().get("10101010");
        Account actual3 = bank.getAccounts().get("12121212");
        assertTrue(bank.getAccounts().containsKey("12345678"));
        assertTrue(bank.getAccounts().containsKey("10101010"));
        assertTrue(bank.getAccounts().containsKey("12121212"));
        assertEquals(actual1.getId(), "12345678");
        assertEquals(actual3.getId(), "12121212");
        assertEquals(actual2.getId(), "10101010");
        assertEquals(actual1.getApr(), 1.0);
        assertEquals(actual2.getApr(), 3.2);
        assertEquals(actual3.getApr(), 2.3);
        assertEquals(actual1.getAmount(), 0);
        assertEquals(actual2.getAmount(), 0);
        assertEquals(actual3.getAmount(), 0);
        assertTrue(actual1 instanceof Savings);
        assertTrue(actual2 instanceof Savings);
        assertTrue(actual3 instanceof Savings);

    }

    @Test
    void create_cd_account_is_successful() {
        commandProcessor.createAccount("create cd 12345678 1.0 2000");
        Account actual = bank.getAccounts().get("12345678");
        assertTrue(bank.getAccounts().containsKey("12345678"));
        assertEquals(actual.getId(), "12345678");
        assertEquals(actual.getId(), "12345678");
        assertEquals(actual.getApr(), 1.0);
        assertEquals(actual.getAmount(), 2000);
        assertTrue(actual instanceof Cd);
    }

    @Test
    void create_multiple_cd_accounts_is_successful() {
        commandProcessor.createAccount("create cd 12345678 1.0 2000");
        commandProcessor.createAccount("create cd 12121212 2.3 5000");
        commandProcessor.createAccount("create cd 10101010 3.2 6000");
        Account actual1 = bank.getAccounts().get("12345678");
        Account actual2 = bank.getAccounts().get("10101010");
        Account actual3 = bank.getAccounts().get("12121212");
        assertTrue(bank.getAccounts().containsKey("12345678"));
        assertTrue(bank.getAccounts().containsKey("10101010"));
        assertTrue(bank.getAccounts().containsKey("12121212"));
        assertEquals(actual1.getId(), "12345678");
        assertEquals(actual3.getId(), "12121212");
        assertEquals(actual2.getId(), "10101010");
        assertEquals(actual1.getApr(), 1.0);
        assertEquals(actual2.getApr(), 3.2);
        assertEquals(actual3.getApr(), 2.3);
        assertEquals(actual1.getAmount(), 2000);
        assertEquals(actual2.getAmount(), 6000);
        assertEquals(actual3.getAmount(), 5000);
        assertTrue(actual1 instanceof Cd);
        assertTrue(actual2 instanceof Cd);
        assertTrue(actual3 instanceof Cd);

    }
}
