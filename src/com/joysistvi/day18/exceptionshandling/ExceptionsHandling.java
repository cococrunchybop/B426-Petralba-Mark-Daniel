package com.joysistvi.day18.exceptionshandling;

public class ExceptionsHandling {
    public static void main(String[] args) {
        int [] numbers = {1,2,3,4,5,6,7,8,9,10};

        try {

            System.out.println(numbers[11]);


        } catch (Exception e){
            System.out.println(e);
        }

        System.out.println("hello");
    }
}
