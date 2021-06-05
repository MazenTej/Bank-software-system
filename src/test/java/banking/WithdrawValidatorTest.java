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


}
