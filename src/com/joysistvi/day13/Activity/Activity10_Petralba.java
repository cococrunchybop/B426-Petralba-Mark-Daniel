package com.joysistvi.day13.Activity;



public class Activity10_Petralba{

    public static void main(String[] args) {
        System.out.println("##### DOCUMENTATION: STRING METHODS #####\n");

        containsDemo();
        endsWithDemo();
        startsWithDemo();
        indexOfDemo();
        trimDemo();
        replaceDemo();
        toCharArrayDemo();

        System.out.println("\n##### STRETCH GOAL #####\n");
        stretchGoal();
    }

    // ---------------------------------------------------------
    // 1. contains()
    // Checks if a string contains a sequence of characters.
    // Returns: boolean (true / false). Case-sensitive.
    // ---------------------------------------------------------
    static void containsDemo() {
        String sentence = "I love learning Java";

        System.out.println("=== contains() ===");
        System.out.println(sentence.contains("Java"));    // true
        System.out.println(sentence.contains("java"));    // false (case-sensitive)
        System.out.println(sentence.contains("Python"));  // false
    }

    // ---------------------------------------------------------
    // 2. endsWith()
    // Checks if a string ends with the given suffix.
    // Returns: boolean
    // ---------------------------------------------------------
    static void endsWithDemo() {
        String fileName = "report.pdf";

        System.out.println("\n=== endsWith() ===");
        System.out.println(fileName.endsWith(".pdf"));    // true
        System.out.println(fileName.endsWith(".docx"));   // false
    }

    // ---------------------------------------------------------
    // 3. startsWith()
    // Checks if a string begins with the given prefix.
    // Returns: boolean
    // ---------------------------------------------------------
    static void startsWithDemo() {
        String website = "https://www.example.com";

        System.out.println("\n=== startsWith() ===");
        System.out.println(website.startsWith("https"));  // true
        System.out.println(website.startsWith("ftp"));    // false
    }

    // ---------------------------------------------------------
    // 4. indexOf()
    // Returns the position (index) of the first match.
    // Returns: int. If not found, it returns -1.
    // ---------------------------------------------------------
    static void indexOfDemo() {
        String word = "Programming";

        System.out.println("\n=== indexOf() ===");
        System.out.println(word.indexOf('g'));            // 3  (first 'g')
        System.out.println(word.indexOf("gram"));         // 3
        System.out.println(word.indexOf('g', 4));         // 10 (search starts at index 4)
        System.out.println(word.indexOf('z'));            // -1 (not found)
    }

    // ---------------------------------------------------------
    // 5. trim()
    // Removes whitespace at the START and END of a string.
    // Returns: a new String (the original is not changed).
    // Useful for cleaning up user input.
    // ---------------------------------------------------------
    static void trimDemo() {
        String messy = "   Hello World   ";

        System.out.println("\n=== trim() ===");
        System.out.println("[" + messy + "]");            // [   Hello World   ]
        System.out.println("[" + messy.trim() + "]");     // [Hello World]
    }

    // ---------------------------------------------------------
    // 6. replace()
    // Replaces ALL occurrences of a character/text with another.
    // Returns: a new String (the original is not changed).
    // ---------------------------------------------------------
    static void replaceDemo() {
        String greeting = "Hello World";

        System.out.println("\n=== replace() ===");
        System.out.println(greeting.replace('o', '0'));          // Hell0 W0rld
        System.out.println(greeting.replace("World", "Java"));   // Hello Java
    }

    // ---------------------------------------------------------
    // 7. toCharArray()
    // Converts a string into an array of its characters.
    // Returns: char[]
    // ---------------------------------------------------------
    static void toCharArrayDemo() {
        String name = "Java";
        char[] letters = name.toCharArray();

        System.out.println("\n=== toCharArray() ===");
        for (char c : letters) {
            System.out.println(c);                        // J, a, v, a (one per line)
        }
        System.out.println("Length of array: " + letters.length);  // 4
    }

    // ---------------------------------------------------------
    // STRETCH GOAL
    // Using: String country = "Philippines";
    //  - Display the first and last character
    //  - Count how many times 'P' / 'p' appears
    //  - Count the total number of vowels (a, e, i, o, u)
    // ---------------------------------------------------------
    static void stretchGoal() {
        String country = "Philippines";

        // 1. First and last character
        char first = country.charAt(0);
        char last = country.charAt(country.length() - 1);

        System.out.println("First character: " + first);
        System.out.println("Last character: " + last);

        // 2. Count 'P' / 'p'   3. Count vowels
        int pCount = 0;
        int vowelCount = 0;

        for (char c : country.toCharArray()) {
            char lower = Character.toLowerCase(c); // makes the check case-insensitive

            if (lower == 'p') {
                pCount++;
            }

            if (lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u') {
                vowelCount++;
            }
        }

        System.out.println("Number of 'P/p': " + pCount);
        System.out.println("Number of vowels: " + vowelCount);
    }
}