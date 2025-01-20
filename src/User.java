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

    /**
     * Variable storing User account pin-number.
     */
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

    /**
     *
     * @return Returns this users payroll bank account object
     */
    public PayrollAccount getPayrollAccount() {
        return payrollAccount;
    }

    /**
     *
     * @return this user's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return this user's account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }
    /**
     *
     * @return this user's account pin-number
     */
    public int getPinNumber() {
        return pinNumber;
    }

    /**
     *
     * @return Returns this users savings bank account object
     */
    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    /**
     * Checks if current bank account belongs to this user account.
     * @param accountNr bank account number we want to ceck
     * @return bank account object
     */
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



