import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Simulate login
        String correctUsername = "user123";
        String correctPassword = "password";
        String username;
        String password;
        boolean isLoggedIn = false;

        double accountBalance = 1000.00; // Example account balance
        int mainChoice;
        while (true) { // Outer loop to allow re-lo
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
                        String accountName = scanner.nextLine();
                        Bank.createUser(accountName);
                        break;
                    case 2:
                        System.out.println("Enter your username:");
                        username = scanner.nextLine();
                        System.out.println("Enter your password:");
                        password = scanner.nextLine();
                        if (username.equals(correctUsername) && password.equals(correctPassword)) {
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
                if (username.equals(correctUsername) && password.equals(correctPassword)) {
                    isLoggedIn = true;
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Invalid username or password.");
                    continue; // Return to the login prompt
                }
            }

            // Bank withdrawal process
            while (isLoggedIn) { // Inner loop for banking operations
                System.out.println("\nYour current balance: $" + accountBalance);

                System.out.println("Choose an option:");
                System.out.println("1. Show all my accounts");
                System.out.println("2. make a transfer between accounts");
                System.out.println("3. Logout");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:

                        break;
                    case 2:
                        if (accountBalance >= 200) {
                            accountBalance -= 200;
                            System.out.println("You withdrew $200. New balance: $" + accountBalance);
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                        break;
                    case 3:
                        if (accountBalance >= 500) {
                            accountBalance -= 500;
                            System.out.println("You withdrew $500. New balance: $" + accountBalance);
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                        break;
                    case 4:
                        isLoggedIn = false;
                        System.out.println("You have been logged out. Returning to login screen...");
                        scanner.nextLine(); // Consume leftover newline
                        break;
                    case 5:
                        System.out.println("Exiting. Have a nice day!");
                        scanner.close();
                        return; // Exit the program
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}
