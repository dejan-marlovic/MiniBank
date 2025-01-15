
import java.util.HashMap;

public class Bank {

    private static int accountCounter;
    private static HashMap<Integer, User> users  = new HashMap<Integer, User>();

    static int generateAccountNumber() {
        return ++accountCounter;
    }

    static int createUser(String name, int pinNumber){
        int userAccountNumber = generateAccountNumber();
        SavingsAccount savingsAccount = new SavingsAccount(generateAccountNumber());
        PayrollAccount payrollAccount = new PayrollAccount(generateAccountNumber());
        User user = new User(savingsAccount, payrollAccount, name, generateAccountNumber(), pinNumber);
        users.put(userAccountNumber,user);
        return userAccountNumber;
    }

    static void showUserAccounts(int accountNumber){
        User user = users.get(accountNumber);
        System.out.println("Savings account number: " +
                user.getSavingsAccount().getAccountNumber());
        System.out.println("Savings account balance:" +
                user.getSavingsAccount().getBalance());
        System.out.println("Payroll account number: " +
                user.getPayrollAccount().getAccountNumber());
        System.out.println("Payroll account balance:" +
                user.getPayrollAccount().getBalance());
    }

    static User getUser(int accountNumber){
        return users.get(accountNumber);
    }

}
