package com.employeewage;

/**
 * UC6: Calculate Wages till a condition of total working hours OR days is reached
 *      - Max Hours per Month  : 100
 *      - Max Working Days     : 20
 *      - Loop exits when EITHER condition is met (whichever comes first)
 */
public class EmployeeWageComputation {

    // Constants
    static final int WAGE_PER_HOUR    = 20;
    static final int FULL_DAY_HRS     = 8;
    static final int PART_DAY_HRS     = 4;
    static final int NUM_WORKING_DAYS = 20;
    static final int MAX_HRS_IN_MONTH = 100;

    // Employee type flags
    static final int IS_ABSENT        = 0;
    static final int IS_PART_TIME     = 1;
    static final int IS_FULL_TIME     = 2;

    public static void main(String[] args) {
        System.out.println("Welcome to Employee Wage Computation Program on Master Branch");
        System.out.println("------------------------------------------------------------");

        int totalEmpHrs  = 0;
        int totalEmpWage = 0;
        int day          = 0;

        // UC6: Loop until MAX_HRS_IN_MONTH reached OR NUM_WORKING_DAYS exhausted
        while (totalEmpHrs < MAX_HRS_IN_MONTH && day < NUM_WORKING_DAYS) {
            day++;

            // UC1: Attendance check (0=Absent, 1=Part-time, 2=Full-time)
            int empCheck = (int) (Math.random() * 3);

            // UC4: Determine hours via Switch Case
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

            // UC2: Accumulate daily wage
            int dailyWage = empHrs * WAGE_PER_HOUR;
            totalEmpHrs  += empHrs;
            totalEmpWage += dailyWage;

            System.out.printf("Day #%2d | Status: %s | Hours: %d | Daily Wage: Rs. %-5d | Total Hours So Far: %d%n",
                    day, status, empHrs, dailyWage, totalEmpHrs);
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Loop Exit Condition   : " +
                (totalEmpHrs >= MAX_HRS_IN_MONTH ? "Max hours (" + MAX_HRS_IN_MONTH + ") reached"
                        : "Max days (" + NUM_WORKING_DAYS + ") reached"));
        System.out.println("Total Days Worked     : " + day);
        System.out.println("Total Hours Worked    : " + totalEmpHrs);
        System.out.println("Total Monthly Wage    : Rs. " + totalEmpWage);
    }
}
