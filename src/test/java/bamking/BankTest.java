package bamking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {
    public static final String ID = "12345678";
    public static final double APR = 0.6;
    public static final String ID2 = "87654321";
    public static final String ID3 = "13245678";
    public static final double DOLLARS_AMOUNT_DEPOSITED = 1000;
    public static final double DOLLARS_AND_CENTS_AMOUNT_DEPOSITED = 1000.63;
    public static final double DOLLARS_AMOUNT_DEPOSITED_1 = 400;
    public static final double DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1 = 400.645;
    public static final double DOLLARS_AMOUNT_DEPOSITED_2 = 300;
    public static final double DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_2 = 300.123;
    public static final double DOLLARS_AMOUNT_WITHDRAWN = 500;
    public static final double DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN = 500.125;
    public static final double AMOUNT_CD = 700.265;
    public static final double AMOUNT = 0;
    public static final double DOLLARS_AMOUNT_SET = 1000;
    public static final double DOLLARS_AND_CENTS_AMOUNT_SET = 1000.123;

    public static final double DOLLARS_AMOUNT_SET_2 = 300;
    public static final double DOLLARS_AND_CENTS_AMOUNT_SET_2 = 300.123;


    Bank bank;

    Cd cd;
    Checking checking;
    Savings savings;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        checking = new Checking(ID, APR);
        savings = new Savings(ID, APR);
        cd = new Cd(ID, APR, AMOUNT_CD);
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

    @Test
    void checking_account_has_zero_amount_initially() {
        bank.addCheckingAccount(ID, APR);
        assertEquals(AMOUNT, bank.getAccounts().get(ID).getAmount());


    }

    @Test
    void deposit_dollars_amount_into_empty_checking_account() {
        bank.addCheckingAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED, actual.getAmount());

    }

    @Test
    void deposit_dollars_and_cents_amount_into_empty_checking_account() {
        bank.addCheckingAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AND_CENTS_AMOUNT_DEPOSITED);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED, actual.getAmount());

    }

    @Test
    void deposit_dollars_amounts_twice_into_checking() {
        bank.addCheckingAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED_1);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED_2);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED_1 + DOLLARS_AMOUNT_DEPOSITED_2, actual.getAmount());


    }

    @Test
    void deposit_dollars_and_cents_amounts_twice_into_checking_account() {
        bank.addCheckingAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1);
        bank.depositAmount(ID, DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_2);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1 + DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_2, actual.getAmount());


    }

    @Test
    void deposit_into_checking_account_with_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        actual.setAmount(DOLLARS_AMOUNT_SET);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED);
        assertEquals(DOLLARS_AMOUNT_SET + DOLLARS_AMOUNT_DEPOSITED, actual.getAmount());
    }

    @Test
    void withdraw_dollars_amount_less_than_checking_account_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        actual.setAmount(DOLLARS_AMOUNT_SET);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AMOUNT_WITHDRAWN, actual.getAmount());


    }

    @Test
    void withdraw_dollars_and_cents_amount_less_than_checking_account_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        actual.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);
        bank.withdrawAmount(ID, DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET - DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN, actual.getAmount());


    }

    @Test
    void withdraw_dollars_amount_more_than_checking_account_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        actual.setAmount(DOLLARS_AMOUNT_SET_2);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, actual.getAmount());

    }

    @Test
    void withdraw_dollars_and_cents_amount_more_than_checking_account_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        actual.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.withdrawAmount(ID, DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, actual.getAmount());

    }

    @Test
    void withdraw_from_empty_checking_account() {
        bank.addCheckingAccount(ID, APR);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        assertEquals(AMOUNT, actual.getAmount());


    }

    @Test
    void withdraw_twice_from_checking_account_a_total_amount_more_than_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        actual.setAmount(DOLLARS_AMOUNT_SET);

        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        bank.withdrawAmount(ID, DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, actual.getAmount());
    }

    @Test
    void savings_account_has_zero_amount_initially() {
        assertEquals(AMOUNT, savings.getAmount());
    }

    @Test
    void deposit_dollars_amount_into_empty_savings_account() {
        bank.addSavingsAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED, actual.getAmount());
    }

    @Test
    void deposit_dollars_and_cents_amount_into_empty_savings_account() {
        bank.addSavingsAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AND_CENTS_AMOUNT_DEPOSITED);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED, actual.getAmount());
    }


    @Test
    void deposit_dollars_amounts_twice_into_savings_account() {
        bank.addSavingsAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED_1);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED_2);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED_1 + DOLLARS_AMOUNT_DEPOSITED_2, actual.getAmount());
    }

    @Test
    void deposit_dollars_and_cents_amounts_twice_into_savings_account() {
        bank.addSavingsAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_2);
        bank.depositAmount(ID, DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1 + DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_2, actual.getAmount());
    }

    @Test
    void deposit_into_savings_account_with_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        actual.setAmount(DOLLARS_AMOUNT_SET);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED);
        assertEquals(DOLLARS_AMOUNT_SET + DOLLARS_AMOUNT_DEPOSITED, actual.getAmount());
    }

    @Test
    void withdraw_dollars_amount_less_than_savings_account_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        actual.setAmount(DOLLARS_AMOUNT_SET);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AMOUNT_WITHDRAWN, actual.getAmount());


    }

    @Test
    void withdraw_dollars_and_cents_amount_less_than_savings_account_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        actual.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);

        bank.withdrawAmount(ID, DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET - DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN, actual.getAmount());


    }

    @Test
    void withdraw_dollars_amount_more_than_savings_account_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        actual.setAmount(DOLLARS_AMOUNT_SET_2);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, actual.getAmount());

    }

    @Test
    void withdraw_dollars_and_cents_amount_more_than_savings_account_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        actual.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.withdrawAmount(ID, DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, actual.getAmount());

    }

    @Test
    void withdraw_from_empty_savings_account() {
        bank.addSavingsAccount(ID, APR);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        assertEquals(AMOUNT, actual.getAmount());


    }

    @Test
    void withdraw_twice_from_savings_account_a_total_more_than_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        actual.setAmount(DOLLARS_AMOUNT_SET);

        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        bank.withdrawAmount(ID, DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, actual.getAmount());
    }

    @Test
    void cd_has_amount_equal_to_amount_declared_in_command() {
        bank.addCdAccount(ID, APR, AMOUNT_CD);
        assertEquals(AMOUNT_CD, cd.getAmount());
    }


}