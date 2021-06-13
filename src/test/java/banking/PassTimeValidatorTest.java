package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeValidatorTest {
    PassTimeValidator passTimeValidator;
    Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        passTimeValidator = new PassTimeValidator();
    }

    @Test
    void validatePassTime() {
        boolean actual = passTimeValidator.validatePassTime("Pass 2");
        assertTrue(actual);
    }

    @Test
    void missing_number_of_months_is_invalid() {
        boolean actual = passTimeValidator.validatePassTime("pass");
        assertFalse(actual);
    }

    @Test
    void negative_number_of_months_is_invalid() {
        boolean actual = passTimeValidator.validatePassTime("pass -12");
        assertFalse(actual);

    }

    @Test
    void zero_number_of_months_is_invalid() {
        boolean actual = passTimeValidator.validatePassTime("pass 0");
        assertFalse(actual);

    }

    @Test
    void number_of_months_equal_to_minimum_is_valid() {
        boolean actual = passTimeValidator.validatePassTime("pass 1");
        assertTrue(actual);
    }

    @Test
    void number_of_months_equals_to_maximum_is_valid() {
        boolean actual = passTimeValidator.validatePassTime("pass 60");
        assertTrue(actual);
    }

    @Test
    void number_of_months_in_range_is_valid() {
        boolean actual = passTimeValidator.validatePassTime("pass 50");
        assertTrue(actual);
    }

    @Test
    void number_of_months_more_than_maximum_invalid() {
        boolean actual = passTimeValidator.validatePassTime("pass 65");
        assertFalse(actual);
    }

    @Test
    void number_of_months_not_integer_is_invalid() {
        boolean actual = passTimeValidator.validatePassTime("pass 4ll");
        assertFalse(actual);
    }

}
