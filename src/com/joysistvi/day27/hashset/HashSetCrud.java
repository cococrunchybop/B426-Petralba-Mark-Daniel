package com.joysistvi.day27.hashset;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class HashSetCrud {

    public static void main(String[] args) {
        // Lotto Simulator
//        HashSet<Integer> lottoNumbers = new HashSet<>();
//        Random random = new Random();
//
//        System.out.println("=== Lotto Simulator ===");
//
//        // 0
//        while (lottoNumbers.size() < 6) {
//            lottoNumbers.add(random.nextInt(58) + 1);
//
//        }
//
//        System.out.println(lottoNumbers);

        Set<Integer> bingoNumbers = new HashSet<>();
        Random random = new Random();

        String letter;
        int roll = random.nextInt(75) + 1;

        if (roll < 16) {
            letter = "B";
        } else if (roll < 31) {
            letter = "I";
        } else if (roll < 46) {
            letter = "N";
        } else if (roll < 61) {
            letter = "G";
        } else {
            letter = "O";
        }

        System.out.println("Sa letra ng " + letter + " : " + roll);
    }
}

/*
HashSet<Integer> evenNumbers = new HashSet<>();

        // Create item(s)
        evenNumbers.add(2);
        evenNumbers.add(4);
        evenNumbers.add(6);
        evenNumbers.add(8);
        evenNumbers.add(10);
        evenNumbers.add(10);

        // Delete an item / Delete all items
//        evenNumbers.remove(10);
//        evenNumbers.removeAll(evenNumbers);
//        evenNumbers.clear();

        evenNumbers.contains(10);

        // Print all the elements
        evenNumbers.forEach(System.out::println);
 */
