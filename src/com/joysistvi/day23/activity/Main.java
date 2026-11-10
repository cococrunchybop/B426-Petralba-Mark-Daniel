package com.joysistvi.day23.activity;

public class Main {

    public static void main(String[] args) {


        Pen pen1 = new Pen("Generic", "Blue", 15.0);
        Pen pen2 = new Ballpen("Pilot", "Black", 1.0, 20.0);
        Pen pen3 = new Highlighter("HBW", "Pink", "Neon", 50.0);

        System.out.println("=== Calling methods on Pen references ===");

        System.out.println("--- Pen ---");
        pen1.write();
        pen1.refill();

        System.out.println("\n--- Ballpen ---");
        pen2.write();
        pen2.refill();

        System.out.println("\n--- Highlighter ---");
        pen3.write();
        pen3.refill();

        System.out.println("\n=== testPen() ===");
        testPen(pen1);
        testPen(pen2);
        testPen(pen3);


        System.out.println("\n=== Overloading ===");
        pen3.write("Hello");
        pen3.write("Hello", 3);
    }

    public static void testPen(Pen pen) {
        System.out.println(pen);
        pen.write();
        pen.refill();
        System.out.println();
    }
}

