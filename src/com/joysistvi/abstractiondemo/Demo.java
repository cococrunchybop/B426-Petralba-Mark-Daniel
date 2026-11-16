package com.joysistvi.abstractiondemo;

public class Demo {

    public static void main(String[] args) {

        Dog dog = new Dog("Rex", 3);
        Duck duck = new Duck("Donald", 2);
        Airplane plane = new Airplane("Boeing 747");

        // Neither of these lines compiles. Remove the // to see the error.
        // Animal generic = new Animal("Generic", 1);   // Animal is abstract; cannot be instantiated
        // Flyable thing = new Flyable();               // Flyable is abstract; cannot be instantiated

        // ---------- Abstract class ----------
        System.out.println("=== Abstract class: Animal ===");
        Animal[] animals = {dog, duck};   // Animal references holding concrete objects
        for (Animal animal : animals) {
            animal.introduce();           // inherited method that calls the child's makeSound()
            animal.sleep();               // inherited as it is
        }

        // ---------- Interfaces ----------
        System.out.println("\n=== Interface: Flyable ===");
        Flyable[] flyers = {duck, plane}; // a Duck and an Airplane share nothing except Flyable
        for (Flyable flyer : flyers) {
            flyer.fly();
            flyer.land();                 // Duck uses the default land(), Airplane overrides it
        }

        System.out.println("\n=== Interface: Swimmable ===");
        Swimmable[] swimmers = {dog, duck};
        for (Swimmable swimmer : swimmers) {
            swimmer.swim();
        }

        System.out.println("\n=== Constants and static methods ===");
        System.out.println("Gravity constant: " + Flyable.GRAVITY);
        Flyable.printRules();

        // ---------- Checking an ability at runtime ----------
        System.out.println("\n=== Checking a capability with instanceof ===");
        for (Animal animal : animals) {
            if (animal instanceof Flyable) {
                ((Flyable) animal).fly();   // cast needed: an Animal reference doesn't know fly()
            } else {
                System.out.println(animal.getName() + " can't fly.");
            }
        }
    }
}
