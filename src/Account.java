public class Account {

    protected double balance;
    protected  int accountNumber;


    public void withdrawal(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount can not be negative!");
        }
        if (balance - amount < 0)
        {
            throw new IllegalArgumentException("Can not withdraw amount: "
                    + amount +" from account: " + accountNumber +
                    ". Insufficient funds!");
        }
        balance -= amount;
        messageOutput("Withdrawal has been made from account: ", amount);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount can not be negative!");
        }
        balance += amount;
        messageOutput("Deposit has been made to account: ", amount);
    }

    public void messageOutput(String message, double amount) {
        printRowDelimiterLine();
        System.out.println(message
                + this.accountNumber + ", "
                + " Amount: "
                + amount
                + ", total balance is now: "
                + this.balance);
    }
    public void printRowDelimiterLine() {
        System.out.println("=========================================================================");
    }
}
