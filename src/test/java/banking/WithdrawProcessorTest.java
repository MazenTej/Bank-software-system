package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawProcessorTest {
    WithdrawProcessor withdrawProcessor;
    Bank bank;


    String ID1 = "12345678";
    String ID2 = "10101010";


    @BeforeEach
    void setUp() {
        bank = new Bank();
        withdrawProcessor = new WithdrawProcessor(bank);
    }

    @Test
    void withdraw_from_empty_checking_account_is_successful() {
        bank.addCheckingAccount(ID1, 1.0);
        withdrawProcessor.withdrawAmount("withdraw 12345678 100");
        Account actual = bank.retrieveAccount(ID1);
        assertEquals(actual.getAmount(), 0);
    }

    @Test
    void withdraw_from_empty_checking_account_twice_is_successful() {
        bank.addCheckingAccount(ID1, 1.0);
        withdrawProcessor.withdrawAmount("withdraw 12345678 100");
        withdrawProcessor.withdrawAmount("withdraw 12345678 100");
        Account actual = bank.retrieveAccount(ID1);
        assertEquals(actual.getAmount(), 0);
    }

    @Test
    void withdraw_from_empty_savings_account_is_successful() {
        bank.addSavingsAccount(ID1, 1.0);
        withdrawProcessor.withdrawAmount("withdraw 12345678 100");
        Account actual = bank.retrieveAccount(ID1);
        assertEquals(actual.getAmount(), 0);
    }

    @Test
    void withdraw_from_empty_savings_account_twice_is_successful() {
        bank.addCheckingAccount(ID1, 1.0);
        withdrawProcessor.withdrawAmount("withdraw 12345678 100");
        withdrawProcessor.withdrawAmount("withdraw 12345678 100");
        Account actual = bank.retrieveAccount(ID1);
        assertEquals(actual.getAmount(), 0);
    }

    @Test
    void withdraw_from_checking_account_with_balance_is_successful() {
        bank.addCheckingAccount(ID1, 1.0);
        Account account = bank.retrieveAccount(ID1);
        account.setAmount(1000);
        withdrawProcessor.withdrawAmount("withdraw 12345678 400");
        assertEquals(account.getAmount(), 600);
    }

    @Test
    void withdraw_from_savings_account_with_balance_is_successful() {
        bank.addSavingsAccount(ID1, 1.0);
        Account account = bank.retrieveAccount(ID1);
        account.setAmount(1000);
        withdrawProcessor.withdrawAmount("withdraw 12345678 100");
        assertEquals(account.getAmount(), 900);
    }
}
