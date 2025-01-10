public class SavingsAccount {
    private double balance;
    private int accountNumber;

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
