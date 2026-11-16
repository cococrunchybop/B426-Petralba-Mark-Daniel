package com.joysistvi.abstractiondemo;

// A class can extend only ONE class, but it can implement as many interfaces as it needs.
public class Duck extends Animal implements Flyable, Swimmable {

    public Duck(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("Quack!");
    }

    @Override
    public void fly() {
        System.out.println(getName() + " is flapping its wings and flying.");
    }

    @Override
    public void swim() {
        System.out.println(getName() + " is floating on the pond.");
    }

    // land() is not written here: Duck uses the default method from Flyable.
}
