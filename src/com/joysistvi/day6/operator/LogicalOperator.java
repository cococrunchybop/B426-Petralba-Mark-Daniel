package com.joysistvi.day6.operator;

public class LogicalOperator {

    public static void main(String[] args) {
        // Compound Conditional Statement

        int age = 18;
        // 13 - 17
        boolean isJuniorHigh = age >= 13 && age < 17;

        System.out.println(isJuniorHigh);

        // boolean isAdult = !(age < 18);
        //System.out.println(isAdult);

        int num = 10;
        int num2= 20;

        //                     F              T
        //System.out.println(num < num2 && num > num2 && num == num || num != num2);
        //                      F           T
        //System.out.println(num > num2 | num < num2);
    }
}
