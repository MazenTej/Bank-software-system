package banking;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;
    private CommandStorage commandStorage;

    public Bank() {
        accounts = new LinkedHashMap<>();
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


    public void addCdAccount(String id, double apr, double amountCd) {
        accounts.put(id, new Cd(id, apr, amountCd));
    }

    public boolean accountExistsWithId(String id) {
        if (accounts.get(id) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidDepositInAccount(String id, String depositAmount) {
        Account account = accounts.get(id);
        boolean isValidDeposit = account.isValidDepositWith(depositAmount);
        return isValidDeposit;
    }

    public void transferAmount(String id, String id2, double amountTransferred) {
        Account account1 = accounts.get(id);
        Account account2 = accounts.get(id2);
        if (account1.getAmount() >= amountTransferred) {
            account1.withdraw(amountTransferred);
            account2.deposit(amountTransferred);
        } else {
            account2.deposit(account1.getAmount());
            account1.setAmount(0);

        }

    }

    public Account retrieveAccount(String id) {
        Account account = accounts.get(id);
        return account;

    }


    public void passTime(int months) {
        for (int j = 1; j <= months; j++) {
            Iterator<String> it = accounts.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                Account account = retrieveAccount(key);
                if (account.getAmount() == 0) {
                    it.remove();
                } else if (account.getAmount() < 100) {
                    withdrawAmount(key, 25);
                }
                account.passTime(months);
            }
        }


    }


    public boolean isValidWithdrawFromAccount(String id, String withdrawAmount) {
        Account account = accounts.get(id);
        boolean isValidWithdraw = account.isValidWithdraw(withdrawAmount);
        return isValidWithdraw;
    }

    public String getAccountType(String key) {
        Account account = accounts.get(key);
        String accountType = account.getAccountType(key);
        return accountType;
    }

    public boolean isValidTransferBetweenAccounts(String fromId) {
        Account account = accounts.get(fromId);
        boolean isValidTransfer = account.isValidTransfer();
        return isValidTransfer;

    }
}
