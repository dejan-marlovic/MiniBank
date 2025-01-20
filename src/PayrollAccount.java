public class PayrollAccount extends Account {

    /**
     * Class representing payroll bank account for each user, extends Account
     */
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
