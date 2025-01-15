public class User {

    private SavingsAccount savingsAccount;
    private PayrollAccount payrollAccount;
    private String name;
    private int accountNumber;
    private int pinNumber;


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
}
