import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Simulate login
        String correctUsername = "user123";
        String correctPassword = "password";
        String accountName;
        String password;
        int pinNumber;
        boolean isLoggedIn = false;
        int currentAcountNumber;
        int mainChoice;
        String username;
        while (true) {
            System.out.println("Welcome to MiniBank!");

            System.out.println("Choose an option:");
            System.out.println("1. Create an account");
            System.out.println("2. Sign in");
            System.out.println("3. Quit");
            mainChoice = scanner.nextInt();
            if (!isLoggedIn) {
                switch (mainChoice) {
                    case 1:
                        System.out.println("Enter name you wish to create account under:");
                        accountName = scanner.nextLine();
                        System.out.println("Enter pin number:");
                        pinNumber = scanner.nextInt();
                        currentAcountNumber = Bank.createUser(accountName, pinNumber);
                        break;
                    case 2:
                        System.out.println("Enter your username:");
                        String username = scanner.nextLine();
                        System.out.println("Enter your password:");
                        password = scanner.nextLine();
                        if (username.equals(Bank.getUser(currentAcountNumber).getName()) &&
                                password.equals(Bank.getUser(currentAcountNumber).getPasswod())) {
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
                if (username.equals(Bank.getUser(currentAcountNumber).getName()) &&
                        password.equals(Bank.getUser(currentAcountNumber).getPasswod())) {
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

                        break;
                    case 4:
                        isLoggedIn = false;
                        System.out.println("You have been logged out. Returning to login screen...");
                        scanner.nextLine();
                        break;
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
