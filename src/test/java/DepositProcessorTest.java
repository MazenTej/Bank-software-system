import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositProcessorTest {
    DepositProcessor depositProcessor;

    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        depositProcessor = new DepositProcessor(bank);
    }


    @Test
    void deposit_into_checking_account_is_successful() {
        bank.addCheckingAccount("12345678", 1.0);
        depositProcessor.depositAmount("deposit 12345678 100");
        Account actual = bank.getAccounts().get("12345678");
        assertEquals(actual.getAmount(), 100);
    }


}
