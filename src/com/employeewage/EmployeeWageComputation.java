package com.employeewage;

/**
 * UC4: Solving using Switch Case Statement
 *      - 0 → Absent       → 0 hrs
 *      - 1 → Part-time    → 4 hrs
 *      - 2 → Full-time    → 8 hrs
 */
public class EmployeeWageComputation {

    // Constants
    static final int WAGE_PER_HOUR = 20;
    static final int FULL_DAY_HRS  = 8;
    static final int PART_DAY_HRS  = 4;

    // Employee type flags
    static final int IS_ABSENT     = 0;
    static final int IS_PART_TIME  = 1;
    static final int IS_FULL_TIME  = 2;

    public static void main(String[] args) {
        System.out.println("Welcome to Employee Wage Computation Program on Master Branch");

        // UC1: Check Attendance using Random (0 = Absent, 1 = Part-time, 2 = Full-time)
        int empCheck = (int) (Math.random() * 3);

        // UC4: Determine hours using Switch Case
        int empHrs = 0;
        String status;
        switch (empCheck) {
            case IS_FULL_TIME:
                empHrs = FULL_DAY_HRS;
                status = "Full-time (Present)";
                break;
            case IS_PART_TIME:
                empHrs = PART_DAY_HRS;
                status = "Part-time (Present)";
                break;
            default:
                empHrs = 0;
                status = "Absent";
        }
        System.out.println("Employee Status       : " + status);

        // UC2: Calculate Daily Wage
        int dailyWage = empHrs * WAGE_PER_HOUR;
        System.out.println("Employee Hours Worked : " + empHrs);
        System.out.println("Daily Employee Wage   : Rs. " + dailyWage);
    }
}
