package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositProcessorTest {
    DepositProcessor depositProcessor;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        depositProcessor = new DepositProcessor(bank);
    }


    @Test
    void deposit_into_empty_checking_account_is_successful() {
        bank.addCheckingAccount("12345678", 1.0);
        depositProcessor.depositAmount("deposit 12345678 100");
        Account actual = bank.getAccounts().get("12345678");
        assertEquals(actual.getAmount(), 100);
    }

    @Test
    void deposit_into_empty_checking_account_twice_is_successful() {
        bank.addCheckingAccount("12345678", 1.0);
        depositProcessor.depositAmount("deposit 12345678 100");
        depositProcessor.depositAmount("deposit 12345678 200");
        Account actual = bank.getAccounts().get("12345678");
        assertEquals(actual.getAmount(), 300);

    }

    @Test
    void deposit_into_empty_savings_account_is_successful() {
        bank.addSavingsAccount("12345678", 1.0);
        depositProcessor.depositAmount("deposit 12345678 100");
        Account actual = bank.getAccounts().get("12345678");
        assertEquals(actual.getAmount(), 100);
    }

    @Test
    void deposit_twice_into_empty_savings_account_is_successful() {
        bank.addSavingsAccount("12345678", 1.0);
        depositProcessor.depositAmount("deposit 12345678 100");
        depositProcessor.depositAmount("deposit 12345678 200");
        Account actual = bank.getAccounts().get("12345678");
        assertEquals(actual.getAmount(), 300);
    }

    @Test
    void deposit_into_checking_account_with_balance_is_successful() {
        bank.addCheckingAccount("12345678", 1.0);
        Account actual = bank.getAccounts().get("12345678");
        actual.setAmount(200);
        depositProcessor.depositAmount("deposit 12345678 200");
        assertEquals(actual.getAmount(), 400);


    }

    @Test
    void deposit_into_savings_account_with_balance_is_successful() {
        bank.addSavingsAccount("12345678", 1.0);
        Account actual = bank.getAccounts().get("12345678");
        actual.setAmount(200);
        depositProcessor.depositAmount("deposit 12345678 200");
        assertEquals(actual.getAmount(), 400);
    }

}
