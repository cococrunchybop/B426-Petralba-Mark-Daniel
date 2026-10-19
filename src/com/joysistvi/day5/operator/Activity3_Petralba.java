package com.joysistvi.day5.operator;

public class Activity3_Petralba {
    public static void main (String[] args){
        //1.---Event Entry Check---
            int age = 18;
            boolean hasValidID = true;

            String allowed = age >= 18 && hasValidID == true ? "Yes" : "No";

            System.out.println(" ");
            System.out.println("--- Task 2: Event Entry Check ---");
            System.out.println("Allowed to enter the event: " + allowed);


        //2.---Class Status Check---
            boolean isWeekend = true;
            boolean isHoliday = true;

            System.out.println(" ");
            String announcement = isHoliday == true || isWeekend == true ? "Yes, no classes" : "No, today is a holiday";
            System.out.println("--- Task 3: Class Status Check ---");
            System.out.println("Are there no classes today? " + announcement);


        //3.---Pass or Fail Check---
            int score = 76;
            String result = score >= 75 ? "Passed" : "Failed";

            System.out.println(" ");
            System.out.println("--- Task 4: Pass or Fail Check ---");
            System.out.println("Score: " + score);
            System.out.println("Result:" + result);


        //4.---Larger Number Check---
            int num = 10;
            int num2 = 25;

            int larger = num > num2 ? num : num2;

            System.out.println(" ");
            System.out.println("--- Task 5: Larger Number Check ---");
            System.out.println("Number 1: " + num + ", Number 2: " + num2);
            System.out.println("The larger number is: " + larger);

    }
}
