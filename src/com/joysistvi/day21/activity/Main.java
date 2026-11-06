package com.joysistvi.day21.activity;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("*** User Registration ***");
        System.out.println("Please enter the details below:\n");

        String firstName = readRequired(scanner, "first name");

        System.out.print("Enter middle name: ");
        String middleName = scanner.nextLine();   // optional, so no validation needed

        String lastName = readRequired(scanner, "last name");
        String address = readRequired(scanner, "address");
        String email = readEmail(scanner);
        String password = readPassword(scanner);

        // Create the object using the constructor
        UserAccount account = new UserAccount(firstName, middleName, lastName,
                address, email, password);

        System.out.println("\n=== Account Details ===");
        System.out.println(account);

        scanner.close();
    }

    // Keeps asking until the user types something that is not blank
    private static String readRequired(Scanner scanner, String label) {
        while (true) {
            System.out.print("Enter " + label + ": ");
            String value = scanner.nextLine();
            if (!value.trim().isEmpty()) {
                return value;
            }
            System.out.println("Error: " + label + " cannot be empty.");
        }
    }

    // Keeps asking until the email format is valid
    private static String readEmail(Scanner scanner) {
        while (true) {
            System.out.print("Enter email: ");
            String value = scanner.nextLine();
            if (UserAccount.isValidEmail(value)) {
                return value;
            }
            System.out.println("Error: Invalid email format.");
        }
    }

    // Keeps asking until the password follows the rules
    private static String readPassword(Scanner scanner) {
        while (true) {
            System.out.print("Enter password: ");
            String value = scanner.nextLine();
            String problem = UserAccount.getPasswordProblem(value);
            if (problem == null) {
                return value;
            }
            System.out.println("Error: " + problem);
        }
    }
}