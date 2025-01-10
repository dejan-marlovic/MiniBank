import java.util.ArrayList;

public class Bank {

    private static int accountCounter;
    private static ArrayList<User> users  = new ArrayList<User>();

    static int generateAccountNumber() {
        return ++accountCounter;
    }

    static void createUser(String name, int pinNumber){
        SavingsAccount savingsAccount = new SavingsAccount();
        PayrollAccount payrollAccount = new PayrollAccount();
        User user = new User(savingsAccount, payrollAccount, name, generateAccountNumber(), pinNumber );
        users.add(user);
    }

    static void showAccounts(){
        for ()
    }

}
