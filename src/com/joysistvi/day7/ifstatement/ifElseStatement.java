package com.joysistvi.day7.ifstatement;

public class ifElseStatement {
    public static void main (String[] args){


        //String result = (age >= 18) ? "Valid Age" : "Invalid Age";
        //System.out.println(result);

        int age = 11;

        String result = "";

        if (age>=18){
            result="Valid Age";
        }
        else {
            result = "Invalid Age";
        }

        System.out.println(result);

    }
}
