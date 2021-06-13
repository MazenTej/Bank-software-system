package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputTest {
    Output output;
    Bank bank;
    CommandStorage commandStorage;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandStorage = new CommandStorage();
        output = new Output(bank, commandStorage);
    }

    @Test
    void format_account_state_is_successful() {
        bank.addCheckingAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.deposit(5000);
        bank.passTime(1);
        String actual = output.formatAccountState("12345678");
        assertEquals(actual, "Checking 12345678 5002.50 0.60");
    }

    @Test
    void output_is_successful() {
        bank.addSavingsAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        commandStorage.addTransactionHistory("Deposit 12345678 700");
        account.deposit(700);
        commandStorage.addInvalidCommand("Deposit 12345678 5000");
        bank.addCheckingAccount("98765432", 0.01);
        Account account1 = bank.retrieveAccount("98765432");
        commandStorage.addTransactionHistory("Deposit 98765432 300");
        account1.deposit(300);
        commandStorage.addTransactionHistory("Transfer 98765432 12345678 300");
        bank.transferAmount("98765432", "12345678", 300);
        bank.passTime(1);
        commandStorage.addTransactionHistory("Create cd 23456789 1.2 2000");
        bank.addCdAccount("23456789", 1.2, 2000);
        String actual = output.getOutput().get(0);
        String actual2 = output.getOutput().get(1);
        String actual3 = output.getOutput().get(2);
        String actual4 = output.getOutput().get(3);
        String actual5 = output.getOutput().get(4);
        assertEquals(actual, "Savings 12345678 1000.50 0.60");
        assertEquals(actual2, "Deposit 12345678 700");
        assertEquals(actual3, "Transfer 98765432 12345678 300");
        assertEquals(actual4, "Cd 23456789 2000.00 1.20");
        assertEquals(actual5, "Deposit 12345678 5000");
    }


}
