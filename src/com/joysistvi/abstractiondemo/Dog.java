package com.joysistvi.abstractiondemo;

// extends ONE class (Animal) and implements an interface (Swimmable).
public class Dog extends Animal implements Swimmable {

    public Dog(String name, int age) {
        super(name, age);   // runs Animal's constructor
    }

    // Required because Animal.makeSound() is abstract.
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }

    // Required because Swimmable.swim() is abstract. It must be public.
    @Override
    public void swim() {
        System.out.println(getName() + " is paddling in the water.");
    }
}
