public class User {

    private final SavingsAccount savingsAccount;
    private final PayrollAccount payrollAccount;
    private final String name;
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



