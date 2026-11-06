package com.joysistvi.day16.methods;

import java.sql.SQLOutput;

public class JavaMethod {
    public static void main(String[] args) {
        int year = 2026;
        if (year == 2026){
            greet("Cielo", 26);
        } else if (year == 2027){
            greet("Cielo", 27);
        }


    }

    public static void greet (String name, int age){
        printMoto("Hello, my name is "+ name+ "!");
        printMoto("I am " + age + " years old.");
    }

    public static void printMoto(String word) {
        System.out.println(word);

    }

}

/*
    public static void main(String[] args){
        System.out.println(sum(5, 10));
    }

    public static int sum(int a, int b){
        return a + b;
    }


*/
