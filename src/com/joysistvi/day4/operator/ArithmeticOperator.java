package com.joysistvi.day4.operator;

public class ArithmeticOperator {

    public static void main(String[] args) {
        int num = 20;
        int num2 = 3;
        int sum = num + num2;
        int difference = num - num2;
        int product = num * num2;

        double quotient = (float) num / num2; // casting

        // byte short int long float double
//        System.out.println("Sum: " + sum);
//        System.out.println("Difference: " + difference);
//        System.out.println("Product: " + product);
//        System.out.println("Quotient: " + quotient);


        // % -> Modulus
        int number = 31;
        int number2 = 10;
        double remainder = number % number2;
        System.out.println(remainder);


        // Concatenation -> join strings
        // ASCII
        char letter = 'A'; // 65
        char letter2 = 'a'; // 97
        // System.out.println(letter + letter2);
    }
}
