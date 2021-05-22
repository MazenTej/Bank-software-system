package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {

    public static final String ID = "12345678";
    public static final double APR = 0.6;
    public static final double AMOUNT_CD = 700.265;
    public static final double AMOUNT = 0;
    public static final double DOLLARS_AMOUNT_DEPOSITED = 1000;
    public static final double DOLLARS_AND_CENTS_AMOUNT_DEPOSITED = 1000.63;
    public static final double DOLLARS_AMOUNT_DEPOSITED_1 = 400;
    public static final double DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1 = 400.645;
    public static final double DOLLARS_AMOUNT_DEPOSITED_2 = 300;
    public static final double DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_2 = 300.123;
    public static final double DOLLARS_AMOUNT_WITHDRAWN = 500;
    public static final double DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN = 500.125;
    public static final double DOLLARS_AMOUNT_SET = 1000;
    public static final double DOLLARS_AND_CENTS_AMOUNT_SET = 1000.123;

    public static final double DOLLARS_AMOUNT_SET_2 = 300;
    public static final double DOLLARS_AND_CENTS_AMOUNT_SET_2 = 300.123;


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
    void savings_account_has_id() {
        assertEquals(ID, savings.getId());
    }

    @Test
    void savings_account_has_apr() {
        assertEquals(APR, savings.getApr());
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
    void deposit_dollars_amount_into_empty_checking_account() {
        checking.setAmount(AMOUNT);
        checking.deposit(DOLLARS_AMOUNT_DEPOSITED);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED, checking.getAmount());

    }

    @Test
    void deposit_dollars_and_cents_amount_into_empty_checking_account() {
        checking.setAmount(AMOUNT);
        checking.deposit(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED, checking.getAmount());

    }

    @Test
    void deposit_dollars_amount_into_checking_account_with_balance() {
        checking.setAmount(DOLLARS_AMOUNT_SET);
        checking.deposit(DOLLARS_AMOUNT_DEPOSITED);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED + DOLLARS_AMOUNT_SET, checking.getAmount());

    }

    @Test
    void deposit_dollars_and_cents_amount_into_checking_account_with_balance() {
        checking.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);
        checking.deposit(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED + DOLLARS_AND_CENTS_AMOUNT_SET, checking.getAmount());

    }

    @Test
    void deposit_dollars_amounts_twice_into_empty_checking_account() {
        checking.setAmount(AMOUNT);
        checking.deposit(DOLLARS_AMOUNT_DEPOSITED_1);
        checking.deposit(DOLLARS_AMOUNT_DEPOSITED_2);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED_1 + DOLLARS_AMOUNT_DEPOSITED_2, checking.getAmount());

    }

    @Test
    void deposit_dollars_and_cents_amount_twice_into_empty_checking_account() {
        checking.setAmount(AMOUNT);
        checking.deposit(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1);
        checking.deposit(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_2);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1 + DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_2, checking.getAmount());

    }

    @Test
    void withdraw_dollars_amount_less_than_checking_account_balance() {
        checking.setAmount(DOLLARS_AMOUNT_SET);
        checking.withdraw(DOLLARS_AMOUNT_WITHDRAWN);
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AMOUNT_WITHDRAWN, checking.getAmount());
    }

    @Test
    void withdraw_dollars_and_cents_amount_less_than_checking_account_balance() {
        checking.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);
        checking.withdraw(DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET - DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN, checking.getAmount());
    }

    @Test
    void withdraw_dollars_amount_from_empty_checking_account() {
        checking.setAmount(AMOUNT);
        checking.withdraw(DOLLARS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, checking.getAmount());
    }

    @Test
    void withdraw_dollars_and_cents_amount_from_empty_checking_account() {
        checking.setAmount(AMOUNT);
        checking.withdraw(DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, checking.getAmount());
    }

    @Test
    void withdraw_dollars_amount_more_than_checking_account_balance() {
        checking.setAmount(DOLLARS_AMOUNT_SET_2);
        checking.withdraw(DOLLARS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, checking.getAmount());
    }

    @Test
    void withdraw_dollars_and_cents_amount_more_than_checking_account_balance() {
        checking.setAmount((DOLLARS_AND_CENTS_AMOUNT_SET_2));
        checking.withdraw(DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, checking.getAmount());
    }

    @Test
    void withdraw_twice_from_checking_account_with_total_more_than_balance() {
        checking.setAmount(DOLLARS_AMOUNT_SET);
        checking.withdraw(DOLLARS_AMOUNT_WITHDRAWN);
        checking.withdraw(DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, checking.getAmount());


    }


    @Test
    void deposit_dollars_amount_into_empty_savings_account() {
        savings.setAmount(AMOUNT);
        savings.deposit(DOLLARS_AMOUNT_DEPOSITED);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED, savings.getAmount());

    }

    @Test
    void deposit_dollars_and_cents_amount_into_empty_savings_account() {
        savings.setAmount(AMOUNT);
        savings.deposit(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED, savings.getAmount());

    }

    @Test
    void deposit_dollars_amount_into_savings_account_with_balance() {
        savings.setAmount(DOLLARS_AMOUNT_SET);
        savings.deposit(DOLLARS_AMOUNT_DEPOSITED);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED + DOLLARS_AMOUNT_SET, savings.getAmount());

    }

    @Test
    void deposit_dollars_and_cents_amount_into_savings_account_with_balance() {
        savings.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);
        savings.deposit(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED + DOLLARS_AND_CENTS_AMOUNT_SET, savings.getAmount());

    }

    @Test
    void deposit_dollars_amounts_twice_into_empty_savings_account() {
        savings.setAmount(AMOUNT);
        savings.deposit(DOLLARS_AMOUNT_DEPOSITED_1);
        savings.deposit(DOLLARS_AMOUNT_DEPOSITED_2);
        assertEquals(DOLLARS_AMOUNT_DEPOSITED_1 + DOLLARS_AMOUNT_DEPOSITED_2, savings.getAmount());

    }

    @Test
    void deposit_dollars_and_cents_amount_twice_into_empty_savings_account() {
        savings.setAmount(AMOUNT);
        savings.deposit(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1);
        savings.deposit(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_2);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_1 + DOLLARS_AND_CENTS_AMOUNT_DEPOSITED_2, savings.getAmount());

    }

    @Test
    void withdraw_dollars_amount_less_than_savings_account_balance() {
        savings.setAmount(DOLLARS_AMOUNT_SET);
        savings.withdraw(DOLLARS_AMOUNT_WITHDRAWN);
        assertEquals(DOLLARS_AMOUNT_SET - DOLLARS_AMOUNT_WITHDRAWN, savings.getAmount());
    }

    @Test
    void withdraw_dollars_and_cents_amount_less_than_savings_account_balance() {
        savings.setAmount(DOLLARS_AND_CENTS_AMOUNT_SET);
        savings.withdraw(DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(DOLLARS_AND_CENTS_AMOUNT_SET - DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN, savings.getAmount());
    }

    @Test
    void withdraw_dollars_amount_from_empty_savings_account() {
        savings.setAmount(AMOUNT);
        savings.withdraw(DOLLARS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, savings.getAmount());
    }

    @Test
    void withdraw_dollars_and_cents_amount_from_empty_savings_account() {
        savings.setAmount(AMOUNT);
        savings.withdraw(DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, savings.getAmount());
    }

    @Test
    void withdraw_dollars_amount_more_than_savings_account_balance() {
        savings.setAmount(DOLLARS_AMOUNT_SET_2);
        savings.withdraw(DOLLARS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, savings.getAmount());
    }

    @Test
    void withdraw_dollars_and_cents_amount_more_than_savings_account_balance() {
        savings.setAmount((DOLLARS_AND_CENTS_AMOUNT_SET_2));
        savings.withdraw(DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, savings.getAmount());
    }

    @Test
    void withdraw_twice_from_savigns_account_with_total_more_than_balance() {
        savings.setAmount(DOLLARS_AMOUNT_SET);
        savings.withdraw(DOLLARS_AMOUNT_WITHDRAWN);
        savings.withdraw(DOLLARS_AND_CENTS_AMOUNT_WITHDRAWN);
        assertEquals(AMOUNT, savings.getAmount());


    }


}


