package com.joysistvi.day26.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LinkedListCrud{

    public static void main(String[] args) {
        List<String> dice = new LinkedList<>();

        // Create / Add item(s)
        dice.add("Blue");
        dice.add("Green");
        dice.add("Pink");
        dice.add("White");
        dice.add("Yellow");
        dice.add("Red");

        for (int i = 0; i < 3; i++){

            Collections.shuffle(dice);
            System.out.println((i+1) + ". " + dice.get(0));
        }

    }
}
