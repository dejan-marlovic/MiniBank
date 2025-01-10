public class PayrollAccount {
    private double balance;
    private int accountNumber;

    public PayrollAccount(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}
