
import java.util.HashMap;

public class Bank {

    private static int accountCounter;
    private final static HashMap<Integer, User> users = new HashMap<>();
    private final static HashMap<Integer, Account> accounts = new HashMap<>();

    static int generateAccountNumber() {
        return ++accountCounter;
    }

    static int createUser(String name, int pinNumber) {
        int userAccountNumber = generateAccountNumber();
        int savingsAccountNumber = generateAccountNumber();
        int payrollAccountNumber = generateAccountNumber();
        SavingsAccount savingsAccount = new SavingsAccount(savingsAccountNumber);
        PayrollAccount payrollAccount = new PayrollAccount(payrollAccountNumber);
        accounts.put(savingsAccountNumber, savingsAccount);
        accounts.put(payrollAccountNumber, payrollAccount);
        User user = new User(savingsAccount, payrollAccount, name, userAccountNumber, pinNumber);
        users.put(userAccountNumber, user);
        return userAccountNumber;
    }

    static void printRowDelimiterLine() {
        System.out.println("=============================================================================================");
    }


    static void showUserAccounts(int userAccountNumber) {
        System.out.println("Showing your accounts:");
        printRowDelimiterLine();
        User user = users.get(userAccountNumber);
        System.out.println("Account name: " + user.getName());
        printRowDelimiterLine();
        System.out.println("Savings account number: " +
                user.getSavingsAccount().getAccountNumber());
        System.out.println("Savings account balance: " +
                user.getSavingsAccount().getBalance());
        System.out.println("Payroll account number: " +
                user.getPayrollAccount().getAccountNumber());
        System.out.println("Payroll account balance: " +
                user.getPayrollAccount().getBalance());
        printRowDelimiterLine();
    }

    static User getUser(int accountNumber) {

        if (users.get(accountNumber) != null) {
            return users.get(accountNumber);
        } else {
            throw new IllegalArgumentException("Could not find User with that account number!");
        }

    }

    static User getUserByNameAndPin(String name, int pinNumber) {
        User user = null;
        for (User usr : users.values()) {
            if (usr.getPinNumber() == pinNumber && usr.getName().equals(name)) {
                user = usr;
            }
        }
        if (user == null) {
            throw new IllegalArgumentException("Could not find user account! Invalid pin or user name!");
        } else {
            return user;
        }

    }


    static void accountTransfer(int fromAcc, int toAcc, double amount, int currentUserAccountNumber) {
        printRowDelimiterLine();
        System.out.println("Starting transaction between accounts!");
        try {
            if (users.get(currentUserAccountNumber).getPayrollAccount().getAccountNumber() == fromAcc
                    || users.get(currentUserAccountNumber).getSavingsAccount().getAccountNumber() == fromAcc) {
                try {
                    getAccount(fromAcc).withdrawal(amount);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else
                throw new IllegalArgumentException("You can only make a withdrawal from your own accounts!");
            try {
                getAccount(toAcc).deposit(amount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Transaction completed successfully!");
            printRowDelimiterLine();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        printRowDelimiterLine();
    }

    static void showAllAccounts() {
        printRowDelimiterLine();
        System.out.println("Showing all accounts you can deposit funds to:");
        printRowDelimiterLine();
        for (User user : users.values()) {
            showUserAccounts(user.getAccountNumber());
        }
        printRowDelimiterLine();
    }

    static Account getAccount(int accountNumber) {

        if (accounts.get(accountNumber) != null) {
            return accounts.get(accountNumber);
        } else {
            throw new IllegalArgumentException("Could not find Account with that account number!");
        }

    }

    static void validateAccountName(String accountName){
        if (accountName.matches(".*\\d.*")){
            throw new IllegalArgumentException("Name can not contain numbers!");
        }
        if(accountName.length() <= 2){
            throw new IllegalArgumentException("Name has to be longer than 2 characters!");
        }
        if (accountName.matches("^-.*|.*-$")) {
            throw new IllegalArgumentException("Name can not start or end with a hyphen!");
        }
    }
}
