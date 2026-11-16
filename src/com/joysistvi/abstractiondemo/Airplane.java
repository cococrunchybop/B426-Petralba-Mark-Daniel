package com.joysistvi.abstractiondemo;

// Not an Animal at all, yet it can still be Flyable.
// Interfaces can connect classes that have nothing else in common.
public class Airplane implements Flyable {

    private String model;

    public Airplane(String model) {
        this.model = model;
    }

    @Override
    public void fly() {
        System.out.println("The " + model + " is flying with its jet engines.");
    }

    // Overriding a default method is optional. Airplane wants its own landing message.
    @Override
    public void land() {
        System.out.println("The " + model + " is landing on the runway.");
    }
}
