package com.employeewage;

/**
 * UC3: Add Part-time Employee and Wage
 *      - Full Day Hours: 8  → Full-time wage = 8  × Rs.20 = Rs.160
 *      - Part Day Hours: 4  → Part-time wage = 4  × Rs.20 = Rs.80
 *
 *      Random check:
 *        0 → Absent
 *        1 → Part-time
 *        2 → Full-time
 */
public class EmployeeWageComputation {

    // Constants
    static final int WAGE_PER_HOUR    = 20;
    static final int FULL_DAY_HRS     = 8;
    static final int PART_DAY_HRS     = 4;

    // Employee type flags
    static final int IS_ABSENT        = 0;
    static final int IS_PART_TIME     = 1;
    static final int IS_FULL_TIME     = 2;

    public static void main(String[] args) {
        System.out.println("Welcome to Employee Wage Computation Program on Master Branch");

        // UC1: Check Attendance using Random (0 = Absent, 1 = Part-time, 2 = Full-time)
        int empCheck = (int) (Math.random() * 3);

        // UC3: Determine hours based on attendance type
        int empHrs = 0;
        String status;
        if (empCheck == IS_FULL_TIME) {
            empHrs = FULL_DAY_HRS;
            status = "Full-time (Present)";
        } else if (empCheck == IS_PART_TIME) {
            empHrs = PART_DAY_HRS;
            status = "Part-time (Present)";
        } else {
            empHrs = 0;
            status = "Absent";
        }
        System.out.println("Employee Status: " + status);

        // UC2: Calculate Daily Wage
        int dailyWage = empHrs * WAGE_PER_HOUR;
        System.out.println("Employee Hours Worked: " + empHrs);
        System.out.println("Daily Employee Wage  : Rs. " + dailyWage);
    }
}
