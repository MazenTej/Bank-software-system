package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferProcessorTest {
    TransferProcessor transferProcessor;
    Bank bank;
    

    String ID1 = "12345678";
    String ID2 = "10101010";

    @BeforeEach
    void setUp() {
        bank = new Bank();
        transferProcessor = new TransferProcessor(bank);
    }

    @Test
    void transfer_from_checking_to_savings_account_successful() {
        bank.addCheckingAccount(ID1, 0.6);
        bank.addSavingsAccount(ID2, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        Account account2 = bank.retrieveAccount(ID2);
        account1.setAmount(1000);
        account2.setAmount(1000);
        transferProcessor.transferAmount("transfer 12345678 10101010 300");
        assertEquals(account2.getAmount(), 1300);
        assertEquals(account1.getAmount(), 700);
    }

    @Test
    void transfer_from_savings_to_checking_account_is_successful() {
        bank.addSavingsAccount(ID1, 0.6);
        bank.addCheckingAccount(ID2, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        Account account2 = bank.retrieveAccount(ID2);
        account1.setAmount(1000);
        account2.setAmount(1000);
        transferProcessor.transferAmount("transfer 12345678 10101010 300");
        assertEquals(account2.getAmount(), 1300);
        assertEquals(account1.getAmount(), 700);
    }

    @Test
    void transfer_from_checking_to_checking_is_successful() {
        bank.addCheckingAccount(ID1, 0.6);
        bank.addCheckingAccount(ID2, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        Account account2 = bank.retrieveAccount(ID2);
        account1.setAmount(1000);
        account2.setAmount(1000);
        transferProcessor.transferAmount("transfer 12345678 10101010 300");
        assertEquals(account2.getAmount(), 1300);
        assertEquals(account1.getAmount(), 700);

    }

    @Test
    void transfer_from_savings_to_savings_is_successful() {
        bank.addCheckingAccount(ID1, 0.6);
        bank.addSavingsAccount(ID2, 0.6);
        Account account1 = bank.retrieveAccount(ID1);
        Account account2 = bank.retrieveAccount(ID2);
        account1.setAmount(1000);
        account2.setAmount(1000);
        transferProcessor.transferAmount("transfer 12345678 10101010 300");
        assertEquals(account2.getAmount(), 1300);
        assertEquals(account1.getAmount(), 700);

    }


}
