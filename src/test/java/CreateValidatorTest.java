import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateValidatorTest {
    CreateValidator createValidator;


    @BeforeEach
    void setYp() {
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
        boolean actual = createValidator.validateCreate("create saVnoings 12345678 0.6");
        assertTrue(actual);


    }

    @Test
    void get_second_word_in_command_with_no_extra_spaces() {
        String actual = createValidator.getSecondWord("create checking 12345678 0.6");
        assertEquals("checking", actual);

    }
}
