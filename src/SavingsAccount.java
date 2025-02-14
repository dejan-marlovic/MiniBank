
/**
 * Class representing savings bank account for each user, extends Account
 */
public class SavingsAccount  extends Account{

    public SavingsAccount(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}
