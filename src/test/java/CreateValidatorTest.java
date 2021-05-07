import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateValidatorTest {
    CreateValidator createValidator;


    @BeforeEach
    void setUp() {
        createValidator = new CreateValidator();
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
    void account_type_is_case_insenstive() {
        boolean actual = createValidator.validateCreate("create saVings 12345678 0.6");
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

}

