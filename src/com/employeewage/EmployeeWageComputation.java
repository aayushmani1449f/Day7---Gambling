package com.employeewage;

/**
 * UC1: Check Employee is Present or Absent using Random
 */
public class EmployeeWageComputation {

    public static void main(String[] args) {
        System.out.println("Welcome to Employee Wage Computation Program on Master Branch");

        // UC1: Check Attendance using Random (0 = Absent, 1 = Present)
        int empCheck = (int) (Math.random() * 2);
        boolean isPresent = (empCheck == 1);

        System.out.println("Employee is " + (isPresent ? "Present" : "Absent"));
    }
}
