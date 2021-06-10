import banking.Bank;
import banking.PassTimeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeValidatorTest {
    PassTimeValidator passTimeValidator;
    Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        passTimeValidator = new PassTimeValidator(bank);
    }

    @Test
    void validatePassTime() {
        boolean actual = passTimeValidator.validatePassTime("Pass 2");
        assertTrue(actual);
    }

}
