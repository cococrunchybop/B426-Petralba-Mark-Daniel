package com.joysistvi.day16.methods;

import java.util.Scanner;

public class Activity12 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        dialUssdCode();
    }

    public static void dialUssdCode() {

        System.out.println("Dial *143# to access Globe Services");

        // Keep asking until the correct code is entered
        while (true) {
            System.out.print("Enter USSD Code: ");
            String ussdCode = scanner.nextLine().trim();

            if (ussdCode.equals("*143#")) {
                break;
            }
            System.out.println("Invalid USSD Code!");
        }

        promptMainMenu();
    }

    public static void promptMainMenu() {

        while (true) {
            System.out.println();
            System.out.println("        * Load Registration *");
            System.out.println("    [1] Go+");
            System.out.println("    [2] Go");
            System.out.println("    [3] GoEXTRA");
            System.out.println("    [4] UNLI 5G");
            System.out.println("    [5] ALLNET");
            System.out.println("    [0] Exit");

            int mainChoice = getInput("Enter a choice: ");

            switch (mainChoice) {
                case 1 -> goPlusPromo();
                case 2 -> goPromo();
                case 3 -> goExtraPromo();
                case 4 -> unli5GPromo();
                case 5 -> allNetPromo();
                case 0 -> exitMethod();
                default -> System.out.println("Invalid Input. Please try again.");
            }
        }
    }

    public static void goPlusPromo() {
        System.out.println("\n\n\n       *** Go+ Promos ***");
        System.out.println("    [1] Go+99");
        System.out.println("    [2] Go+129");
        System.out.println("    [3] Go+149");
        System.out.println("    [4] Back");
        System.out.println("    [0] Exit");

        int goPlusChoice = getInput("Enter a choice: ");

        switch (goPlusChoice) {
            case 1 -> promoDetailsMenu("Go+99", 8, 8);
            case 2 -> promoDetailsMenu("Go+129", 16, 16);
            case 3 -> promoDetailsMenu("Go+149", 32, 32);
            case 4 -> { return; }
            case 0 -> exitMethod();
            default -> System.out.println("Invalid Input. Please try again.");
        }
    }

    public static void goPromo() {
        System.out.println();
        System.out.println("        *** Go Promos ***");
        System.out.println("    [1] Go50");
        System.out.println("    [2] Go90");
        System.out.println("    [3] Go120");
        System.out.println("    [4] Back");
        System.out.println("    [0] Exit");

        int goChoice = getInput("Enter a choice: ");

        switch (goChoice) {
            case 1 -> promoDetailsMenu("Go50", 5, 3);
            case 2 -> promoDetailsMenu("Go90", 8, 5);
            case 3 -> promoDetailsMenu("Go120", 12, 8);
            case 4 -> { return; }
            case 0 -> exitMethod();
            default -> System.out.println("Invalid Input. Please try again.");
        }
    }

    // NOTE: promo names and GB values below are placeholders - replace with the real ones
    public static void goExtraPromo() {
        System.out.println();
        System.out.println("        *** GoEXTRA Promos ***");
        System.out.println("    [1] GoEXTRA99");
        System.out.println("    [2] GoEXTRA199");
        System.out.println("    [3] GoEXTRA299");
        System.out.println("    [4] Back");
        System.out.println("    [0] Exit");

        int goExtraChoice = getInput("Enter a choice: ");

        switch (goExtraChoice) {
            case 1 -> promoDetailsMenu("GoEXTRA99", 10, 5);
            case 2 -> promoDetailsMenu("GoEXTRA199", 20, 10);
            case 3 -> promoDetailsMenu("GoEXTRA299", 30, 15);
            case 4 -> { return; }
            case 0 -> exitMethod();
            default -> System.out.println("Invalid Input. Please try again.");
        }
    }

    // NOTE: placeholder promo names and GB values
    public static void unli5GPromo() {
        System.out.println();
        System.out.println("        *** UNLI 5G Promos ***");
        System.out.println("    [1] UNLI 5G 99");
        System.out.println("    [2] UNLI 5G 149");
        System.out.println("    [3] UNLI 5G 199");
        System.out.println("    [4] Back");
        System.out.println("    [0] Exit");

        int unliChoice = getInput("Enter a choice: ");

        switch (unliChoice) {
            case 1 -> promoDetailsMenu("UNLI 5G 99", 6, 2);
            case 2 -> promoDetailsMenu("UNLI 5G 149", 10, 4);
            case 3 -> promoDetailsMenu("UNLI 5G 199", 15, 6);
            case 4 -> { return; }
            case 0 -> exitMethod();
            default -> System.out.println("Invalid Input. Please try again.");
        }
    }

    // NOTE: placeholder promo names and GB values
    public static void allNetPromo() {
        System.out.println();
        System.out.println("        *** ALLNET Promos ***");
        System.out.println("    [1] ALLNET50");
        System.out.println("    [2] ALLNET99");
        System.out.println("    [3] ALLNET149");
        System.out.println("    [4] Back");
        System.out.println("    [0] Exit");

        int allNetChoice = getInput("Enter a choice: ");

        switch (allNetChoice) {
            case 1 -> promoDetailsMenu("ALLNET50", 3, 2);
            case 2 -> promoDetailsMenu("ALLNET99", 6, 4);
            case 3 -> promoDetailsMenu("ALLNET149", 12, 8);
            case 4 -> { return; }
            case 0 -> exitMethod();
            default -> System.out.println("Invalid Input. Please try again.");
        }
    }

    public static void promoDetailsMenu(String promoName, int gb, int freeGB) {
        System.out.println();
        System.out.println("        *** " + promoName + " Promo ***");
        System.out.println("-> Get " + gb + " GB for all sites + " + freeGB + " GB.");
        System.out.println("    [1] Subscribe");
        System.out.println("    [2] Back");

        int actionChoice = getInput("Enter a choice: ");

        switch (actionChoice) {
            case 1 -> System.out.println("\nYou have successfully subscribed to " + promoName + "!");
            case 2 -> { return; }
            default -> System.out.println("Invalid Input. Please try again.");
        }
    }

    public static void exitMethod() {
        while (true) {
            System.out.println("Are you sure that you want to exit the program?");
            System.out.println("[Y] Yes - Exit Program");
            System.out.println("[N] No - Return to Main Menu");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.startsWith("Y")) {
                System.out.println("Exiting...");
                System.exit(0);
            } else if (choice.startsWith("N")) {
                System.out.println("Returning Back to Main Menu");
                return;
            } else {
                System.out.println("Invalid Input. Please enter Y or N.");
            }
        }
    }

    public static int getInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a number");
            }
        }
    }

    // Clears the terminal using ANSI escape codes
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}