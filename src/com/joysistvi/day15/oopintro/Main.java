package com.joysistvi.day15.oopintro;

public class Main {

    public static void main(String[] args) {
        // Class Instantiation / Create an object of a class
        Laptop laptop = new Laptop();
        laptop.brand = "HP";
        laptop.color = "Gray";
        laptop.weightInKg = 1.5;
        laptop.cpu = "i5";
        laptop.ramInGb = 12;

        laptop.browseInternet();
        laptop.createDocuments();
        laptop.sendMessages();
        laptop.transferFiles();

        Laptop laptop2 = new Laptop();
        laptop2.brand = "Lenovo";
        laptop2.color = "Black";
        laptop2.weightInKg = 1;
        laptop2.cpu = "Ryzen7";
        laptop2.ramInGb = 16;

        laptop2.sendMessages();
        laptop2.transferFiles();
        laptop2.createDocuments();

        Laptop laptop3 = new Laptop();
        laptop3.brand = "Acer";
        laptop3.color = "White";
        laptop3.cpu = "Ryzen 9";
        laptop3.ramInGb = 32;
        laptop3.weightInKg = 2;

        laptop3.createDocuments();
        laptop3.transferFiles();
        laptop3.browseInternet();


    }
} // Ctrl + Alt + l

