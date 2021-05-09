import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepositValidatorTest {
    DepositValidator depositValidator;
    Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        depositValidator = new DepositValidator(bank);
    }

    @Test
    void validateDeposit() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 500");
        assertTrue(actual);
    }

    @Test
    void id_with_no_corresponding_account_is_invalid() {
        boolean actual = depositValidator.validateDeposit("deposit 12345678 500");
        assertFalse(actual);
    }

    @Test
    void missing_id_is_invalid() {
        boolean actual = depositValidator.validateDeposit("deposit 500");
        assertFalse(actual);
    }

    @Test
    void deposit_amount_is_not_a_double_is_invalid() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 12ggg");
        assertFalse(actual);
    }

    @Test
    void deposit_amount_is_missing_is_invalid() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678");
        assertFalse(actual);
    }

    @Test
    void deposit_into_cd_is_invalid() {
        bank.addCdAccount("12345678", 0.6, 1000);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 1000");
        assertFalse(actual);
    }

    @Test
    void deposit_into_savings_account_amount_in_dollars_more_than_maximum_is_invalid() {
        bank.addSavingsAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 5000");
        assertFalse(actual);
    }

    @Test
    void deposit_into_savings_account_amount_in_dollars_and_cents_more_than_maximum_is_invalid() {
        bank.addSavingsAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 5000.12");
        assertFalse(actual);
    }

    @Test
    void deposit_into_savings_account_negative_amount_is_invalid() {
        bank.addSavingsAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 -1000");
        assertFalse(actual);
    }

    @Test
    void deposit_zero_amount_into_savings_account_is_valid() {
        bank.addSavingsAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 0");
        assertTrue(actual);
    }

    @Test
    void deposit_into_savings_account_amount_equals_maximum_is_valid() {
        bank.addSavingsAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 2500");
        assertTrue(actual);
    }

    @Test
    void deposit_into_savings_account_amount_in_dollars_range_is_valid() {
        bank.addSavingsAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 1000");
        assertTrue(actual);
    }

    @Test
    void deposit_into_savings_account_amount_in_dollars_and_cents_range_is_valid() {
        bank.addSavingsAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 1000.12");
        assertTrue(actual);
    }


    @Test
    void deposit_into_checking_account_amount_in_dollars_more_than_maximum_is_invalid() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 20000");
        assertFalse(actual);
    }

    @Test
    void deposit_into_checking_account_amount_in_dollars_and_cents_more_than_maximum_is_invalid() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 20000.22");
        assertFalse(actual);
    }

    @Test
    void deposit_into_checking_account_negative_amount_is_invalid() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 -1000");
        assertFalse(actual);
    }

    @Test
    void deposit_zero_amount_into_checking_account_is_valid() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 0");
        assertTrue(actual);
    }

    @Test
    void deposit_into_checking_account_amount_equals_maximum_is_valid() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 1000");
        assertTrue(actual);
    }

    @Test
    void deposit_into_checking_account_amount_in_dollars_in_range_is_valid() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 500");
        assertTrue(actual);
    }

    @Test
    void deposit_into_checking_account_amount_in_dollars_and_cents_in_range_is_valid() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = depositValidator.validateDeposit("deposit 12345678 500.12");
        assertTrue(actual);
    }


    @Test
    void id_with_no_corresponding_account_returns_false() {
        boolean actual = depositValidator.checkDepositId("deposit 12345678 500");
        assertFalse(actual);
    }

    @Test
    void deposit_command_has_number_of_elements_different_than_three_returns_false() {
        boolean actual = depositValidator.checkDepositCommandLength("deposit 12345678");
        assertFalse(actual);
    }


    @Test
    void deposit_amount_is_not_a_double_returns_false() {
        boolean actual = depositValidator.checkDepositAmountDouble("deposit 12345678 50gdg");
        assertFalse(actual);
    }

    @Test
    void check_account_returns_false_for_wrong_account_type() {
        bank.addCdAccount("12345678", 0.6, 1000);
        boolean actual = depositValidator.checkValidDeposit("deposit 12345678 1000");
        assertFalse(actual);


    }


}
