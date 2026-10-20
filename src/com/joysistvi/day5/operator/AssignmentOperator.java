package com.joysistvi.day5.operator;

public class AssignmentOperator {

    public static void main(String[] args) {
        // Simple Assignment
        int num = 10;

        // Compound Assignment
        int num2 = 21;
        //      20
        //num2 = num % 2;
        num2 %= 10;
        System.out.println(num2);
    }
}
