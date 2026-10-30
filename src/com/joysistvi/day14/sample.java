package com.joysistvi.day14;

public class sample {
    public static void main(String[] args) {
        System.out.println("15. Math.tan() - Height of a Building");
        System.out.println("Situation: You stand 50 meters from a building and  look up at its top at a 30-degree angle. How tall is it?");
        System.out.println("Answer:");

        double buildingHeight = 50 * Math.tan(Math.toRadians(30));
        System.out.printf("Building height: %.2f meters", buildingHeight);
    }
}
