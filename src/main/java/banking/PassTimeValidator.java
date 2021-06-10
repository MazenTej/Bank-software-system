package banking;

public class PassTimeValidator {
    public Parsing parsing;
    private Bank bank;

    public PassTimeValidator(Bank bank) {
        this.bank = bank;
        parsing = new Parsing();
    }

    public boolean validatePassTime(String command) {
        return true;
    }
}
