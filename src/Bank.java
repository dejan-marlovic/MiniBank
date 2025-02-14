
import java.util.HashMap;

/**
 * Class representing Bank. This class contains account and user collection. It also contains helper
 * functions for: Creating users, Creating accounts, Handling transfer between accounts, generating
 * account numbers, generating user account numbers, listing users and listing accounts
 */
public class Bank {
    /**
     * accountCounter keeps track of each account number and increments each time new account is created via function:
     * generateAccountNumber()
     */
    private static int accountCounter;

    /**
     * Stores user accounts
     */
    private final static HashMap<Integer, User> users = new HashMap<>();

    /**
     * Stores bank accounts
     */
    private final static HashMap<Integer, Account> accounts = new HashMap<>();

    /**
     * This is a helper class with helper methods, we will never instantiate an object of this class.
     * This is why the constructor is private
     */
    private Bank() {
    }

    /**
     * Increments account counter, This function is used for generation of  user and account numbers
     */
    static int generateAccountNumber() {
        return ++accountCounter;
    }

    /**
     * Creates user account by first generating unique account and user numbers. Each user account has
     * savings bank account and payroll bank account. Account number is generated for each.
     * Also creates savings account and payroll account object for each user.
     * @param name Class User constructor needs name so it is passed from input via name parameter.
     * @param pinNumber Class User constructor needs pin so it is passed from input via pinNumber parameter.
     * @return newly created user account number
     */
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

    /**
     * Prints row delimiter for more readable console printouts.
     */
    static void printRowDelimiterLine() {
        System.out.println("=============================================================================================");
    }

    /**
     * Shows user account's savings and bank account, including bank account number and balance
     * @param userAccountNumber user account number passed from input.
     */
    static void showUserAccounts(int userAccountNumber) {
        System.out.println("Showing user account:");
        printRowDelimiterLine();
        User user = getUser(userAccountNumber);
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

    /**
     *
     * @param accountNumber returns user object form given user account number
     * @return User object
     */
    static User getUser(int accountNumber) {

        if (users.get(accountNumber) != null) {
            return users.get(accountNumber);
        } else {
            throw new IllegalArgumentException("Could not find User with that account number!");
        }

    }

    /**
     * Returns user object by name and pin-number.
     * @param name uses username to find corresponding user object
     * @param pinNumber uses pin -number to find corresponding user object
     * @return user object that has given username and pin
     */
    static User getUserByNameAndPin(String name, int pinNumber) {
        User user = null;
        for (User usr : users.values()) {
            if (usr.getPinNumber() == pinNumber && usr.getName().equals(name)) {
                user = usr;
            }
        }
        if (user == null) {
            throw new IllegalArgumentException("Could not find user account! Invalid pin or username!");
        } else {
            return user;
        }

    }

    /**
     * Function for transferring funds between accounts
     * @param fromAcc parameter representing account we transfer funds from.
     * @param toAcc parameter representing account we transfer funds to.
     * @param amount amount we want to transfer.
     * @param currentUserAccountNumber we use current account number form input to make sure that we are allowed to withdraw funds.
     */
    static void accountTransfer(int fromAcc, int toAcc, double amount, int currentUserAccountNumber) {
        printRowDelimiterLine();
        System.out.println("Starting transaction between accounts!");
        try {
            //We can not withdraw amount for transfer unless we are logged in as corresponding user
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

    /**
     * Listing all bank account each user object has
     */
    static void showAllAccounts() {
        printRowDelimiterLine();
        System.out.println("Showing all accounts you can deposit funds to:");
        printRowDelimiterLine();
        for (User user : users.values()) {
            showUserAccounts(user.getAccountNumber());
        }
        printRowDelimiterLine();
    }

    /**
     * Lists bank account numbers
     * @param accountNumber returns bank account object from corresponding bank account parameter
     * @return Account object
     */
    static Account getAccount(int accountNumber) {

        if (accounts.get(accountNumber) != null) {
            return accounts.get(accountNumber);
        } else {
            throw new IllegalArgumentException("Could not find Account with that account number!");
        }

    }

    /**
     * Validates user account name from input. For example: account name can not contain numbers.
     * @param accountName account name that is used for creation of user account objects
     */
    static void validateUserAccountName(String accountName) {
        if (accountName.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Name can not contain numbers!");
        }
        if (accountName.length() <= 2) {
            throw new IllegalArgumentException("Name has to be longer than 2 characters!");
        }
        if (accountName.matches("^-.*|.*-$")) {
            throw new IllegalArgumentException("Name can not start or end with a hyphen!");
        }
    }
}
