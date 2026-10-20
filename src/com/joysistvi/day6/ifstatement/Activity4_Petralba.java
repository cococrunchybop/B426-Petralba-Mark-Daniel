package com.joysistvi.day6.ifstatement;

public class Activity4_Petralba {
    public static void main(String[] args){
        int age = 25;
        String result;

        if (age>=0){
            if(age<=0){
                result= "Baby";
            } else if (age<=3) {
                result= "Child";
            } else if (age<=13) {
                result= "Teenager";
            } else if (age<=20) {
                result= "Young Adult";
            } else if (age<=30) {
                result = "Adult";
            } else if (age<=60) {
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
