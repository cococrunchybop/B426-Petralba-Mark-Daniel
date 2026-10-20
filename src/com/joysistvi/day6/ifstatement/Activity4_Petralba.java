package com.joysistvi.day6.ifstatement;

public class Activity4_Petralba {
    public static void main(String[] args){
        int age = 25;
        String result;

        if (age>=0){
            if(age<=2){
                result = "Baby";
            } else if (age<=12) {
                result = "Child";
            } else if (age<=19) {
                result = "Teenager";
            } else if (age<=29) {
                result = "Young Adult";
            } else if (age<=59) {
                result = "Adult";
            } else if (age<=100) {
                result = "Senior";
            } else {
                result = "Invalid age";
            }

        } else {
            result = "Invalid age";
        }

        System.out.println("Age: " + age);
        System.out.println("Category: " + result);

    }
}
