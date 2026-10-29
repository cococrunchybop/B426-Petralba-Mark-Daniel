package com.joysistvi.day14;

public class Activity11_Petralba {

    public static void main(String[] args) {

        System.out.println("===== Activity 11: Math Class =====\n");

        absMethod();
        maxMethod();
        minMethod();
        powMethod();
        sqrtMethod();
        roundMethod();
        ceilMethod();
        floorMethod();
        randomMethod();
        logMethod();
        log10Method();
        expMethod();
        sinMethod();
        cosMethod();
        tanMethod();
    }


    static void absMethod() {
        System.out.println("1. Math.abs()");
        System.out.println("Description: Returns the absolute value of a number (removes the negative sign).");
        System.out.println("Sample Code:");
        System.out.println("    int result = Math.abs(-25);");
        System.out.println("    System.out.println(result);");

        int result = Math.abs(-25);
        System.out.println("Output: " + result);
        System.out.println();
    }


    static void maxMethod() {
        System.out.println("2. Math.max()");
        System.out.println("Description: Returns the larger of two values.");
        System.out.println("Sample Code:");
        System.out.println("    int result = Math.max(10, 20);");
        System.out.println("    System.out.println(result);");

        int result = Math.max(10, 20);
        System.out.println("Output: " + result);
        System.out.println();
    }


    static void minMethod() {
        System.out.println("3. Math.min()");
        System.out.println("Description: Returns the smaller of two values.");
        System.out.println("Sample Code:");
        System.out.println("    int result = Math.min(10, 20);");
        System.out.println("    System.out.println(result);");

        int result = Math.min(10, 20);
        System.out.println("Output: " + result);
        System.out.println();
    }


    static void powMethod() {
        System.out.println("4. Math.pow()");
        System.out.println("Description: Raises a number to the power of another number (exponentiation).");
        System.out.println("Sample Code:");
        System.out.println("    double result = Math.pow(2, 5);");
        System.out.println("    System.out.println(result);");

        double result = Math.pow(2, 5);
        System.out.println("Output: " + result);
        System.out.println();
    }


    static void sqrtMethod() {
        System.out.println("5. Math.sqrt()");
        System.out.println("Description: Returns the square root of a number.");
        System.out.println("Sample Code:");
        System.out.println("    double result = Math.sqrt(81);");
        System.out.println("    System.out.println(result);");

        double result = Math.sqrt(81);
        System.out.println("Output: " + result);
        System.out.println();
    }


    static void roundMethod() {
        System.out.println("6. Math.round()");
        System.out.println("Description: Rounds a decimal number to the nearest whole number.");
        System.out.println("Sample Code:");
        System.out.println("    long result = Math.round(7.6);");
        System.out.println("    System.out.println(result);");

        long result = Math.round(7.6);
        System.out.println("Output: " + result);
        System.out.println();
    }


    static void ceilMethod() {
        System.out.println("7. Math.ceil()");
        System.out.println("Description: Rounds a number UP to the nearest whole number (ceiling).");
        System.out.println("Sample Code:");
        System.out.println("    double result = Math.ceil(4.2);");
        System.out.println("    System.out.println(result);");

        double result = Math.ceil(4.2);
        System.out.println("Output: " + result);
        System.out.println();
    }


    static void floorMethod() {
        System.out.println("8. Math.floor()");
        System.out.println("Description: Rounds a number DOWN to the nearest whole number (floor).");
        System.out.println("Sample Code:");
        System.out.println("    double result = Math.floor(4.9);");
        System.out.println("    System.out.println(result);");

        double result = Math.floor(4.9);
        System.out.println("Output: " + result);
        System.out.println();
    }


    static void randomMethod() {
        System.out.println("9. Math.random()");
        System.out.println("Description: Returns a random decimal number from 0.0 up to (but not including) 1.0.");
        System.out.println("Sample Code:");
        System.out.println("    double result = Math.random();");
        System.out.println("    System.out.println(result);");

        double result = Math.random();
        System.out.println("Output: " + result + "  (changes every run)");
        System.out.println();
    }


    static void logMethod() {
        System.out.println("10. Math.log()");
        System.out.println("Description: Returns the natural logarithm (base e) of a number.");
        System.out.println("Sample Code:");
        System.out.println("    double result = Math.log(10);");
        System.out.println("    System.out.println(result);");

        double result = Math.log(10);
        System.out.println("Output: " + result);
        System.out.println();
    }


    static void log10Method() {
        System.out.println("11. Math.log10()");
        System.out.println("Description: Returns the logarithm base 10 of a number.");
        System.out.println("Sample Code:");
        System.out.println("    double result = Math.log10(1000);");
        System.out.println("    System.out.println(result);");

        double result = Math.log10(1000);
        System.out.println("Output: " + result);
        System.out.println();
    }


    static void expMethod() {
        System.out.println("12. Math.exp()");
        System.out.println("Description: Returns e raised to the power of a number (e^x).");
        System.out.println("Sample Code:");
        System.out.println("    double result = Math.exp(2);");
        System.out.println("    System.out.println(result);");

        double result = Math.exp(2);
        System.out.println("Output: " + result);
        System.out.println();
    }


    static void sinMethod() {
        System.out.println("13. Math.sin()");
        System.out.println("Description: Returns the sine of an angle (the angle must be in radians).");
        System.out.println("Sample Code:");
        System.out.println("    double result = Math.sin(Math.toRadians(30));");
        System.out.println("    System.out.printf(\"%.2f\", result);");

        double result = Math.sin(Math.toRadians(30));
        System.out.printf("Output: %.2f%n", result);
        System.out.println();
    }


    static void cosMethod() {
        System.out.println("14. Math.cos()");
        System.out.println("Description: Returns the cosine of an angle (the angle must be in radians).");
        System.out.println("Sample Code:");
        System.out.println("    double result = Math.cos(Math.toRadians(60));");
        System.out.println("    System.out.printf(\"%.2f\", result);");

        double result = Math.cos(Math.toRadians(60));
        System.out.printf("Output: %.2f%n", result);
        System.out.println();
    }


    static void tanMethod() {
        System.out.println("15. Math.tan()");
        System.out.println("Description: Returns the tangent of an angle (the angle must be in radians).");
        System.out.println("Sample Code:");
        System.out.println("    double result = Math.tan(Math.toRadians(45));");
        System.out.println("    System.out.printf(\"%.2f\", result);");

        double result = Math.tan(Math.toRadians(45));
        System.out.printf("Output: %.2f%n", result);
        System.out.println();
    }
}