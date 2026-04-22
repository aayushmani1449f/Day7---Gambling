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
public class EmpWageBuilder {

    static final int FULL_DAY_HRS     = 8;
    static final int PART_DAY_HRS     = 4;

    // Employee attendance type constants
    static final int IS_ABSENT        = 0;
    static final int IS_PART_TIME     = 1;
    static final int IS_FULL_TIME     = 2;

    // ─── Instance Variables ─────────────────────────────────────────────────
    private final String company;
    private final int empRatePerHour;
    private final int numOfWorkingDays;
    private final int maxHoursPerMonth;
    private int totalEmpWage;

    public EmpWageBuilder(String company, int empRatePerHour, int numOfWorkingDays, int maxHoursPerMonth) {
        this.company = company;
        this.empRatePerHour = empRatePerHour;
        this.numOfWorkingDays = numOfWorkingDays;
        this.maxHoursPerMonth = maxHoursPerMonth;
    }

    // ─── Instance Method ────────────────────────────────────────────────────
    /**
     * Computes monthly employee wage for this company.
     * Loops day by day until maxHoursPerMonth or numOfWorkingDays is reached.
     * Uses Switch Case to determine daily hours based on random attendance.
     */
    public void computeEmpWage() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Computing Wage for Company: " + company);
        System.out.println("------------------------------------------------------------");

        int totalEmpHrs  = 0;
        int day          = 0;

        while (totalEmpHrs < maxHoursPerMonth && day < numOfWorkingDays) {
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

            int dailyWage = empHrs * empRatePerHour;
            totalEmpHrs  += empHrs;
            totalEmpWage += dailyWage;

            System.out.printf("Day #%2d | Status: %s | Hours: %d | Daily Wage: Rs. %-5d | Cumulative Hours: %d%n",
                    day, status, empHrs, dailyWage, totalEmpHrs);
        }

        // Summary
        System.out.println("------------------------------------------------------------");
        System.out.println("Exit Condition        : " +
                (totalEmpHrs >= maxHoursPerMonth
                        ? "Max hours (" + maxHoursPerMonth + " hrs) reached"
                        : "Max days  (" + numOfWorkingDays + " days) reached"));
        System.out.println("Total Days Worked     : " + day);
        System.out.println("Total Hours Worked    : " + totalEmpHrs);
        System.out.println("Total Monthly Wage    : Rs. " + totalEmpWage);
        System.out.println("------------------------------------------------------------\n");
    }

    // ─── Entry Point ────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Wage Computation Program");
        EmpWageBuilder dmart = new EmpWageBuilder("DMart", 20, 2, 10);
        EmpWageBuilder reliance = new EmpWageBuilder("Reliance", 10, 4, 20);

        dmart.computeEmpWage();
        reliance.computeEmpWage();
    }
}
