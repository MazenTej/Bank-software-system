package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawValidatorTest {
    WithdrawValidator withdrawValidator;
    Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        withdrawValidator = new WithdrawValidator(bank);
    }

    @Test
    void validateWithdraw() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 300");
        assertTrue(actual);
    }


    @Test
    void id_with_no_corresponding_account_is_invalid() {
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 500");
        assertFalse(actual);
    }

    @Test
    void missing_id_is_invalid() {
        boolean actual = withdrawValidator.validateWithdraw("withdraw 500");
        assertFalse(actual);
    }

    @Test
    void withdraw_amount_is_not_a_double_is_invalid() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 12ggg");
        assertFalse(actual);
    }

    @Test
    void withdraw_amount_is_missing_is_invalid() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678");
        assertFalse(actual);
    }

    @Test
    void withdraw_from_checking_account_amount_in_dollars_more_than_maximum_is_invalid() {
        bank.addCheckingAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 500");
        assertFalse(actual);

    }

    @Test
    void withdraw_from_checking_account_amount_in_dollars_and_cents_more_than_maximum_is_invalid() {
        bank.addCheckingAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000.11);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 500.12");
        assertFalse(actual);
    }

    @Test
    void withdraw_from_checking_account_negative_amount_in_dollars_is_invalid() {
        bank.addCheckingAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 -500");
        assertFalse(actual);

    }

    @Test
    void withdraw_from_checking_account_negative_amount_in_dollars_and_cents_is_invalid() {
        bank.addCheckingAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000.11);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 -500.12");
        assertFalse(actual);

    }

    @Test
    void withdraw_from_checking_account_zero_amount_is_valid() {
        bank.addCheckingAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000.11);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 0");
        assertTrue(actual);

    }

    @Test
    void withdraw_from_checking_account_equals_to_maximum_amount_is_valid() {
        bank.addCheckingAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000.11);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 400");
        assertTrue(actual);

    }

    @Test
    void withdraw_from_checking_account_amount_in_dollars_in_range_is_valid() {
        bank.addCheckingAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000.11);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 300");
        assertTrue(actual);

    }

    @Test
    void withdraw_from_checking_account_amount_in_dollars_and_cents_in_range_is_valid() {
        bank.addCheckingAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000.11);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 300.15");
        assertTrue(actual);
    }

    @Test
    void withdraw_from_savings_account_amount_in_dollars_more_than_maximum_is_invalid() {
        bank.addSavingsAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 1200");
        assertFalse(actual);

    }

    @Test
    void withdraw_from_savings_account_amount_in_dollars_and_cents_more_than_maximum_is_invalid() {
        bank.addSavingsAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000.11);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 10000.12");
        assertFalse(actual);
    }

    @Test
    void withdraw_from_savings_account_negative_amount_in_dollars_is_invalid() {
        bank.addSavingsAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 -500");
        assertFalse(actual);

    }

    @Test
    void withdraw_from_savings_account_negative_amount_in_dollars_and_cents_is_invalid() {
        bank.addSavingsAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000.11);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 -500.12");
        assertFalse(actual);

    }

    @Test
    void withdraw_from_savings_account_zero_amount_is_valid() {
        bank.addSavingsAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000.11);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 0");
        assertTrue(actual);

    }

    @Test
    void withdraw_from_savings_account_equals_to_maximum_amount_is_valid() {
        bank.addSavingsAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000.11);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 1000");
        assertTrue(actual);

    }

    @Test
    void withdraw_from_savings_account_amount_in_dollars_in_range_is_valid() {
        bank.addSavingsAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000.11);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 300");
        assertTrue(actual);
    }

    @Test
    void withdraw_from_savings_account_amount_in_dollars_and_cents_in_range_is_valid() {
        bank.addSavingsAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000.11);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 300.15");
        assertTrue(actual);
    }

    @Test
    void withdraw_from_savings_once_a_month_is_valid() {
        bank.addSavingsAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000.11);
        bank.passTime(1);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 300.15");
        assertTrue(actual);
    }

    @Test
    void withdraw_from_savings_account_more_than_once_a_month_is_invalid() {
        bank.addSavingsAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.setAmount(1000);
        bank.passTime(1);
        boolean actual1 = withdrawValidator.validateWithdraw("withdraw 12345678 300.15");
        boolean actual2 = withdrawValidator.validateWithdraw("withdraw 12345678 300.15");
        assertFalse(actual2);
        assertTrue(actual1);
    }

    @Test
    void withdraw_from_cd_account_before_twelve_months_is_invalid() {
        bank.addCdAccount("12345678", 0.6, 5000);
        bank.passTime(5);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 200");
        assertFalse(actual);
    }

    @Test
    void withdraw_from_cd_account_after_twelve_months_an_amount_less_than_balance_is_invalid() {
        bank.addCdAccount("12345678", 0.6, 5000);
        bank.passTime(12);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 200");
        assertFalse(actual);
    }

    @Test
    void withdraw_from_cd_account_after_exactly_twelve_months_an_amount_more_than_balance_is_valid() {
        bank.addCdAccount("12345678", 0.6, 5000);
        bank.passTime(12);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 200");
        assertFalse(actual);
    }

    @Test
    void withdraw_from_cd_account_after_twelve_months_an_amount_more_than_balance_is_valid() {
        bank.addCdAccount("12345678", 0.6, 5000);
        bank.passTime(16);
        boolean actual = withdrawValidator.validateWithdraw("withdraw 12345678 200");
        assertFalse(actual);
    }


}
