import java.util.ArrayList;
import java.util.HashMap;

public class Bank {

    private static int accountCounter;
    private static HashMap<Integer, User> users  = new HashMap<Integer, User>();

    static int generateAccountNumber() {
        return ++accountCounter;
    }

    static void createUser(String name, int pinNumber){
        SavingsAccount savingsAccount = new SavingsAccount(generateAccountNumber());
        PayrollAccount payrollAccount = new PayrollAccount(generateAccountNumber());
        User user = new User(savingsAccount, payrollAccount, name, generateAccountNumber(), pinNumber );
        users.put(accountCounter,user);
    }

    static void showAccounts(int accountNumber){

    }

}
