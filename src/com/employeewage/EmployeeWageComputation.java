package com.employeewage;

/**
 * UC7: Refactor the Code to write a Class Method to Compute Employee Wage
 *      - All constants moved to Class Variables (static final)
 *      - Logic encapsulated in a dedicated Class Method: computeEmpWage()
 *      - main() only calls computeEmpWage() — separation of concerns
 *
 * Business Rules:
 *      - Wage per Hour      : Rs. 20
 *      - Full Day Hours     : 8
 *      - Part Day Hours     : 4
 *      - Working Days/Month : 20
 *      - Max Hours/Month    : 100
 *      - Loop exits when EITHER max hours OR max days is reached
 */
public class EmployeeWageComputation {

    // ─── Class Variables ────────────────────────────────────────────────────
    static final int WAGE_PER_HOUR    = 20;
    static final int FULL_DAY_HRS     = 8;
    static final int PART_DAY_HRS     = 4;
    static final int NUM_WORKING_DAYS = 20;
    static final int MAX_HRS_IN_MONTH = 100;

    // Employee attendance type constants
    static final int IS_ABSENT        = 0;
    static final int IS_PART_TIME     = 1;
    static final int IS_FULL_TIME     = 2;

    // ─── Class Method ───────────────────────────────────────────────────────
    /**
     * Computes monthly employee wage.
     * Loops day by day until MAX_HRS_IN_MONTH or NUM_WORKING_DAYS is reached.
     * Uses Switch Case to determine daily hours based on random attendance.
     */
    public static void computeEmpWage() {
        System.out.println("------------------------------------------------------------");

        int totalEmpHrs  = 0;
        int totalEmpWage = 0;
        int day          = 0;

        while (totalEmpHrs < MAX_HRS_IN_MONTH && day < NUM_WORKING_DAYS) {
            day++;

            // UC1: Random attendance (0=Absent, 1=Part-time, 2=Full-time)
            int empCheck = (int) (Math.random() * 3);

            // UC4: Switch Case to resolve hours
            int empHrs = 0;
            String status;
            switch (empCheck) {
                case IS_FULL_TIME:
                    empHrs = FULL_DAY_HRS;
                    status = "Full-time";
                    break;
                case IS_PART_TIME:
                    empHrs = PART_DAY_HRS;
                    status = "Part-time";
                    break;
                default:
                    empHrs = 0;
                    status = "Absent   ";
            }

            int dailyWage = empHrs * WAGE_PER_HOUR;
            totalEmpHrs  += empHrs;
            totalEmpWage += dailyWage;

            System.out.printf("Day #%2d | Status: %s | Hours: %d | Daily Wage: Rs. %-5d | Cumulative Hours: %d%n",
                    day, status, empHrs, dailyWage, totalEmpHrs);
        }

        // Summary
        System.out.println("------------------------------------------------------------");
        System.out.println("Exit Condition        : " +
                (totalEmpHrs >= MAX_HRS_IN_MONTH
                        ? "Max hours (" + MAX_HRS_IN_MONTH + " hrs) reached"
                        : "Max days  (" + NUM_WORKING_DAYS + " days) reached"));
        System.out.println("Total Days Worked     : " + day);
        System.out.println("Total Hours Worked    : " + totalEmpHrs);
        System.out.println("Total Monthly Wage    : Rs. " + totalEmpWage);
        System.out.println("------------------------------------------------------------");
    }

    // ─── Entry Point ────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Wage Computation Program on Master Branch");
        computeEmpWage();
    }
}
