import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void account_type_is_case_incensitive() {
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
    void apr_is_not_a_double_is_invalid() {
        boolean actual = createValidator.validateCreate("create savings 12345678 0m5");
        assertFalse(actual);
    }

    @Test
    void get_second_word_in_command_with_no_extra_spaces() {
        String actual = createValidator.getSecondWord("create checking 12345678 0.6");
        assertEquals("checking", actual);

    }

    @Test
    void get_third_word_in_command_with_no_extra_spaces() {
        String actual = createValidator.getThirdWord("create checking 12345678 0.6");
        assertEquals("12345678", actual);
    }

    @Test
    void test_id_length_returns_false_if_different_than_eight() {
        boolean actual = createValidator.checkIdLength("create checking 123457 0.6");
        assertFalse(actual);

    }

    @Test
    void string_with_characteres_other_than_numbers_returns_false() {
        boolean actual = createValidator.checkIdHasOnlyNumbers("create checking 1234ma56 0.6");
        assertFalse(actual);
    }

    @Test
    void get_fourth_word_in_command_with_no_extra_spaces() {
        String actual = createValidator.getFourthWord("create checking 12345678 0.6");
        assertEquals(actual, "0.6");
    }

    @Test
    void apr_is_not_a_double_returns_false() {
        boolean actual = createValidator.checkDouble("create checking 12345678 nv0.6");
        assertFalse(actual);
    }


}

