package com.joysistvi.day12.activity;
import java.util.Scanner;

public class Activity9_Petralba {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("         Average Calculator");
        System.out.println("===================================");

        System.out.print("Enter number of students: ");
        int numStudents = s.nextInt();
        System.out.print("Enter number of subjects: ");
        int numSubjects = s.nextInt();
        s.nextLine();

        String[] subjects = new String[numSubjects];
        String[] names = new String[numStudents];
        int[][] grades = new int[numStudents][numSubjects];

        System.out.println();
        System.out.println("Enter subject names:");
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            subjects[i] = s.nextLine();
        }

        for (int i = 0; i < numStudents; i++) {
            System.out.println();
            System.out.print("Name of Student " + (i + 1) + ": ");
            names[i] = s.nextLine();

            System.out.println("Enter grades for each subject:");
            for (int j = 0; j < numSubjects; j++) {
                System.out.print(subjects[j] + ": ");
                grades[i][j] = s.nextInt();
            }
            s.nextLine();
        }


        System.out.println();
        System.out.println();
        System.out.println("===================================");
        System.out.println("         Grades Report");
        System.out.println("===================================");

        System.out.printf("%-20s", "Student");
        for (int j = 0; j < numSubjects; j++) {
            System.out.printf("%-10s", subjects[j]);
        }
        System.out.println("Average");
        System.out.println("------------------------------------------------------------------");

        for (int i = 0; i < numStudents; i++) {
            System.out.printf("%-20s", names[i]);

            double sum = 0;
            for (int j = 0; j < numSubjects; j++) {
                System.out.printf("%-10d", grades[i][j]);
                sum += grades[i][j];
            }

            double average = sum / numSubjects;
            System.out.printf("%.2f%n", average);
        }

        s.close();
    }
}