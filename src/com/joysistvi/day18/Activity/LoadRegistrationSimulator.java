package com.joysistvi.day18.Activity;

import java.util.Scanner;

public class LoadRegistrationSimulator {

    static Scanner scanner = new Scanner(System.in);

    static double balance = 100.0;

    public static void main(String[] args) {
        dialUssdCode();
        promptMainMenu();
    }


    public static void dialUssdCode() {
        clearScreen();
        System.out.println("Dial *143# to access Globe Services");

        while (true) {
            System.out.print("Enter USSD Code: ");
            String ussdCode = scanner.nextLine().trim();

            if (ussdCode.equals("*143#")) {
                return;
            }

            System.out.println("Invalid USSD Code. Please try again.");
        }
    }


    public static void promptMainMenu() {
        while (true) {
            clearScreen();
            System.out.println("** Load Registration **");
            System.out.println("    [1] Unli Text");
            System.out.println("    [2] Unli Call");
            System.out.println("    [3] Unli Call N Text");
            System.out.println("    [4] Check Balance");
            System.out.println("    [5] Exit");

            int mainChoice = getInput("Enter choice: ", 1, 5);

            switch (mainChoice) {
                case 1 -> unliTextPromo();
                case 2 -> unliCallPromo();
                case 3 -> unliCallNTextPromo();
                case 4 -> checkBalance();
                case 5 -> exitMethod();
            }
        }
    }

    public static void checkBalance() {
        clearScreen();
        System.out.println("--------------------------------");
        System.out.println("Your current balance is: P" + balance);
        System.out.println("--------------------------------");
        pressEnterToContinue();
    }


    public static void unliTextPromo() {
        while (true) {
            clearScreen();
            System.out.println("** Enjoy Unli Text **");
            System.out.println("    [1] 1 Day for P10");
            System.out.println("    [2] 3 Days for P30");
            System.out.println("    [3] 5 Days for P50");
            System.out.println("    [4] Back");

            int choice = getInput("Enter choice: ", 1, 4);

            switch (choice) {
                case 1 -> subscriptionConfirmation("Unli Text", 1, 10);
                case 2 -> subscriptionConfirmation("Unli Text", 3, 30);
                case 3 -> subscriptionConfirmation("Unli Text", 5, 50);
                case 4 -> { return; }
            }
        }
    }



    public static void unliCallPromo() {
        while (true) {
            clearScreen();
            System.out.println("** Enjoy Unli Call **");
            System.out.println("    [1] 1 Day for P15");
            System.out.println("    [2] 3 Days for P45");
            System.out.println("    [3] 5 Days for P75");
            System.out.println("    [4] Back");

            int choice = getInput("Enter choice: ", 1, 4);

            switch (choice) {
                case 1 -> subscriptionConfirmation("Unli Call", 1, 15);
                case 2 -> subscriptionConfirmation("Unli Call", 3, 45);
                case 3 -> subscriptionConfirmation("Unli Call", 5, 75);
                case 4 -> { return; }
            }
        }
    }



    public static void unliCallNTextPromo() {
        while (true) {
            clearScreen();
            System.out.println("** Enjoy Unli Call N Text **");
            System.out.println("    [1] 1 Day for P20");
            System.out.println("    [2] 3 Days for P60");
            System.out.println("    [3] 5 Days for P100");
            System.out.println("    [4] Back");

            int choice = getInput("Enter choice: ", 1, 4);

            switch (choice) {
                case 1 -> subscriptionConfirmation("Unli Call N Text", 1, 20);
                case 2 -> subscriptionConfirmation("Unli Call N Text", 3, 60);
                case 3 -> subscriptionConfirmation("Unli Call N Text", 5, 100);
                case 4 -> { return; }
            }
        }
    }

    public static void subscriptionConfirmation(String promoName, int days, int price) {
        System.out.println("\n\n");
        System.out.println("-> Get " + promoName + " good for " + days + "Day/s for only P" + price);
        System.out.println("    [1] Subscribe");
        System.out.println("    [2] Back");
        System.out.println("    [3] Exit");

        int subChoice = getInput("Enter choice: ", 1, 3);

        if (subChoice == 1) {
            if (balance < price) {
                System.out.println("\n[Transaction Error] Insufficient balance!");
                pressEnterToContinue();
            } else {
                balance -= price;
                System.out.println("\n\n");
                System.out.println("You have successfully registered to " + promoName + ".");
                System.out.println("Promo duration: " + days + " day/s.");
                System.out.println("Remaining load balance: P" + balance);
                System.out.println("Thank you for using our service!\n");
                pressEnterToContinue();
            }

        } else if (subChoice == 3) {
            exitMethod();
        }
    }


    public static void exitMethod() {
        clearScreen();
        System.out.println("Are you sure that you want to exit the program?");
        System.out.println("    [Y] Yes - Exit Program");
        System.out.println("    [N] No - Go Back");

        while (true) {
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                System.out.println("Exiting...");
                scanner.close();
                System.exit(0);
            } else if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")) {
                return;
            }

            System.out.println("Invalid Input. Please enter Y or N.");
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }


    public static int getInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a number.");
            }
        }
    }

    public static int getInput(String prompt, int min, int max) {
        while (true) {
            int number = getInput(prompt);

            if (number >= min && number <= max) {
                return number;
            }

            System.out.println("Invalid Input. Please enter a number from " + min + " to " + max + ".");
        }
    }
}