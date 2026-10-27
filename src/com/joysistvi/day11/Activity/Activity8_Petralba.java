package com.joysistvi.day11.Activity;

import java.util.Scanner;

public class Activity8_Petralba {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] numbers = new int[5];

        System.out.println("Enter 5 numbers:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Number " + (i + 1) + ": ");
            numbers[i] = input.nextInt();
        }

        System.out.println();
        System.out.println("You entered:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Index " + i + ": " + numbers[i]);
        }

        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        double average = sum / 5.0;

        System.out.println();
        System.out.println("Sum = " + sum);
        System.out.println("Average = " + average);


        input.close();
    }
}