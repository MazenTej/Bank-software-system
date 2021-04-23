import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {
    public static final String ID = "12345678";
    public static final double APR = 0.6;
    public static final String ID2 = "87654321";
    public static final String ID3 = "13245678";
    public static final double AMOUNT_CD = 1000;


    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    void bank_has_no_accounts_initially() {

        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    void add_checking_account_to_bank() {
        bank.addCheckingAccount(ID, APR);
        assertTrue(bank.getAccounts().containsKey(ID));
    }

    @Test
    void add_two_checking_accounts_to_bank() {
        bank.addCheckingAccount(ID, APR);
        bank.addCheckingAccount(ID2, APR);
        assertTrue(bank.getAccounts().containsKey(ID));

        assertTrue(bank.getAccounts().containsKey(ID2));
    }

    @Test
    void add_savings_account_to_bank() {
        bank.addSavingsAccount(ID, APR);
        assertTrue(bank.getAccounts().containsKey(ID));
    }

    @Test
    void add_two_savings_accounts_to_bank() {
        bank.addSavingsAccount(ID, APR);
        bank.addSavingsAccount(ID2, APR);
        assertTrue(bank.getAccounts().containsKey(ID));

        assertTrue(bank.getAccounts().containsKey(ID2));
    }

    @Test
    void add_savings_account_and_checking_account() {
        bank.addSavingsAccount(ID, APR);
        bank.addCheckingAccount(ID2, APR);
        assertTrue(bank.getAccounts().containsKey(ID2));

    }

    @Test
    void add_cd_account_to_bank() {
        bank.addCdAccount(ID, APR, AMOUNT_CD);
        assertTrue(bank.getAccounts().containsKey(ID));
    }

    @Test
    void add_cd_account_and_savings_account_and_checking_account_to_bank() {
        bank.addSavingsAccount(ID, APR);
        bank.addCdAccount(ID2, APR, AMOUNT_CD);
        bank.addCheckingAccount(ID3, APR);
        assertTrue(bank.getAccounts().containsKey(ID));
        assertTrue(bank.getAccounts().containsKey(ID2));
        assertTrue(bank.getAccounts().containsKey(ID3));

    }


}