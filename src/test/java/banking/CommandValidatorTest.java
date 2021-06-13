package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandValidatorTest {
    CommandValidator commandValidator;
    CreateValidator createValidator;
    DepositValidator depositValidator;
    WithdrawValidator withdrawValidator;
    TransferValidator transferValidator;
    PassTimeValidator passTimeValidator;
    Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        createValidator = new CreateValidator(bank);
        depositValidator = new DepositValidator(bank);
        withdrawValidator = new WithdrawValidator(bank);
        transferValidator = new TransferValidator(bank);
        passTimeValidator = new PassTimeValidator();
        commandValidator = new CommandValidator(createValidator, depositValidator, withdrawValidator, transferValidator, passTimeValidator);
    }

    @Test
    void validate() {
        boolean actual = commandValidator.validate("create checking 12345678 0.6");
        assertTrue(actual);
    }


    @Test
    void command_with_spaces_in_the_beginning_is_invalid() {
        boolean actual = commandValidator.validate("   create checking 12345678 0.6");
        assertFalse(actual);
    }

    @Test
    void create_misspelled_is_invalid() {
        boolean actual = commandValidator.validate("creatte checking 12345678 0.6");
        assertFalse(actual);
    }


    @Test
    void deposit_misspelled_is_invalid() {
        boolean actual = commandValidator.validate("depositt 12345678 2500");
        assertFalse(actual);
    }

    @Test
    void withdraw_misspelled_is_invalid() {
        boolean actual = commandValidator.validate("withdraww 12345678 300");
        assertFalse(actual);
    }

    @Test
    void transfer_misspelled_is_invalid() {
        boolean actual = commandValidator.validate("ttransfer 12345678 10101010 400");
        assertFalse(actual);
    }

    @Test
    void pass_misspelled_is_invalid() {
        boolean actual = commandValidator.validate("passs 1");
        assertFalse(actual);
    }

    @Test
    void missing_create_is_invalid() {
        boolean actual = commandValidator.validate("checking 12345678 0.6");
        assertFalse(actual);
    }

    @Test
    void missing_deposit_is_invalid() {
        boolean actual = commandValidator.validate("12345678 1000");
        assertFalse(actual);
    }

    @Test
    void missing_withdraw_is_invalid() {
        boolean actual = commandValidator.validate("12345678 100");
        assertFalse(actual);
    }

    @Test
    void missing_transfer_is_invalid() {
        boolean actual = commandValidator.validate("12345678 10101010 400");
        assertFalse(actual);
    }

    @Test
    void missing_pass_is_invalid() {
        boolean actual = commandValidator.validate("1");
        assertFalse(actual);
    }

    @Test
    void command_with_extra_spaces_in_the_middle_is_invalid() {
        boolean actual = commandValidator.validate("create     checking   12345678  0.6");
        assertFalse(actual);
    }

    @Test
    void command_with_spaces_at_the_end_is_valid() {
        boolean actual = commandValidator.validate("create savings 12345678 0.6    ");
        assertTrue(actual);
    }

    @Test
    void command_create_is_case_insensitive() {
        boolean actual = commandValidator.validate("Create checking 12345678 0.6");
        assertTrue(actual);
    }

    @Test
    void command_deposit_is_case_insensitive() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = commandValidator.validate("Deposit 12345678 1000");
        assertTrue(actual);
    }

    @Test
    void command_pass_is_case_insensitive() {
        boolean actual = commandValidator.validate("Pass 1");
        assertTrue(actual);
    }

    @Test
    void command_withdraw_is_case_insensitive() {
        bank.addCheckingAccount("12345678", 0.6);
        boolean actual = commandValidator.validate("Withdraw 12345678 0");
        assertTrue(actual);
    }

    @Test
    void check_for_extra_spaces_in_the_middle_returns_false() {
        boolean actual = commandValidator.checkForExtraSpaces("create    command    is invalid");
        assertFalse(actual);
    }

    @Test
    void command_transfer_is_case_insensitive() {
        bank.addCheckingAccount("12345678", 0.6);
        bank.addCheckingAccount("10101010", 0.6);
        boolean actual = commandValidator.validate("Transfer 12345678 10101010 0");
        assertTrue(actual);


    }

    @Test
    void check_for_extra_spaces_in_the_beginning_returns_false() {
        boolean actual = commandValidator.checkForExtraSpaces("  create command is invalid");
        assertFalse(actual);

    }


    @Test
    void check_for_extra_spaces_in_the_end_returns_true() {
        boolean actual = commandValidator.checkForExtraSpaces("create checking 12345678 0.6    ");
        assertTrue(actual);
    }


    @Test
    void check_for_extra_spaces_in_beginning_returns_false() {
        boolean actual = commandValidator.checkForExtraSpaces("    create checking 12345678 0.6 ");
        assertFalse(actual);
    }


}
