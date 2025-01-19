import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String accountName = "";
        int pinNumber = 0;
        boolean isLoggedIn = false;
        int currentUserAccountNumber = 0;
        int mainMenuChoice;
        User currentUser;
        while (true) {
            System.out.println("Welcome to MiniBank!");
            Bank.printRowDelimiterLine();

            System.out.println("Choose an option:");
            System.out.println("1. Create an account");
            System.out.println("2. Sign in");
            System.out.println("3. Quit");
            try {
                mainMenuChoice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Valid inputs are: 1, 2 or 3.");
                scanner.nextLine(); // Clear the invalid input from the scanner
                continue;
            }

            if (!isLoggedIn) {
                switch (mainMenuChoice) {
                    case 1:
                        try {
                            System.out.println("Enter name you wish to create account under:");
                            accountName = scanner.nextLine();
                            Bank.validateAccountName(accountName);
                            System.out.println("Enter pin number:");
                            pinNumber = scanner.nextInt();
                            scanner.nextLine();
                            currentUserAccountNumber = Bank.createUser(accountName, pinNumber);
                            System.out.println("Account has been created! Logging in.....");
                            break;

                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a numeric value for the pin number.");
                            scanner.nextLine(); // Clear the invalid input from the scanner
                            continue;
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }

                    case 2:
                        try {
                            System.out.println("Enter your username:");
                            Bank.validateAccountName(accountName);
                            accountName = scanner.nextLine();
                            System.out.println("Enter your pin number:");
                            pinNumber = scanner.nextInt();
                            currentUser = Bank.getUserByNameAndPin(accountName, pinNumber);
                            currentUserAccountNumber = currentUser.getAccountNumber();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a numeric value for the pin number.");
                            scanner.nextLine(); // Clear the invalid input from the scanner
                            continue;
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());// Catches IllegalArgumentException
                            continue;
                        }
                        break;
                    case 3:
                        System.out.println("Exiting. Have a nice day!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Valid options are: 1, 2 or 3. Please try again!");
                }

                if (currentUserAccountNumber != 0){
                    if (accountName.equals(Bank.getUser(currentUserAccountNumber).getName()) &&
                            pinNumber == Bank.getUser(currentUserAccountNumber).getPinNumber()) {
                        isLoggedIn = true;
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Invalid username or password.");
                        continue; // Return to the login prompt
                    }
                }

            }

            while (isLoggedIn) {
                int choice = 0;
                Bank.printRowDelimiterLine();

                System.out.println("Choose an option:");
                System.out.println("1. Show all my accounts");
                System.out.println("2. Make a transfer between accounts");
                System.out.println("3. Logout");
                System.out.println("4. Make a deposit");
                System.out.println("5. Make a withdrawal");

                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Valid inputs are: 1, 2, 3, 4 or 5!");
                    scanner.nextLine(); // Clear the invalid input from the scanner

                }
                switch (choice) {
                    case 1:
                        Bank.showUserAccounts(currentUserAccountNumber);
                        break;
                    case 2:
                        try {
                            System.out.println("Enter an account number you want to transfer funds from: ");
                            Bank.showUserAccounts(currentUserAccountNumber);
                            int fromAccount = scanner.nextInt();
                            scanner.nextLine();
                            Bank.printRowDelimiterLine();
                            Bank.showAllAccounts();
                            Bank.printRowDelimiterLine();
                            System.out.println("Enter an account number you want to transfer funds to: ");
                            int toAccount = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter amount you would like to transfer:");
                            double amount = scanner.nextDouble();
                            Bank.accountTransfer(fromAccount, toAccount, amount, currentUserAccountNumber);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a numeric value for the pin number.");
                            scanner.nextLine(); // Clear the invalid input from the scanner
                            continue;
                        }

                    case 3:
                        isLoggedIn = false;
                        System.out.println("You have been logged out. Returning to login screen...");
                        scanner.nextLine();
                        break;

                    case 4:
                        try {
                            Bank.showUserAccounts(currentUserAccountNumber);
                            System.out.println("Pick an account you would like to make a deposit to:");
                            int depositAccount = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("How much would you like to deposit?");
                            double depositAmount = scanner.nextDouble();
                            scanner.nextLine();
                            Bank.getUser(currentUserAccountNumber).getAccount(depositAccount).deposit(depositAmount);
                            break;

                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a numeric value for the pin number.");
                            scanner.nextLine(); // Clear the invalid input from the scanner
                            continue;
                        }

                    case 5:
                        try {
                            Bank.showUserAccounts(currentUserAccountNumber);
                            System.out.println("Pick an account you would like to make a withdrawal from:");
                            int from = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("How much would you like to withdraw?");
                            double withdrawalAmount = scanner.nextDouble();
                            scanner.nextLine();
                            Bank.getUser(currentUserAccountNumber).getAccount(from).withdrawal(withdrawalAmount);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a numeric value for the pin number.");
                            scanner.nextLine(); // Clear the invalid input from the scanner
                            continue;
                        }
                    default:
                        System.out.println("Invalid input! Valid inputs are: 1, 2, 3, 4 or 5!");
                }
            }
        }
    }
}
