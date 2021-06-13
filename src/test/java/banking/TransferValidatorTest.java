package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferValidatorTest {
    TransferValidator transferValidator;
    Bank bank;

    String ID1 = "12345678";
    String ID2 = "10101010";

    @BeforeEach
    void setUp() {
        bank = new Bank();
        transferValidator = new TransferValidator(bank);
    }

    @Test
    void validateTransfer() {
        bank.addCheckingAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 10101010 400");
        assertTrue(actual);
    }

    @Test
    void from_id_with_no_corresponding_account_is_invalid() {
        bank.addCheckingAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 51222235 10101010 400");
        assertFalse(actual);

    }

    @Test
    void to_id_with_no_corresponding_account_is_invalid() {
        bank.addCheckingAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 21000001 400");
        assertFalse(actual);

    }

    @Test
    void missing_from_id_is_invalid() {
        bank.addCheckingAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 10101010 400");
        assertFalse(actual);

    }

    @Test
    void missing_to_id_is_invalid() {
        bank.addCheckingAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 400");
        assertFalse(actual);

    }

    @Test
    void transfer_amount_is_not_a_double_is_invalid() {
        bank.addCheckingAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 10101010 400sad");
        assertFalse(actual);

    }

    @Test
    void missing_transfer_amount_is_invalid() {
        bank.addCheckingAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 51222235 10101010");
        assertFalse(actual);

    }

    @Test
    void transfer_from_cd_is_invalid() {
        bank.addCdAccount(ID1, 0.6, 1000);
        bank.addCheckingAccount(ID2, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 10101010 1000");
        assertFalse(actual);
    }

    @Test
    void transfer_into_cd_is_invalid() {
        bank.addCdAccount(ID1, 0.6, 1000);
        bank.addCheckingAccount(ID2, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 10101010 12345678 1000");
        assertFalse(actual);
    }

    @Test
    void transfer_amount_from_checking_is_more_than_maximum_is_invalid() {
        bank.addCheckingAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 10101010 500");
        assertFalse(actual);
    }

    @Test
    void transfer_amount_from_savings_more_than_maximum_is_invalid() {
        bank.addCheckingAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 10101010 12345678 1500");
        assertFalse(actual);
    }

    @Test
    void transfer_amount_from_checking_equals_to_maximum_is_valid() {
        bank.addCheckingAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 10101010 400");
        assertTrue(actual);
    }

    @Test
    void transfer_amount_from_savings_equals_to_maximum_is_valid() {
        bank.addSavingsAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(2000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 10101010 1000");
        assertTrue(actual);
    }

    @Test
    void transfer_amount_from_checking_equals_to_zero_is_valid() {
        bank.addCheckingAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 10101010 0");
        assertTrue(actual);
    }

    @Test
    void transfer_amount_from_savings_equals_to_zero_is_valid() {
        bank.addCheckingAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 10101010 12345678 0");
        assertTrue(actual);
    }

    @Test
    void transfer_amount_from_checking_in_range_is_valid() {
        bank.addCheckingAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 10101010 300");
        assertTrue(actual);
    }

    @Test
    void transfer_amount_from_savings_in_range_is_valid() {
        bank.addSavingsAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 10101010 700");
        assertTrue(actual);
    }

    @Test
    void transfer_amount_to_checking_more_than_maximum_is_invalid() {
        bank.addSavingsAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(1000);
        bank.addCheckingAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 10101010 1200");
        assertFalse(actual);
    }

    @Test
    void transfer_amount_to_savings_more_than_maximum_is_invalid() {
        bank.addSavingsAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(5000);
        bank.addSavingsAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 10101010 3000");
        assertFalse(actual);
    }

    @Test
    void transfer_amount_to_checking_equals_maximum_is_valid() {
        bank.addSavingsAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(2000);
        bank.addCheckingAccount(ID2, 0.6);
        Account account2 = bank.retrieveAccount(ID2);
        account2.setAmount(1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 10101010 1000");
        assertTrue(actual);
    }

    @Test
    void transfer_amount_to_cd_is_invalid() {
        bank.addSavingsAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(2000);
        bank.addCdAccount(ID2, 0.6, 1000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 10101010 1000");
        assertFalse(actual);
    }

    @Test
    void transfer_amount_from_cd_is_invalid() {
        bank.addSavingsAccount(ID1, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        account1.setAmount(2000);
        bank.addCdAccount(ID2, 0.6, 1000);
        boolean actual = transferValidator.validateTransfer("transfer 10101010 12345678 1000");
        assertFalse(actual);

    }

    @Test
    void transfer_to_the_same_Account_is_invalid() {
        bank.addSavingsAccount(ID1, 0.6);
        Account account = bank.retrieveAccount(ID1);
        account.setAmount(2000);
        boolean actual = transferValidator.validateTransfer("transfer 12345678 12345678 1000");
        assertFalse(actual);
    }

}
