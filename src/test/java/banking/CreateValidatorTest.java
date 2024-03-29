package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateValidatorTest {
    CreateValidator createValidator;
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        createValidator = new CreateValidator(bank);
    }

    @Test
    void validateCreate() {
        boolean actual = createValidator.validateCreate("create checking 12345678 0.6");
        assertTrue(actual);

    }

    @Test
    void wrong_account_type_is_invalid() {
        boolean actual = createValidator.validateCreate("create deposit 12345678 0.6");
        assertFalse(actual);
    }

    @Test
    void no_account_type_is_invalid() {
        boolean actual = createValidator.validateCreate("create 12345678 0.6");
        assertFalse(actual);
    }

    @Test
    void account_type_is_case_insensitive() {
        boolean actual = createValidator.validateCreate("create saVings 12345678 0.6");
        assertTrue(actual);


    }

    @Test
    void checking_account_is_valid() {
        boolean actual = createValidator.validateCreate("create checking 12345678 0.6");
        assertTrue(actual);
    }

    @Test
    void savings_account_is_valid() {
        boolean actual = createValidator.validateCreate("create savings 12345678 0.6");
        assertTrue(actual);
    }

    @Test
    void cd_account_is_valid() {
        boolean actual = createValidator.validateCreate("create cd 12345678 0.6 5000");
        assertTrue(actual);
    }

    @Test
    void seven_digits_id_is_invalid() {
        boolean actual = createValidator.validateCreate("create savings 1234567 0.6");
        assertFalse(actual);
    }

    @Test
    void nine_digits_id_is_invalid() {
        boolean actual = createValidator.validateCreate("create savings 123456789 0.6");
        assertFalse(actual);
    }

    @Test
    void negative_id_is_invalid() {
        boolean actual = createValidator.validateCreate("create savings -123456789 0.6");
        assertFalse(actual);
    }

    @Test
    void command_with_no_id_is_invalid() {
        boolean actual = createValidator.validateCreate("create savings 0.6");
        assertFalse(actual);

    }

    @Test
    void duplicate_id_is_invalid() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = createValidator.validateCreate("create savings 12345678 0.6 ");
        assertFalse(actual);

    }

    @Test
    void id_in_form_of_float_in_invalid() {
        boolean actual = createValidator.validateCreate("create savings 123456.5 0.6");
        assertFalse(actual);
    }

    @Test
    void non_duplicate_id_with_eight_digits_is_valid() {
        boolean actual = createValidator.validateCreate("create savings 12345678 0.6");
        assertTrue(actual);
    }

    @Test
    void apr_is_not_a_double_is_invalid() {
        boolean actual = createValidator.validateCreate("create savings 12345678 0m5");
        assertFalse(actual);
    }

    @Test
    void apr_bigger_than_maximum_is_invalid() {
        boolean actual = createValidator.validateCreate("create savings 12345678 12");
        assertFalse(actual);
    }

    @Test
    void apr_less_than_zero_is_invalid() {
        boolean actual = createValidator.validateCreate("create savings 12345678 -0.4");
        assertFalse(actual);
    }

    @Test
    void apr_is_maximum_is_valid() {
        boolean actual = createValidator.validateCreate("create savings 12345678 10");
        assertTrue(actual);
    }

    @Test
    void apr_is_zero_is_valid() {
        boolean actual = createValidator.validateCreate("create savings 12345678 0");
        assertTrue(actual);
    }

    @Test
    void apr_in_range_is_valid() {
        boolean actual = createValidator.validateCreate("create savings 12345678 5.6");
        assertTrue(actual);
    }

    @Test
    void no_apr_is_invalid() {
        boolean actual = createValidator.validateCreate("create savings 1234678");
        assertFalse(actual);
    }


    @Test
    void no_amount_input_with_cd_account_is_invalid() {
        boolean actual = createValidator.validateCreate("create cd 12345678 0.6");
        assertFalse(actual);
    }

    @Test
    void amount_input_with_savings_or_checking_is_invalid() {
        boolean actual = createValidator.validateCreate("create savings 12345678 0.6 100");
        assertFalse(actual);
    }

    @Test
    void cd_amount_not_a_double_is_invalid() {
        boolean actual = createValidator.validateCreate("create cd 12345678 0.6 mm100");
        assertFalse(actual);
    }

    @Test
    void cd_amount_bigger_than_maximum_is_invalid() {
        boolean actual = createValidator.validateCreate("create cd 12345678 0.6 1000000");
        assertFalse(actual);
    }

    @Test
    void cd_amount_less_than_minimum_is_invalid() {
        boolean actual = createValidator.validateCreate("create cd 12345678 0.6 100");
        assertFalse(actual);
    }

    @Test
    void cd_amount_is_maximum_is_valid() {
        boolean actual = createValidator.validateCreate("create cd 12345678 0.6 10000");
        assertTrue(actual);
    }

    @Test
    void cd_amount_is_minimum_is_valid() {
        boolean actual = createValidator.validateCreate("create cd 12345678 0.6 1000");
        assertTrue(actual);
    }

    @Test
    void cd_amount_in_range_in_dollars_is_valid() {
        boolean actual = createValidator.validateCreate("create cd 12345678 0.6 5000");
        assertTrue(actual);
    }

    @Test
    void cd_amount_in_range_in_dollars_ad_cents_is_valid() {
        boolean actual = createValidator.validateCreate("create cd 12345678 0.6 5000.12");
        assertTrue(actual);
    }

    @Test
    void cd_amount_less_than_zero_is_invalid() {
        boolean actual = createValidator.validateCreate("create cd 12345678 0.6 -1000");
        assertFalse(actual);
    }


    @Test
    void double_cd_amount_not_in_range_returns_false() {
        boolean actual = createValidator.checkCdAmountInRange("create cd 12345678 0.6 1000000");
        assertFalse(actual);
    }

}

