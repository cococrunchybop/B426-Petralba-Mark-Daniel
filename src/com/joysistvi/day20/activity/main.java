package com.joysistvi.day20.activity;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAccount user = new UserAccount();

        System.out.println("***********************************");
        System.out.println("*     User Account Registration   *");
        System.out.println("***********************************");
        System.out.println();


        while (true) {
            System.out.print("Enter First Name: ");
            try {
                user.setFirstName(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.print("Enter Middle Name: ");
        user.setMiddleName(scanner.nextLine());

        while (true) {
            System.out.print("Enter Last Name: ");
            try {
                user.setLastName(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            System.out.print("Enter Address: ");
            try {
                user.setAddress(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            System.out.print("Enter Email: ");
            try {
                user.setEmail(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        while (true) {
            System.out.print("Enter Password: ");
            try {
                user.setPassword(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println();
        user.displayAccountInfo();

        scanner.close();
    }
}