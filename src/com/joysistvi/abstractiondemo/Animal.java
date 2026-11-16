package com.joysistvi.abstractiondemo;

// ABSTRACT CLASS: a partial blueprint for related classes.
// You cannot write "new Animal(...)". Only concrete subclasses can be created.
public abstract class Animal {

    // Shared state: every animal has a name and an age.
    private String name;
    private int age;

    // Abstract classes CAN have constructors. Subclasses run it with super(...).
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // ABSTRACT METHOD: no body, just a promise.
    // Every concrete subclass must provide its own version.
    public abstract void makeSound();

    // CONCRETE METHOD: real code that every animal inherits as it is.
    public void sleep() {
        System.out.println(name + " is sleeping. Zzz...");
    }

    // A concrete method can call an abstract one. Animal doesn't know which
    // animal it is running for, so the actual object decides which makeSound() runs.
    public void introduce() {
        System.out.print("Hi, I'm " + name + " (" + age + " years old). ");
        makeSound();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
