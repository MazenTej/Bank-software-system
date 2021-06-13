package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandStorageTest {
    CommandStorage commandStorage;
    Bank bank;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        commandStorage = new CommandStorage(bank);
    }

    @Test
    void commands_is_initially_empty() {
        assertEquals(commandStorage.invalidCommands.size(), 0);

    }

    @Test
    void adding_one_command_and_returning_it_is_successful() {
        commandStorage.addInvalidCommand("creeeta checking 123 1.0");
        assertEquals(commandStorage.getInvalidCommands(), commandStorage.invalidCommands);
        assertEquals(commandStorage.invalidCommands.size(), 1);
        assertEquals(commandStorage.invalidCommands.get(0), "creeeta checking 123 1.0");
    }

    @Test
    void adding_two_commands_and_returning_them_is_successful() {
        commandStorage.addInvalidCommand("deepotst 12345 45");
        commandStorage.addInvalidCommand("deespkt 542 5421");
        assertEquals(commandStorage.getInvalidCommands(), commandStorage.invalidCommands);

        assertEquals(commandStorage.invalidCommands.size(), 2);
        assertEquals(commandStorage.invalidCommands.get(0), "deepotst 12345 45");
        assertEquals(commandStorage.invalidCommands.get(1), "deespkt 542 5421");

    }

    @Test
    void adding_three_commands_and_returning_them_is_successful() {
        commandStorage.addInvalidCommand("deepotst 12345 45");
        commandStorage.addInvalidCommand("deespkt 542 5421");
        commandStorage.addInvalidCommand("creeeta checking 123 1.0");

        assertEquals(commandStorage.getInvalidCommands(), commandStorage.invalidCommands);


        assertEquals(commandStorage.invalidCommands.size(), 3);
        assertEquals(commandStorage.invalidCommands.get(0), "deepotst 12345 45");
        assertEquals(commandStorage.invalidCommands.get(1), "deespkt 542 5421");
        assertEquals(commandStorage.invalidCommands.get(2), "creeeta checking 123 1.0");
    }

    @Test
    void adding_a_valid_command_and_return_it() {
        commandStorage.addTransactionHistory("deposit 12345678 500");
        commandStorage.addTransactionHistory("deposit 12345678 600");
        commandStorage.addTransactionHistory("withdraw 10101010 300");
        commandStorage.addTransactionHistory("transfer 12345678 10101010 500");
        String actual = commandStorage.validCommands.get("12345678").get(0);
        String actual2 = commandStorage.validCommands.get("12345678").get(1);
        String actual3 = commandStorage.validCommands.get("10101010").get(0);
        String actual4 = commandStorage.validCommands.get("10101010").get(1);
        String actual5 = commandStorage.validCommands.get("12345678").get(2);
        assertEquals(actual, "deposit 12345678 500");
        assertEquals(actual2, "deposit 12345678 600");
        assertEquals(actual3, "withdraw 10101010 300");
        assertEquals(actual4, "transfer 12345678 10101010 500");
        assertEquals(actual5, "transfer 12345678 10101010 500");


    }

   /* @Test
    void formatAccountType() {
        bank.addCheckingAccount("12345678", 0.6);
        Account account = bank.retrieveAccount("12345678");
        account.deposit(5000);
        bank.passTime(1);
        String actual = commandStorage.formatAccountState("12345678");
        assertEquals(actual, "Checking 12345678 5002.50 0.60");
    }*/

    /*@Test
    void getOutput() {
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
        String actual = commandStorage.getOutput().get(0);
        String actual2 = commandStorage.getOutput().get(1);
        String actual3 = commandStorage.getOutput().get(2);
        String actual4 = commandStorage.getOutput().get(3);
        String actual5 = commandStorage.getOutput().get(4);
        assertEquals(actual, "Savings 12345678 1000.50 0.60");
        assertEquals(actual2, "Deposit 12345678 700");
        assertEquals(actual3, "Transfer 98765432 12345678 300");
        assertEquals(actual4, "Cd 23456789 2000.00 1.20");
        assertEquals(actual5, "Deposit 12345678 5000");
    }*/


}
