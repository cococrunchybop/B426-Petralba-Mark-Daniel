package com.joysistvi.day6.ifstatement;

public class ifElseLadder {
    public static void main(String[] args) {
        int day = -1;
        String result;

        if (day == 1) {
            result = "Sunday";
        } else if (day == 2) {
            result = "Monday";
        } else if (day == 3) {
            result = "Tuesday";
        } else if (day == 4) {
            result = "Wednesday";
        } else if (day == 5) {
            result = "Thursday";
        } else if (day == 6) {
            result = "Friday";
        } else if (day == 7) {
            result = "Saturday";
        } else {
            result = "Invalid Day";
        }

        System.out.println(result);
    }
}
