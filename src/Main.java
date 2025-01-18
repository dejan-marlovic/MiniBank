import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String accountName = "";
        int pinNumber = 0;
        boolean isLoggedIn = false;
        int currentAccountNumber = 0;
        int mainMenuChoice;
        while (true) {
            System.out.println("Welcome to MiniBank!");
            System.out.println("=============================================================================================");

            System.out.println("Choose an option:");
            System.out.println("1. Create an account");
            System.out.println("2. Sign in");
            System.out.println("3. Quit");
            mainMenuChoice = scanner.nextInt();
            scanner.nextLine();
            if (!isLoggedIn) {
                switch (mainMenuChoice) {
                    case 1:
                        System.out.println("Enter name you wish to create account under:");
                        accountName = scanner.nextLine();
                        System.out.println("Enter pin number:");
                        pinNumber = scanner.nextInt();
                        currentAccountNumber = Bank.createUser(accountName, pinNumber);
                        System.out.println(currentAccountNumber);
                        break;
                    case 2:
                        System.out.println("Enter your username:");
                        accountName = scanner.nextLine();
                        System.out.println("Enter your pin number:");
                        pinNumber = scanner.nextInt();
                        User user = Bank.getUserByNameAndPin(accountName, pinNumber);
                        if (user !=  null) {
                            isLoggedIn = true;
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Invalid username or password.");
                            continue; // Return to the login prompt
                        }
                        break;
                    case 3:
                        System.out.println("Exiting. Have a nice day!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
                if (accountName.equals(Bank.getUser(currentAccountNumber).getName()) &&
                        pinNumber == Bank.getUser(currentAccountNumber).getPinNumber()) {
                    isLoggedIn = true;
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Invalid username or password.");
                    continue; // Return to the login prompt
                }
            }

            while (isLoggedIn) {
                System.out.println("=============================================================================================");

                System.out.println("Choose an option:");
                System.out.println("1. Show all my accounts");
                System.out.println("2. make a transfer between accounts");
                System.out.println("3. Logout");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        Bank.showUserAccounts(currentAccountNumber);
                        break;
                    case 2:
                        System.out.println("Enter account number you want to transfer funds to: ");
                        Bank.printRowDelimiterLine();
                        Bank.showAllAccounts();
                        int toAccount = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter account number you want to transfer funds from: ");
                        int fromAccount = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter amount you would like to transfer:");
                        double amount = scanner.nextDouble();
                        Bank.accountTransfer(fromAccount, toAccount, amount, currentAccountNumber);
                        break;

                    case 3:
                        isLoggedIn = false;
                        System.out.println("You have been logged out. Returning to login screen...");
                        scanner.nextLine();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}
