package com.joysistvi.day3.variable;

public class Variable {

    public static void main(String[] args) { // main method
        // initialize a variable
        byte age = 26;
        short num = 500;
        int num2 = 50_000;
        long num3 = 10000000000L;
        System.out.println(num3);
        //System.out.println(age);

        float average = 99.90f;
        double price = 10.555555d;
        //System.out.println(price);

        boolean is_active = false;
        is_active = true;
        System.out.println(is_active);

        char middleInitial = 'B';
        //System.out.println(middleInitial);

        String firstName = "Mark Daniel";
        String lastName = "Petralba";


        System.out.println("Full Name: " + lastName + ',' + ' ' + firstName + " " + middleInitial + ".");
        // Concatenation (+) -> joining strings
        // Full Name: Mark Daniel Petralba.

    }

}

// Syntax -> structure of code
// Main Method: psvm + tab
// Print Statement: sout + tab