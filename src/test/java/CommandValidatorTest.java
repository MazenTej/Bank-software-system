import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandValidatorTest {
    CommandValidator commandValidator;


    @BeforeEach
    void setUp() {
        commandValidator = new CommandValidator();
    }

    @Test
    void validate() {
        boolean actual = commandValidator.validate("create checking 12345678 0.6");
        assertTrue(actual);
    }

    @Test
    void check_for_extra_spaces_in_beginning_returns_false() {
        boolean actual = commandValidator.checkForExtraSpaces("    create checking ");
        assertFalse(actual);
    }

    @Test
    void command_with_spaces_in_the_beginning_is_invalid() {
        boolean actual = commandValidator.validate("   create checking 12345678 0.6");
        assertFalse(actual);
    }

    @Test
    void check_for_extra_spaces_in_the_middle_returns_false() {
        boolean actual = commandValidator.validate(("create    command    is invalid"));
        assertFalse(actual);
    }


    @Test
    void command_with_extra_spaces_in_the_middle_is_invalid() {
        boolean actual = commandValidator.validate("create     command  is invalid");
        assertFalse(actual);


    }

    @Test
    void check_for_extra_spaces_in_the_end_returns_true() {
        boolean actual = commandValidator.checkForExtraSpaces("create checking 12345678     ");
        assertTrue(actual);
    }

    @Test
    void command_with_spaces_at_the_end_is_valid() {
        boolean actual = commandValidator.validate("create checking 12345678 0.6     ");
        assertTrue(actual);
    }


    @Test
    void get_first_word() {
        String actual = commandValidator.getFirstWord("create checking account");
        assertEquals(actual, "create");
    }

    @Test
    void command_is_case_insensitive() {
        boolean actual = commandValidator.validate("CreaTE checKing 12345678 0.6");
        assertTrue(actual);
    }

    @Test
    void create_misspelled_is_invalid() {
        boolean actual = commandValidator.validate("creatte checking 12345678 0.6");
        assertFalse(actual);
    }
}
