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
    void deposit_command_has_number_of_elements_different_than_three_returns_false() {
        boolean actual = withdrawValidator.checkWithdrawCommandLength("withdraw 12345678");
        assertFalse(actual);
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


}
