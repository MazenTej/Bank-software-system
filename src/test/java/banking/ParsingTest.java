package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParsingTest {
    Parsing parsing;

    @BeforeEach
    void setUp() {
        parsing = new Parsing();
    }

    @Test
    void get_first_word_in_command_with_no_extra_spaces() {
        String actual1 = parsing.getFirstWord("create");
        String actual2 = parsing.getFirstWord("deposit 12345678 1000");
        String actual3 = parsing.getFirstWord("withdraw 12345678 300");
        String actual4 = parsing.getFirstWord("transfer 12345678 10101010 300");
        String actual5 = parsing.getFirstWord("pass 12");
        assertEquals(actual1, "create");
        assertEquals(actual2, "deposit");
        assertEquals(actual3, "withdraw");
        assertEquals(actual4, "transfer");
        assertEquals(actual5, "pass");

    }

    @Test
    void get_second_word_in_command_with_no_extra_spaces() {
        String actual1 = parsing.getSecondWord("create checking 12345678 0.6");
        String actual2 = parsing.getSecondWord("deposit 12345678 1000");
        String actual3 = parsing.getSecondWord("withdraw 12345678 300");
        String actual4 = parsing.getSecondWord("transfer 12345678 10101010 300");
        String actual5 = parsing.getSecondWord("pass 12");
        assertEquals(actual1, "checking");
        assertEquals(actual2, "12345678");
        assertEquals(actual3, "12345678");
        assertEquals(actual4, "12345678");
        assertEquals(actual5, "12");

    }

    @Test
    void get_third_word_in_command_with_no_extra_spaces() {
        String actual1 = parsing.getThirdWord("create checking 12345678 0.6");
        String actual2 = parsing.getThirdWord("deposit 12345678 1000");
        String actual3 = parsing.getThirdWord("withdraw 12345678 300");
        String actual4 = parsing.getThirdWord("transfer 12345678 10101010 300");

        assertEquals(actual1, "12345678");
        assertEquals(actual2, "1000");
        assertEquals(actual3, "300");
        assertEquals(actual4, "10101010");
    }

    @Test
    void get_fourth_word_in_command_with_no_extra_spaces() {
        String actual1 = parsing.getFourthWord("create checking 12345678 0.6");
        String actual2 = parsing.getFourthWord("transfer 12345678 10101010 300");
        assertEquals(actual1, "0.6");
        assertEquals(actual2, "300");

    }

    @Test
    void get_fifth_word_in_command_with_no_extra_spaces() {
        String actual = parsing.getFifthWord("create cd 12345678 0.6 1000");

        assertEquals("1000", actual);
    }

    @Test
    void check_id_length_returns_false_if_length_not_eight() {
        boolean actual = parsing.checkIdLength("123");
        assertFalse(actual);
    }

    @Test
    void check_id_has_only_numbers_returns_false_if_id_has_characters_other_than_numbers() {
        boolean actual = parsing.checkIdHasOnlyNumbers("12aa3456");
        assertFalse(actual);
    }

    @Test
    void check_string_double_returns_false_if_string_can_not_be_parsed_to_double() {
        boolean actual = parsing.checkStringDouble("1a");
        assertFalse(actual);
    }

    @Test
    void check_apr_range_returns_false_if_apr_not_in_range() {
        boolean actual = parsing.checkAprRange("15");
        assertFalse(actual);
    }

    @Test
    void check_command_length_returns_false_if_lengths_do_not_match() {
        boolean actual = parsing.checkCommandLength("create checking 12345678", 4);
        assertFalse(actual);
    }


}
