package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParsingTest {
    Parsing parsing;

    @BeforeEach
    void setUp() {
        parsing = new Parsing();
    }

    @Test
    void get_first_word_in_command_with_no_extra_spaces() {
        String actual1 = parsing.getFirstWord("create checking 12345678 0.6");
        String actual2 = parsing.getFirstWord("deposit 12345678 1000");
        String actual3 = parsing.getFirstWord("withdraw 12345678 300");
        assertEquals(actual1, "create");
        assertEquals(actual2, "deposit");
        assertEquals(actual3, "withdraw");

    }

    @Test
    void get_second_word_in_command_with_no_extra_spaces() {
        String actual1 = parsing.getSecondWord("create checking 12345678 0.6");
        String actual2 = parsing.getSecondWord("deposit 12345678 1000");
        String actual3 = parsing.getSecondWord("withdraw 12345678 300");
        assertEquals(actual1, "checking");
        assertEquals(actual2, "12345678");
        assertEquals(actual3, "12345678");

    }

    @Test
    void get_third_word_in_command_with_no_extra_spaces() {
        String actual1 = parsing.getThirdWord("create checking 12345678 0.6");
        String actual2 = parsing.getThirdWord("deposit 12345678 1000");
        String actual3 = parsing.getThirdWord("withdraw 12345678 300");
        assertEquals(actual1, "12345678");
        assertEquals(actual2, "1000");
        assertEquals(actual3, "300");
    }

    @Test
    void get_fourth_word_in_command_with_no_extra_spaces() {
        String actual = parsing.getFourthWord("create checking 12345678 0.6");
        assertEquals(actual, "0.6");
    }

    @Test
    void get_fifth_word_in_command_with_no_extra_spaces() {
        String actual = parsing.getFifthWord("create cd 12345678 0.6 1000");
        assertEquals("1000", actual);
    }

}
