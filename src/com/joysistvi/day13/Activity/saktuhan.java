package com.joysistvi.day13.Activity;

public class saktuhan {
    public static void main (String[] args){
        String text = "banana";
        int index = text.indexOf('a');

        while (index != -1) {
            System.out.println("Found 'a' at index " + index);
            index = text.indexOf('a', index + 1);       // search again AFTER the last match
        }
    }
}
