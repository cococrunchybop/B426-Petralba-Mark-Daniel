package com.joysistvi.day21.encap;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


//        Ballpen ballpen2 = new Ballpen("Pilot", "Black", 1, 20);
//        System.out.println(ballpen2);


        System.out.println("*** Ballpen Information Program ***");
        System.out.println("Please enter the details below:\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Enter point size: ");
        double pointSize = scanner.nextDouble();
        System.out.print("Enter weight in grams: ");
        double weightInGrams = scanner.nextDouble();

        Ballpen ballpen = new Ballpen(brand, color, pointSize, weightInGrams);


        System.out.println("\n=== Ballpen Details ===");
        System.out.println(ballpen);
    }
}
