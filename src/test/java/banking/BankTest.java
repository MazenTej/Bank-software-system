package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public static final double DOLLARS_AMOUNT_TRANSFERRED_1 = 400;
    public static final double DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1 = 400.645;
    public static final double DOLLARS_AMOUNT_TRANSFERRED_2 = 1500;
    public static final double DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_2 = 1500.645;


    Bank bank;


    Cd cd;
    Checking checking;
    Savings savings;


    @Test

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
        bank.addSavingsAccount(ID, APR);
        assertEquals(AMOUNT, bank.getAccounts().get(ID).getAmount());
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

    @Test
    void transfer_from_checking_account_amount_in_dollars_less_than_balance_to_empty_savings_account() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AMOUNT_TRANSFERRED_1, actual2.getAmount());
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AMOUNT_TRANSFERRED_1, actual1.getAmount());

    }

    @Test
    void transfer_from_savings_account_amount_in_dollars_less_than_balance_to_empty_checking_account() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AMOUNT_TRANSFERRED_1, actual2.getAmount());
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AMOUNT_TRANSFERRED_1, actual1.getAmount());

    }

    @Test
    void transfer_from_checking_account_amount_in_dollars_and_cents_less_than_balance_to_empty_savings_account() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1, actual2.getAmount());
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1, actual1.getAmount());

    }

    @Test
    void transfer_from_savings_account_amount_in_dollars_and_cents_less_than_balance_to_empty_checking_account() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1, actual2.getAmount());
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1, actual1.getAmount());

    }

    @Test
    void transfer_from_checking_account_amount_in_dollars_less_than_balance_to_savings_account_with_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AMOUNT_TRANSFERRED_1 + DOLLARS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AMOUNT_TRANSFERRED_1, actual1.getAmount());

    }

    @Test
    void transfer_from_savings_account_amount_in_dollars_less_than_balance_to_checking_account_with_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AMOUNT_TRANSFERRED_1 + DOLLARS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AMOUNT_TRANSFERRED_1, actual1.getAmount());

    }

    @Test
    void transfer_from_checking_account_amount_in_dollars_and_cents_less_than_balance_to_savings_account_with_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1 + DOLLARS_AND_CENTS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1, actual1.getAmount());

    }

    @Test
    void transfer_from_savings_account_amount_in_dollars_and_cents_and_cents_less_than_balance_to_checking_account_with_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1 + DOLLARS_AND_CENTS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1, actual1.getAmount());

    }

    @Test
    void transfer_from_checking_account_amount_in_dollars_equal_to_balance_to_empty_savings_account() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_SET);
        assertEquals(DOLLARS_AMOUNT_SET, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_savings_account_amount_in_dollars_equal_to_balance_to_empty_checking_account() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_SET);
        assertEquals(DOLLARS_AMOUNT_SET, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_checking_account_amount_in_dollars_and_cents_equal_to_balance_to_empty_savings_account() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AND_CENTS_AMOUNT_SET);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_savings_account_amount_in_dollars_and_cents_and_cents_equal_to_balance_to_empty_checking_account() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AND_CENTS_AMOUNT_SET);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_checking_account_amount_in_dollars_equal_to_balance_to_savings_account_with_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_SET);
        assertEquals(DOLLARS_AMOUNT_SET + DOLLARS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_savings_account_amount_in_dollars_equal_to_balance_to_checking_account_with_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_SET);
        assertEquals(DOLLARS_AMOUNT_SET + DOLLARS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_checking_account_amount_in_dollars_and_cents_equal_to_balance_to_savings_account_with_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AND_CENTS_AMOUNT_SET);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET + DOLLARS_AND_CENTS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_savings_account_amount_in_dollars_and_cents_equal_to_balance_to_checking_account_with_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AND_CENTS_AMOUNT_SET);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET + DOLLARS_AND_CENTS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_checking_account_amount_in_dollars_more_than_balance_to_empty_savings_account() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_2);
        assertEquals(DOLLARS_AMOUNT_SET, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_savings_account_amount_in_dollars_more_than_balance_to_empty_checking_account() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_2);
        assertEquals(DOLLARS_AMOUNT_SET, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_checking_account_amount_in_dollars_and_cents_more_than_balance_to_empty_savings_account() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_2);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_savings_account_amount_in_dollars_and_cents_and_cents_more_than_balance_to_empty_checking_account() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_2);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_checking_account_amount_in_dollars_more_than_balance_to_savings_account_with_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_2);
        assertEquals(DOLLARS_AMOUNT_SET + DOLLARS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_savings_account_amount_in_dollars_more_than_balance_to_checking_account_with_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_2);
        assertEquals(DOLLARS_AMOUNT_SET + DOLLARS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_checking_account_amount_in_dollars_and_cents_more_than_balance_to_savings_account_with_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_2);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET + DOLLARS_AND_CENTS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_savings_account_amount_in_dollars_and_cents_more_than_balance_to_checking_account_with_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_2);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET + DOLLARS_AND_CENTS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_twice_from_checking_account_total_amounts_less_than_balance_to_empty_savings_account() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AMOUNT_TRANSFERRED_1 + DOLLARS_AMOUNT_TRANSFERRED_1, actual2.getAmount());
        assertEquals(DOLLARS_AMOUNT_SET - (2 * DOLLARS_AMOUNT_TRANSFERRED_1), actual1.getAmount());
    }

    @Test
    void transfer_twice_from_savings_account_total_amounts_less_than_balance_to_empty_checking_account() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AMOUNT_TRANSFERRED_1 + DOLLARS_AMOUNT_TRANSFERRED_1, actual2.getAmount());
        assertEquals(DOLLARS_AMOUNT_SET - (2 * DOLLARS_AMOUNT_TRANSFERRED_1), actual1.getAmount());
    }

    @Test
    void transfer_twice_from_checking_account_total_amounts_less_than_balance_to_savings_accounts_with_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET_2 + DOLLARS_AMOUNT_TRANSFERRED_1 + DOLLARS_AMOUNT_TRANSFERRED_1, actual2.getAmount());
        assertEquals(DOLLARS_AMOUNT_SET - (2 * DOLLARS_AMOUNT_TRANSFERRED_1), actual1.getAmount());

    }

    @Test
    void transfer_twice_from_savings_account_total_amounts_less_than_balance_to_checking_accounts_with_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET_2 + DOLLARS_AMOUNT_TRANSFERRED_1 + DOLLARS_AMOUNT_TRANSFERRED_1, actual2.getAmount());
        assertEquals(DOLLARS_AMOUNT_SET - (2 * DOLLARS_AMOUNT_TRANSFERRED_1), actual1.getAmount());

    }

    @Test
    void transfer_twice_from_checking_account_total_amounts_more_than_balance_to_empty_savings_account() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AMOUNT_SET, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());
    }

    @Test
    void transfer_twice_from_savings_account_total_amounts_more_than_balance_to_empty_checking_account() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AMOUNT_SET, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());
    }

    @Test
    void transfer_twice_from_checking_account_total_amounts_more_than_balance_to_savings_accounts_with_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AMOUNT_SET + DOLLARS_AND_CENTS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_twice_from_savings_account_total_amounts_more_than_balance_to_checking_accounts_with_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AMOUNT_SET + DOLLARS_AND_CENTS_AMOUNT_SET_2, actual2.getAmount());
        assertEquals(AMOUNT, actual1.getAmount());

    }

    @Test
    void transfer_from_checking_to_savings_then_from_saving_to_checking_amounts_less_than_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        bank.transferAmount(ID2, ID, DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AMOUNT_TRANSFERRED_1 + DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1, actual1.getAmount());
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET_2 + DOLLARS_AMOUNT_TRANSFERRED_1 - DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1, actual2.getAmount());
    }

    @Test
    void transfer_from_savings_to_checking_then_from_saving_to_checking_amounts_less_than_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        bank.transferAmount(ID2, ID, DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1);
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AMOUNT_TRANSFERRED_1 + DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1, actual1.getAmount());
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET_2 + DOLLARS_AMOUNT_TRANSFERRED_1 - DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_1, actual2.getAmount());
    }

    @Test
    void transfer_from_checking_to_savings_amount_less_than_balance_then_from_saving_to_checking_amount_more_than_balance() {
        bank.addCheckingAccount(ID, APR);
        Checking actual1 = (Checking) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addSavingsAccount(ID2, APR);
        Savings actual2 = (Savings) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        bank.transferAmount(ID2, ID, DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_2);
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AMOUNT_TRANSFERRED_1 + (DOLLARS_AND_CENTS_AMOUNT_SET_2 + DOLLARS_AMOUNT_TRANSFERRED_1), actual1.getAmount());
        assertEquals(AMOUNT, actual2.getAmount());
    }

    @Test
    void transfer_from_savings_to_checking_amount_less_than_balance_then_from_checking_to_savings_amount_more_than_balance() {
        bank.addSavingsAccount(ID, APR);
        Savings actual1 = (Savings) bank.getAccounts().get(ID);
        actual1.setAmount(DOLLARS_AMOUNT_SET);
        bank.addCheckingAccount(ID2, APR);
        Checking actual2 = (Checking) bank.getAccounts().get(ID2);
        actual2.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET_2);
        bank.transferAmount(ID, ID2, DOLLARS_AMOUNT_TRANSFERRED_1);
        bank.transferAmount(ID2, ID, DOLLARS_AND_CENTS_AMOUNT_TRANSFERRED_2);
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AMOUNT_TRANSFERRED_1 + (DOLLARS_AND_CENTS_AMOUNT_SET_2 + DOLLARS_AMOUNT_TRANSFERRED_1), actual1.getAmount());
        assertEquals(AMOUNT, actual2.getAmount());
    }

    @Test
    void retrieve_account_gets_account_from_id() {
        bank.addCheckingAccount(ID, APR);
        bank.addSavingsAccount(ID2, APR);
        bank.addCdAccount(ID3, APR, AMOUNT_CD);
        Account account1 = bank.retrieveAccount(ID2);
        Account account2 = bank.retrieveAccount(ID);
        Account account3 = bank.retrieveAccount(ID3);
        assertTrue(account1 instanceof Savings);
        assertTrue(account2 instanceof Checking);
        assertTrue(account3 instanceof Cd);
    }


    @Test
    void pass_time_closes_empty_checking_and_savings_accounts_after_one_month() {
        bank.addCheckingAccount(ID2, APR);
        bank.addSavingsAccount(ID, APR);
        bank.passTime(1);
        assertFalse(bank.getAccounts().containsKey(ID));
        assertFalse(bank.getAccounts().containsKey(ID2));
    }

    @Test
    void pass_time_deducts_fee_from_checking_and_savings_accounts_with_balance_less_than_hundred_every_month_and_calculates_apr() {
        bank.addCheckingAccount(ID, APR);
        bank.addSavingsAccount(ID2, APR);
        Account actual1 = bank.retrieveAccount(ID);
        Account actual2 = bank.retrieveAccount(ID2);
        actual1.setAmount(50);
        actual2.setAmount(90);
        bank.passTime(1);
        assertEquals(actual1.getAmount(), 25.0125);
        assertEquals(actual2.getAmount(), 65.0325);

    }

    @Test
    void pass_time_deducts_from_account_with_balance_less_than_hundred_until_it_gets_to_zero_and_closes_it() {
        bank.addSavingsAccount(ID, APR);
        bank.addCheckingAccount(ID2, APR);
        Account actual1 = bank.retrieveAccount(ID);
        Account actual2 = bank.retrieveAccount(ID2);
        actual2.setAmount(25);
        actual1.setAmount(50);
        bank.passTime(4);
        assertTrue(bank.getAccounts().isEmpty());
    }

    @Test
    void pass_time_does_not_deduct_fee_at_a_hundred() {
        bank.addCheckingAccount(ID, 0);
        Account actual1 = bank.retrieveAccount(ID);
        actual1.setAmount(100);
        bank.passTime(1);
        assertEquals(actual1.getAmount(), 100);

    }

    @Test
    void pass_time_calculates_apr_every_month() {
        bank.addCheckingAccount(ID, 0.6);
        bank.addCdAccount(ID2, 0.6, 1000);
        Account actual = bank.retrieveAccount(ID);
        actual.setAmount(5000);
        bank.passTime(1);
        assertEquals(actual.getAmount(), 5002.5);

    }

    @Test
    void pass_time_calculates_apr_four_times_a_month_for_cd() {
        bank.addCdAccount(ID, 0.6, 1000);
        bank.passTime(1);
        Account actual = bank.retrieveAccount(ID);
        assertEquals(actual.getAmount(), 1002.0015005000625);

    }

    @Test
    void get_account_type() {
        bank.addCheckingAccount("12345678", 0.6);
        String type = bank.getAccountType("12345678");
        assertEquals(type, "Checking");
    }


}