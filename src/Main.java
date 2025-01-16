import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Simulate login
        String accountName = "";
        int pinNumber = 0;
        boolean isLoggedIn = false;
        int currentAcountNumber = 0;
        int mainChoice;
        while (true) {
            System.out.println("Welcome to MiniBank!");

            System.out.println("Choose an option:");
            System.out.println("1. Create an account");
            System.out.println("2. Sign in");
            System.out.println("3. Quit");
            mainChoice = scanner.nextInt();
            scanner.nextLine();
            if (!isLoggedIn) {
                switch (mainChoice) {
                    case 1:
                        System.out.println("Enter name you wish to create account under:");
                        accountName = scanner.nextLine();
                        System.out.println("Enter pin number:");
                        pinNumber = scanner.nextInt();
                        currentAcountNumber = Bank.createUser(accountName, pinNumber);
                        System.out.println(currentAcountNumber);
                        break;
                    case 2:
                        System.out.println("Enter your username:");
                        accountName = scanner.nextLine();
                        System.out.println("Enter your pin number:");
                        pinNumber = scanner.nextInt();

                        User user = Bank.getUserByNameAndPin(accountName, pinNumber);

                        if (user != null) {
                            isLoggedIn = true;
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Invalid username or password.");
                            continue; // Return to the login prompt
                        }
                        break;

                    case 3:
                        isLoggedIn = false;
                        System.out.println("Exiting. Have a nice day!");
                        scanner.nextLine();
                        scanner.close();
                        return; // Exit the program
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
                if (accountName.equals(Bank.getUser(currentAcountNumber).getName()) &&
                        pinNumber == Bank.getUser(currentAcountNumber).getPinNumber()) {
                    isLoggedIn = true;
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Invalid username or password.");
                    continue; // Return to the login prompt
                }
            }


            while (isLoggedIn) {

                System.out.println("Choose an option:");
                System.out.println("1. Show all my accounts");
                System.out.println("2. make a transfer between accounts");
                System.out.println("3. Logout");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        Bank.showUserAccounts(currentAcountNumber);
                        break;
                    case 2:

                        break;
                    case 3:
                        isLoggedIn = false;
                        System.out.println("You have been logged out. Returning to login screen...");
                        scanner.nextLine();
                        break;
                    case 4:

                    case 5:
                        System.out.println("Exiting. Have a nice day!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}
