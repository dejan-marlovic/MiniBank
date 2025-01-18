
import java.util.HashMap;

public class Bank {

    private static int accountCounter;
    private static HashMap<Integer, User> users = new HashMap<Integer, User>();

    static int generateAccountNumber() {
        return ++accountCounter;
    }

    static int createUser(String name, int pinNumber) {
        int userAccountNumber = generateAccountNumber();
        SavingsAccount savingsAccount = new SavingsAccount(generateAccountNumber());
        PayrollAccount payrollAccount = new PayrollAccount(generateAccountNumber());
        User user = new User(savingsAccount, payrollAccount, name, userAccountNumber, pinNumber);
        users.put(userAccountNumber, user);
        return userAccountNumber;
    }

    static void printRowDelimiterLine() {
        System.out.println("=============================================================================================");
    }


    static void showUserAccounts(int accountNumber) {
        User user = users.get(accountNumber);

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

        }else{
            throw  new IllegalArgumentException("Could not find User with that name!");
        }

    }

    static User getUserByNameAndPin(String name, int pinNumber){
        User user = null;
        for (User usr : users.values()){
            if (usr.getPinNumber() == pinNumber && usr.getName().equals(name)){
               user = usr;
            }
        }
        if (user == null) {
            throw new IllegalArgumentException("Could not find user account! Invalid pin or user name!");
        } else {
            return user;
        }

    }


    static void accountTransfer(int fromAcc, int toAcc, double amount, int currentUser) {
        printRowDelimiterLine();
        System.out.println("Starting transaction between accounts!");
        try {
            Bank.getUser(currentUser).getAccount(fromAcc).withdrawal(amount);
            try{
                Bank.getUser(currentUser).getAccount(toAcc).deposit(amount);

            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                System.out.println("Starting rollback process..");
                //rollback
                System.out.println("Rollback transaction:");
                Bank.getUser(fromAcc).getAccount(fromAcc).deposit(amount);

            }
            System.out.println("Transaction completed successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        printRowDelimiterLine();
    }

    static void showAllAccounts(){
        for (User user : users.values()){
            showUserAccounts(user.getAccountNumber());
        }
    }
}
