package com.joysistvi.day23.polymorphism.methodoverriding;

public class Animal {

    String name;
        private int age;
        String breed;

        public Animal(String name, int age) {
            this.name=name;
            this.age=age;
        }

        public void eat() {
            System.out.println(name + " is eating.");}

    public void makeSound() {System.out.println(name + " is make sound.");
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                "Age: " + age;
    }

}

