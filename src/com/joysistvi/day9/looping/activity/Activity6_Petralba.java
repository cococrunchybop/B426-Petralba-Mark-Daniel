package com.joysistvi.day9.looping.activity;

import java.util.Scanner;

public class Activity6_Petralba {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the multiplication table you want to display: ");
        int number = s.nextInt();

        System.out.println();
        System.out.println("Multiplication Table of " + number);

        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }

        s.close();
    }
}