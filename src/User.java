/**
 * Class representing user account. Each User "has" Savings account and
 * PayrollAccount bank account object.
 */
public class User {

    /**
     * Object reference representing savings account.
     * This object is created each User object is created
     */
    private final SavingsAccount savingsAccount;

    /**
     * Object reference representing payroll account.
     * This object is created each User object is created
     */
    private final PayrollAccount payrollAccount;

    /**
     * Variable storing User account name
     */
    private final String name;

    /**
     * Variable storing User account number.
     */
    private final int accountNumber;

    private final int pinNumber;

    public User(SavingsAccount savingsAccount,
                PayrollAccount payrollAccount,
                String name,
                int accountNumber,
                int pinNumber) {

        this.savingsAccount = savingsAccount;
        this.payrollAccount = payrollAccount;
        this.name = name;
        this.pinNumber = pinNumber;
        this.accountNumber = accountNumber;

    }

    public PayrollAccount getPayrollAccount() {
        return payrollAccount;
    }

    public String getName() {
        return name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    Account getAccount(int accountNr) {
        Account account = null;

        if (this.savingsAccount.getAccountNumber() == accountNr) {
            account = savingsAccount;
        }
        if (this.payrollAccount.accountNumber == accountNr) {
            account = payrollAccount;
        }
        if (account == null) {
            throw new IllegalArgumentException("Could not find an account with that account number!");
        } else {
            return account;

        }
    }
}



