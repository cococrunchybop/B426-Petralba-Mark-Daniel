package com.joysistvi.day13.Activity;


import java.util.Scanner;

public class miniActivity {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("*** Login ***");

        String correctUsername = "admin";
        String correctPassword = "admin123";

        final int m_attempts = 5;
        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < m_attempts) {
            System.out.print("Enter Username: ");
            String username = s.nextLine();

            System.out.print("Enter Password: ");
            String password = s.nextLine();

            if (username.isEmpty() || password.isEmpty()) {
                System.out.println("Username and Password cannot be empty.\n");
                continue;
            }

            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                System.out.println("Login Successfully!");
                System.out.println("Welcome, " + username + "!");
                loggedIn = true;
                break;
            }

            attempts++;
            int remaining = m_attempts - attempts;

            System.out.println("Invalid Username or Password.");
            System.out.println("Access Denied.");

            if (remaining > 0) {
                System.out.println("Attempts remaining: " + remaining + "\n");
            }
        }

        if (!loggedIn) {
            System.out.println("\nToo many failed attempts. Account locked.");
            System.out.println("Exiting program...");
        }

        s.close();
    }
}

