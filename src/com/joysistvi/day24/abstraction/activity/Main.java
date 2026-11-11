package com.joysistvi.day24.abstraction.activity;

public class Main {

    public static void main(String[] args) {

        // Creating a Pen with "new" does not compile. javac says: "Pen is abstract; cannot be instantiated".
        // Pen is abstract and its write() has no body, so a Pen object would have nothing to run when
        // write() is called. Only concrete subclasses that fill in write(), like Ballpen and Highlighter, can be created.

        Ballpen ballpen = new Ballpen("Pilot", "Black", 1.0, 20.0);
        Highlighter highlighter = new Highlighter("HBW", "Pink", "Neon", 50.0);

        System.out.println("=== testPen() ===");
        testPen(ballpen);
        testPen(highlighter);

        System.out.println("=== testRefillable() ===");
        testRefillable(ballpen);
        testRefillable(highlighter);

        System.out.println("=== testClickable() ===");
        testClickable(ballpen);

        // testClickable(highlighter) does not compile. javac says:
        // "incompatible types: Highlighter cannot be converted to Clickable".
        // The method only accepts a Clickable, but Highlighter implements only Refillable. It has no
        // click() method, so the compiler rejects the call before the program runs.
    }

    public static void testPen(Pen pen) {
        System.out.println(pen);
        pen.write();
        pen.store();
        System.out.println();
    }

    public static void testRefillable(Refillable refillable) {
        refillable.refill();
        System.out.println("Maximum refills: " + Refillable.MAX_REFILLS);
        System.out.println();
    }

    public static void testClickable(Clickable clickable) {
        clickable.click();
    }
}

// Abstract class (extends): a partial blueprint for related classes. It can hold fields, a constructor
// and regular methods like store(), plus abstract methods like write(). A class can extend only one.
// Interface (implements): a contract of abilities (Refillable, Clickable). It holds constants and
// methods with no body. A class can implement many, like Ballpen does with both interfaces.
