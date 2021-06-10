import banking.Bank;
import banking.PassTimeValidator;
import org.junit.jupiter.api.BeforeEach;

public class PassTimeValidatorTest {
    PassTimeValidator passTimeValidator;
    Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        passTimeValidator = new PassTimeValidator(bank);
    }
}
