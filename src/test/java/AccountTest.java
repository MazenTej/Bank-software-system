import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    public static final String ID = "12345678";
    public static final double APR = 0.6;
    public static final double AMOUNT = 0;
    public static final double DOLLARS_AMOUNT_DEPOSITED = 1000;
    public static final double DOLLARS_AND_CENTS_AMOUNT_DEPOSITED = 1000.63;
    public static final double DOLLARS_AMOUNT_DEPOSITED_1 = 400.;
    public static final double DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1 = 400.645;
    public static final double DOLLARS_AMOUNT_DEPOSITED_2 = 300;
    public static final double DOLLARS_AMOUNT_WITHDRAWN = 500;
    public static final double DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN = 500.125;
    public static final double AMOUNT_CD = 700.265;

    Cd cd;
    Checking checking;
    Bank bank;
    Savings savings;


    @BeforeEach
    void setUp() {
        checking = new Checking(ID, APR);
        bank = new Bank();
        savings = new Savings(ID, APR);
        cd = new Cd(ID, APR, AMOUNT_CD);
    }

    @Test
    void checking_account_has_id() {

        assertEquals(ID, checking.getId());
    }

    @Test
    void checking_account_has_apr() {

        assertEquals(APR, checking.getApr());
    }

    @Test
    void checking_account_has_zero_amount_initially() {
        Bank bank = new Bank();
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
    void deposit_dollars_amount_into_checking_account_with_balance() {
        bank.addCheckingAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED_1);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED_2);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED_1 + DOLLARS_AMOUNT_DEPOSITED_2, actual.getAmount());


    }

    @Test
    void deposit_dollars_and_cents_amount_into_checking_account_with_balance() {
        bank.addCheckingAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED);
        bank.depositAmount(ID, DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED + DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1, actual.getAmount());


    }

    @Test
    void withdraw_dollars_amount_less_than_checking_account_balance() {
        bank.addCheckingAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED - DOLLARS_AMOUNT_WITHDRAWN, actual.getAmount());


    }

    @Test
    void withdraw_dollars_and_cents_amount_less_than_checking_account_balance() {
        bank.addCheckingAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED);
        bank.withdrawAmount(ID, DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED - DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN, actual.getAmount());


    }

    @Test
    void withdraw_dollars_amount_more_than_checking_account_balance() {
        bank.addCheckingAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED_2);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        assertEquals(AMOUNT, actual.getAmount());

    }

    @Test
    void withdraw_dollars_and_cents_amount_more_than_checking_account_balance() {
        bank.addCheckingAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED_2);
        bank.withdrawAmount(ID, DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        assertEquals(AMOUNT, actual.getAmount());

    }

    @Test
    void withdarw_from_empty_checking_account() {
        bank.addCheckingAccount(ID, APR);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        assertEquals(AMOUNT, actual.getAmount());


    }

    @Test
    void withdraw_twice_from_checking_account_with_balance() {
        bank.addCheckingAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        bank.withdrawAmount(ID, DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        Checking actual = (Checking) bank.getAccounts().get(ID);
        assertEquals(AMOUNT, actual.getAmount());
    }

    @Test
    void savings_account_has_id() {
        assertEquals(ID, savings.getId());
    }

    @Test
    void savings_account_has_apr() {
        assertEquals(APR, savings.getApr());
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
    void deposit_dollars_amount_into_savings_account_with_balance() {
        bank.addSavingsAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED_1);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED_2);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED_1 + DOLLARS_AMOUNT_DEPOSITED_2, actual.getAmount());
    }

    @Test
    void deposit_dollars_and_cents_amount_into_savings_account_with_balance() {
        bank.addSavingsAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED_1);
        bank.depositAmount(ID, DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED_1 + DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1, actual.getAmount());
    }

    @Test
    void withdraw_dollars_amount_less_than_savings_account_balance() {
        bank.addSavingsAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED - DOLLARS_AMOUNT_WITHDRAWN, actual.getAmount());


    }

    @Test
    void withdraw_dollars_and_cents_amount_less_than_savings_account_balance() {
        bank.addSavingsAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED);
        bank.withdrawAmount(ID, DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED - DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN, actual.getAmount());


    }

    @Test
    void withdraw_dollars_amount_more_than_savings_account_balance() {
        bank.addSavingsAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED_2);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        assertEquals(AMOUNT, actual.getAmount());

    }

    @Test
    void withdraw_dollars_and_cents_amount_more_than_savings_account_balance() {
        bank.addSavingsAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED_2);
        bank.withdrawAmount(ID, DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        assertEquals(AMOUNT, actual.getAmount());

    }

    @Test
    void withdarw_from_empty_savings_account() {
        bank.addSavingsAccount(ID, APR);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        assertEquals(AMOUNT, actual.getAmount());


    }

    @Test
    void withdraw_twice_from_savings_account_with_balance() {
        bank.addSavingsAccount(ID, APR);
        bank.depositAmount(ID, DOLLARS_AMOUNT_DEPOSITED);
        bank.withdrawAmount(ID, DOLLARS_AMOUNT_WITHDRAWN);
        bank.withdrawAmount(ID, DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        Savings actual = (Savings) bank.getAccounts().get(ID);
        assertEquals(AMOUNT, actual.getAmount());
    }


    @Test
    void cd_account_has_id() {
        bank.addCdAccount(ID, APR, AMOUNT_CD);
        assertEquals(ID, cd.getId());
    }

    @Test
    void cd_account_has_apr() {
        bank.addCdAccount(ID, APR, AMOUNT_CD);
        assertEquals(APR, cd.getApr());
    }

    @Test
    void cd_has_amount_equal_to_amount_declared_in_command() {
        bank.addCdAccount(ID, APR, AMOUNT_CD);
        assertEquals(AMOUNT_CD, cd.getAmount());
    }
}


