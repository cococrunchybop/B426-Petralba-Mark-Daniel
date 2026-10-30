package com.joysistvi.day14;

public class saktongSampleLang {
    public static void main(String[] args) {

        System.out.println("===== Math Class: Real-Life Examples =====\n");

        //absRealLife();
        //maxRealLife();
        //minRealLife();
        //powRealLife();
        //sqrtRealLife();
        //roundRealLife();
        //ceilRealLife();
        //floorRealLife();
        //randomRealLife();
        //logRealLife();
        //log10RealLife();
        //expRealLife();
        //sinRealLife();
        //cosRealLife();
        tanRealLife();
    }


    static void absRealLife() {
        System.out.println("1. Math.abs() - Temperature Difference");
        System.out.println("Situation: Manila is 33 degrees and Baguio is 22 degrees. How big is the difference, no matter which is hotter?");
        System.out.println("Sample Code:");
        System.out.println("    int manila = 33;");
        System.out.println("    int baguio = 22;");
        System.out.println("    int difference = Math.abs(baguio - manila);");
        System.out.println("    System.out.println(\"The difference is \" + difference + \" degrees\");");

        System.out.println("Output:");
        int manila = 33;
        int baguio = 22;
        int difference = Math.abs(baguio - manila);
        System.out.println("The difference is " + difference + " degrees");
        System.out.println();
    }


    static void maxRealLife() {
        System.out.println("2. Math.max() - Highest Quiz Score");
        System.out.println("Situation: A student took two quizzes and only the higher score will be recorded.");
        System.out.println("Sample Code:");
        System.out.println("    int quiz1 = 88;");
        System.out.println("    int quiz2 = 92;");
        System.out.println("    int highest = Math.max(quiz1, quiz2);");
        System.out.println("    System.out.println(\"Recorded score: \" + highest);");

        System.out.println("Output:");
        int quiz1 = 88;
        int quiz2 = 92;
        int highest = Math.max(quiz1, quiz2);
        System.out.println("Recorded score: " + highest);
        System.out.println();
    }


    static void minRealLife() {
        System.out.println("3. Math.min() - Cheaper Price");
        System.out.println("Situation: The same item costs 45.50 in Store A and 39.99 in Store B. Which price do you pay?");
        System.out.println("Sample Code:");
        System.out.println("    double storeA = 45.50;");
        System.out.println("    double storeB = 39.99;");
        System.out.println("    double cheaper = Math.min(storeA, storeB);");
        System.out.println("    System.out.println(\"You pay: \" + cheaper);");

        System.out.println("Output:");
        double storeA = 45.50;
        double storeB = 39.99;
        double cheaper = Math.min(storeA, storeB);
        System.out.println("You pay: " + cheaper);
        System.out.println();
    }


    static void powRealLife() {
        System.out.println("4. Math.pow() - Bacteria Growth");
        System.out.println("Situation: 100 bacteria double every hour. How many are there after 3 hours?");
        System.out.println("Sample Code:");
        System.out.println("    int start = 100;");
        System.out.println("    double total = start * Math.pow(2, 3);");
        System.out.println("    System.out.println(\"Bacteria after 3 hours: \" + total);");

        System.out.println("Output:");
        int start = 100;
        double total = start * Math.pow(2, 3);
        System.out.println("Bacteria after 3 hours: " + total);
        System.out.println();
    }


    static void sqrtRealLife() {
        System.out.println("5. Math.sqrt() - Straight-Line Distance");
        System.out.println("Situation: You walk 3 blocks east and 4 blocks north. How far are you from the start in a straight line?");
        System.out.println("Sample Code:");
        System.out.println("    int east = 3;");
        System.out.println("    int north = 4;");
        System.out.println("    double distance = Math.sqrt(east * east + north * north);");
        System.out.println("    System.out.println(\"Distance: \" + distance + \" blocks\");");

        System.out.println("Output:");
        int east = 3;
        int north = 4;
        double distance = Math.sqrt(east * east + north * north);
        System.out.println("Distance: " + distance + " blocks");
        System.out.println();
    }


    static void roundRealLife() {
        System.out.println("6. Math.round() - Final Grade");
        System.out.println("Situation: A student's computed grade is 87.6 and the school rounds it to the nearest whole number.");
        System.out.println("Sample Code:");
        System.out.println("    double grade = 87.6;");
        System.out.println("    long finalGrade = Math.round(grade);");
        System.out.println("    System.out.println(\"Final grade: \" + finalGrade);");

        System.out.println("Output:");
        double grade = 87.6;
        long finalGrade = Math.round(grade);
        System.out.println("Final grade: " + finalGrade);
        System.out.println();
    }


    static void ceilRealLife() {
        System.out.println("7. Math.ceil() - Buses Needed");
        System.out.println("Situation: 45 students are going on a field trip and each bus holds 10. You can't rent half a bus, so always round UP.");
        System.out.println("Sample Code:");
        System.out.println("    int students = 45;");
        System.out.println("    int buses = (int) Math.ceil(students / 10.0);");
        System.out.println("    System.out.println(\"Buses needed: \" + buses);");

        System.out.println("Output:");
        int students = 45;
        int buses = (int) Math.ceil(students / 10.0);
        System.out.println("Buses needed: " + buses);
        System.out.println();
    }


    static void floorRealLife() {
        System.out.println("8. Math.floor() - Items You Can Buy");
        System.out.println("Situation: You have 100 pesos and each item costs 30 pesos. You can only buy whole items, so always round DOWN.");
        System.out.println("Sample Code:");
        System.out.println("    int money = 100;");
        System.out.println("    int items = (int) Math.floor(money / 30.0);");
        System.out.println("    System.out.println(\"Items you can buy: \" + items);");

        System.out.println("Output:");
        int money = 100;
        int items = (int) Math.floor(money / 30.0);
        System.out.println("Items you can buy: " + items);
        System.out.println();
    }


    static void randomRealLife() {
        System.out.println("9. Math.random() - Rolling a Dice");
        System.out.println("Situation: A board game needs a dice roll from 1 to 6.");
        System.out.println("Sample Code:");
        System.out.println("    int dice = (int) (Math.random() * 6) + 1;");
        System.out.println("    System.out.println(\"You rolled: \" + dice);");

        System.out.println("Output:");
        int dice = (int) (Math.random() * 6) + 1;
        System.out.println("You rolled: " + dice + "  (changes every run)");
        System.out.println();
    }


    static void logRealLife() {
        System.out.println("10. Math.log() - Time to Double Your Money");
        System.out.println("Situation: Money grows continuously at 7% per year. About how many years until it doubles?");
        System.out.println("Sample Code:");
        System.out.println("    double rate = 0.07;");
        System.out.println("    double years = Math.log(2) / rate;");
        System.out.println("    System.out.printf(\"Years to double: %.2f%n\", years);");

        System.out.println("Output:");
        double rate = 0.07;
        double years = Math.log(2) / rate;
        System.out.printf("Years to double: %.2f%n", years);
        System.out.println();
    }


    static void log10RealLife() {
        System.out.println("11. Math.log10() - Sound Loudness (Decibels)");
        System.out.println("Situation: A sound is 1000 times more intense than the quietest sound we can hear. How loud is it in decibels?");
        System.out.println("Sample Code:");
        System.out.println("    double decibels = 10 * Math.log10(1000);");
        System.out.println("    System.out.println(\"Loudness: \" + decibels + \" dB\");");

        System.out.println("Output:");
        double decibels = 10 * Math.log10(1000);
        System.out.println("Loudness: " + decibels + " dB");
        System.out.println();
    }


    static void expRealLife() {
        System.out.println("12. Math.exp() - Continuous Growth of Savings");
        System.out.println("Situation: 1000 pesos grows continuously at 5% per year for 2 years. How much will it be?");
        System.out.println("Sample Code:");
        System.out.println("    double amount = 1000 * Math.exp(0.05 * 2);");
        System.out.println("    System.out.printf(\"Savings after 2 years: %.2f%n\", amount);");

        System.out.println("Output:");
        double amount = 1000 * Math.exp(0.05 * 2);
        System.out.printf("Savings after 2 years: %.2f%n", amount);
        System.out.println();
    }


    static void sinRealLife() {
        System.out.println("13. Math.sin() - Height of a Ramp");
        System.out.println("Situation: A 10-meter ramp is tilted at 30 degrees. How high does it rise?");
        System.out.println("Sample Code:");
        System.out.println("    double height = 10 * Math.sin(Math.toRadians(30));");
        System.out.println("    System.out.printf(\"Height: %.2f meters%n\", height);");

        System.out.println("Output:");
        double height = 10 * Math.sin(Math.toRadians(30));
        System.out.printf("Height: %.2f meters%n", height);
        System.out.println();
    }


    static void cosRealLife() {
        System.out.println("14. Math.cos() - Ladder Distance from the Wall");
        System.out.println("Situation: A 10-meter ladder leans on a wall at a 60-degree angle with the ground. How far is its base from the wall?");
        System.out.println("Sample Code:");
        System.out.println("    double baseDistance = 10 * Math.cos(Math.toRadians(60));");
        System.out.println("    System.out.printf(\"Base distance: %.2f meters%n\", baseDistance);");

        System.out.println("Output:");
        double baseDistance = 10 * Math.cos(Math.toRadians(60));
        System.out.printf("Base distance: %.2f meters%n", baseDistance);
        System.out.println();
    }


    static void tanRealLife() {
        System.out.println("15. Math.tan() - Height of a Building");
        System.out.println("Situation: You stand 50 meters from a building and look up at its top at a 30-degree angle. How tall is it?");
        System.out.println("Answer:");

        double buildingHeight = 50 * Math.tan(Math.toRadians(30));
        System.out.printf("Building height: %.2f meters%n", buildingHeight);
        System.out.println();
    }
}
