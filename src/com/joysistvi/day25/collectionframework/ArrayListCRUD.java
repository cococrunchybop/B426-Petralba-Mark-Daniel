package com.joysistvi.day25.collectionframework;

import java.util.ArrayList;
import java.util.List;

public class ArrayListCRUD {
    public static void main(String[] args) {
        List<String> colors  = new ArrayList<String>();

        colors.add("Red");
        colors.add("Blue");
        colors.add("Green");
        colors.add(1,"Orange");

        System.out.println(colors);
    }
}
