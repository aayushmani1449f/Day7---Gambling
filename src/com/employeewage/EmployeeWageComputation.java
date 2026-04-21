package com.employeewage;

/**
 * UC2: Calculate Daily Employee Wage
 *      - Wage per Hour: Rs. 20
 *      - Full Day Hours: 8
 */
public class EmployeeWageComputation {

    // Constants
    static final int WAGE_PER_HOUR = 20;
    static final int FULL_DAY_HRS = 8;

    public static void main(String[] args) {
        System.out.println("Welcome to Employee Wage Computation Program on Master Branch");

        // UC1: Check Attendance using Random (0 = Absent, 1 = Present)
        int empCheck = (int) (Math.random() * 2);
        boolean isPresent = (empCheck == 1);
        System.out.println("Employee is " + (isPresent ? "Present" : "Absent"));

        // UC2: Calculate Daily Wage
        int dailyWage = 0;
        if (isPresent) {
            dailyWage = FULL_DAY_HRS * WAGE_PER_HOUR;
        }
        System.out.println("Daily Employee Wage: Rs. " + dailyWage);
    }
}
