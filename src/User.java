public class User {

    private final SavingsAccount savingsAccount;
    private final PayrollAccount payrollAccount;
    private final String name;
    private int accountNumber;
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


}
