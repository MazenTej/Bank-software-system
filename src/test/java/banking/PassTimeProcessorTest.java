package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeProcessorTest {
    PassTimeProcessor passTimeProcessor;
    Bank bank;
    String ID1 = "123456788";
    String ID2 = "10101010";

    @BeforeEach
    void setUp() {
        bank = new Bank();
        passTimeProcessor = new PassTimeProcessor(bank);
    }

    @Test
    void pass_time_closes_empty_accounts_is_successful() {
        bank.addSavingsAccount(ID1, 0.6);
        bank.addCheckingAccount(ID2, 0.6);
        passTimeProcessor.passTime("pass 1");
        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    void pass_time_deducts_fee_from_accounts_with_balance_less_than_a_hundred_and_calculates_apr_evey_month_is_successful() {
        bank.addCheckingAccount(ID1, 0.6);
        bank.addSavingsAccount(ID2, 0.6);
        Account actual1 = bank.retrieveAccount(ID1);
        Account actual2 = bank.retrieveAccount(ID2);
        actual1.setAmount(50);
        actual2.setAmount(90);
        passTimeProcessor.passTime("pass 1");
        assertEquals(actual1.getAmount(), 25.0125);
        assertEquals(actual2.getAmount(), 65.0325);
    }

    @Test
    void pass_time_calculates_apr_every_month_is_successful() {
        bank.addCheckingAccount(ID1, 0.6);
        bank.addCdAccount(ID2, 0.6, 1000);
        Account actual = bank.retrieveAccount(ID1);
        actual.setAmount(5000);
        passTimeProcessor.passTime("pass 1");
        assertEquals(actual.getAmount(), 5002.5);
    }

    @Test
    void pass_time_calculates_apr_four_time_a_month_for_cd_is_successful() {
        bank.addCdAccount(ID1, 0.6, 1000);
        passTimeProcessor.passTime("pass 1");
        Account actual = bank.retrieveAccount(ID1);
        assertEquals(actual.getAmount(), 1002.0015005000625);

    }

    @Test
    void pass_time_deducts_from_account_with_balance_less_than_hundred_until_it_gets_to_zero_and_closes_it_is_successful() {
        bank.addSavingsAccount(ID1, 0.6);
        bank.addCheckingAccount(ID2, 0.6);
        Account actual1 = bank.retrieveAccount(ID1);
        Account actual2 = bank.retrieveAccount(ID2);
        actual2.setAmount(25);
        actual1.setAmount(50);
        passTimeProcessor.passTime("pass 4");
        assertTrue(bank.getAccounts().isEmpty());
    }
}
