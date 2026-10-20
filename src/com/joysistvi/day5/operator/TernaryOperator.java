package com.joysistvi.day5.operator;

public class TernaryOperator {

    public static void main(String[] args) {
        // Syntax
        // (condition) ? true block : false block;

        int age = 18;

        String result = (age >= 18) ? "Valid Age" : "Invalid Age";
        //System.out.println(result);

        int num = 10;
        int num2 = 20;

        int bigger = (num > num2) ? num : num2;
        System.out.println("Bigger number: " + bigger);

        int number = 2;

        boolean isEvenNumber = number % 2 == 0;
        System.out.println(isEvenNumber);
        // divide number from 2 and get the remainder
        // check if the remainder is 0
    }
}
