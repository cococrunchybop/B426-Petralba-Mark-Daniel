package com.joysistvi.day27.program;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Create an arraylist to store student objects
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee(101, "JM", "Malabon", "09451548948"));
        employeeList.add(new Employee(102, "Ced", "Rizal", "09456456465"));
        employeeList.add(new Employee(103, "Aljane", "Romblon", "0946545645"));

        System.out.println("List of Employees: ");
        employeeList.forEach(employee -> System.out.println(employee.getName() ));
        //employeeList.forEach(System.out::println);

        // Activity Time!

    }
}
// [] [] [] []

/*      []    []
    []              []
       []     []
          []
 */
/*
    ArrayList: get (Default Choice)
    LinkedList: add, delete
 */
