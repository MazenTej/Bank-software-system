package banking;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    Bank() {
        accounts = new HashMap<>();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void addCheckingAccount(String id, double apr) {
        accounts.put(id, new Checking(id, apr));
    }

    public void depositAmount(String id, double amountDeposited) {
        accounts.get(id).deposit(amountDeposited);
    }

    public void withdrawAmount(String id, double amountWithdrawn) {
        accounts.get(id).withdraw(amountWithdrawn);
    }

    public void addSavingsAccount(String id, double apr) {
        accounts.put(id, new Savings(id, apr));
    }


    public void addCdAccount(String id, double apr, double amount_cd) {
        accounts.put(id, new Cd(id, apr, amount_cd));
    }

    public boolean accountExistsWithId(String id) {
        if (accounts.get(id) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidDepositInAccount(String id, String deposit_amount) {
        Account account = accounts.get(id);
        boolean isValidDeposit = account.isValidDepositWith(deposit_amount);
        return isValidDeposit;
    }

    public void transferAmount(String id, String id2, double amountTransferred) {
        accounts.get(id).withdraw(amountTransferred);
        accounts.get(id2).deposit(amountTransferred);

    }
}