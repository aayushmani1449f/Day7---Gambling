package com.employeewage;

/**
 * UC5: Calculating Wages for a Month
 *      - Working Days per Month: 20
 *      - Wage per Hour         : Rs. 20
 *      - Full Day Hours        : 8
 *      - Part Day Hours        : 4
 */
public class EmployeeWageComputation {

    // Constants
    static final int WAGE_PER_HOUR      = 20;
    static final int FULL_DAY_HRS       = 8;
    static final int PART_DAY_HRS       = 4;
    static final int NUM_WORKING_DAYS   = 20;

    // Employee type flags
    static final int IS_ABSENT          = 0;
    static final int IS_PART_TIME       = 1;
    static final int IS_FULL_TIME       = 2;

    public static void main(String[] args) {
        System.out.println("Welcome to Employee Wage Computation Program on Master Branch");
        System.out.println("----------------------------------------------------------");

        int totalEmpHrs  = 0;
        int totalEmpWage = 0;

        // UC5: Loop for 20 working days
        for (int day = 1; day <= NUM_WORKING_DAYS; day++) {
            // UC1: Attendance check
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

            // UC2: Daily wage
            int dailyWage = empHrs * WAGE_PER_HOUR;
            totalEmpHrs  += empHrs;
            totalEmpWage += dailyWage;

            System.out.printf("Day #%2d | Status: %s | Hours: %d | Daily Wage: Rs. %d%n",
                    day, status, empHrs, dailyWage);
        }

        System.out.println("----------------------------------------------------------");
        System.out.println("Total Hours Worked  : " + totalEmpHrs);
        System.out.println("Total Monthly Wage  : Rs. " + totalEmpWage);
    }
}
