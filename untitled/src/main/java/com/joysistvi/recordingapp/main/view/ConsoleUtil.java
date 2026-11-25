package com.joysistvi.recordingapp.main.view;

import java.util.Scanner;


public final class ConsoleUtil {

    private ConsoleUtil() {
    }

    // ------------------------------------------------------------------
    // Screen handling
    // ------------------------------------------------------------------


    public static void clearScreen() {
        try {
            if (System.console() == null) {
                for (int i = 0; i < 40; i++) {
                    System.out.println();
                }
                return;
            }
            String os = System.getProperty("os.name", "").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 40; i++) {
                System.out.println();
            }
        }
    }


    public static void pause(Scanner scanner) {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // ------------------------------------------------------------------
    // Input
    // ------------------------------------------------------------------


    public static int promptForInt(Scanner scanner, String promptMessage) {
        while (true) {
            System.out.print(promptMessage);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be blank. Please enter a number.");
                continue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid whole number.");
            }
        }
    }

    public static String promptForString(Scanner scanner, String promptMessage) {
        System.out.print(promptMessage);
        String line = scanner.nextLine();
        return line == null ? "" : line;
    }

    // ------------------------------------------------------------------
    // Output
    // ------------------------------------------------------------------

    public static String truncate(String text, int maxLength) {
        if (text == null) {
            return "";
        }

        String clean = text.replaceAll("[\\r\\n\\t]+", " ");
        if (clean.length() <= maxLength) {
            return clean;
        }
        if (maxLength <= 3) {
            return clean.substring(0, maxLength);
        }
        return clean.substring(0, maxLength - 3) + "...";
    }
}
