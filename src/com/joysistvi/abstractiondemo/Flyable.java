package com.joysistvi.abstractiondemo;

// INTERFACE: a contract. A class that says "implements Flyable" promises to provide fly().
public interface Flyable {

    // Constant: fields in an interface are automatically public static final.
    double GRAVITY = 9.8;

    // Abstract method: automatically public abstract, so no body and no keywords needed.
    void fly();

    // Default method (Java 8+): has a body. Implementing classes get it for free
    // and may override it if they want something different.
    default void land() {
        System.out.println("Landing safely.");
    }

    // Static method (Java 8+): belongs to the interface itself.
    // Call it as Flyable.printRules(), not through an object.
    static void printRules() {
        System.out.println("Anything that flies must beat gravity (" + GRAVITY + " m/s^2).");
    }
}
