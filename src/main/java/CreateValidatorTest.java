import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateValidatorTest {
    CreateValidator createValidator;

    @BeforeEach
    void setUp() {
        createValidator = new CreateValidator();
    }

    @Test
    void validate() {
        boolean actual = createValidator.validateCreate("create checking 12345678 0.6");
        assertTrue(actual);
    }
}
