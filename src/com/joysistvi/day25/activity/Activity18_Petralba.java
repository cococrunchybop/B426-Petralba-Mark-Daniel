package com.joysistvi.day25.activity;

import java.util.ArrayList;
import java.util.Scanner;

public class Activity18_Petralba {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> tasks = new ArrayList<>();


    public static int getInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. Please enter a number.");
            }
        }
    }


    public static int showMenu() {
        System.out.println("--- Todo Manager Menu ---");
        System.out.println("1. Add a task");
        System.out.println("2. View all tasks");
        System.out.println("3. Update a task");
        System.out.println("4. Remove a task");
        System.out.println("5. Clear all tasks");
        System.out.println("0. Exit");
        return getInput("Enter your choice: ");
    }


    public static void addTask() {
        System.out.print("Enter the task: ");
        String task = scanner.nextLine().trim();
        tasks.add(task);
        System.out.println("Task added.");
    }


    public static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }


    public static void updateTask() {
        int number = getInput("Enter the task number to update: ");

        if (number >= 1 && number <= tasks.size()) {
            System.out.print("Enter the new task: ");
            String newTask = scanner.nextLine().trim();
            tasks.set(number - 1, newTask);
            System.out.println("Task updated.");
        } else {
            System.out.println("Invalid task number.");
        }
    }


    public static void removeTask() {
        int number = getInput("Enter the task number to remove: ");

        if (number >= 1 && number <= tasks.size()) {
            tasks.remove(number - 1);
            System.out.println("Task removed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }


    public static void clearTasks() {
        tasks.clear();
        System.out.println("All tasks cleared.");
    }

    public static void main(String[] args) {
        int choice;

        do {
            choice = showMenu();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    updateTask();
                    break;
                case 4:
                    removeTask();
                    break;
                case 5:
                    clearTasks();
                    break;
                case 0:
                    System.out.println("Exiting TodoManager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        } while (choice != 0);
    }
}