package com.employeewage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class EmpWageBuilder implements IComputeEmpWage {

    static final int FULL_DAY_HRS     = 8;
    static final int PART_DAY_HRS     = 4;

    // Employee attendance type constants
    static final int IS_ABSENT        = 0;
    static final int IS_PART_TIME     = 1;
    static final int IS_FULL_TIME     = 2;

    // ─── Instance Variables ─────────────────────────────────────────────────
    private List<CompanyEmpWage> companyEmpWageList;
    private Map<String, CompanyEmpWage> companyToEmpWageMap;

    public EmpWageBuilder() {
        companyEmpWageList = new ArrayList<>();
        companyToEmpWageMap = new HashMap<>();
    }

    @Override
    public void addCompanyEmpWage(String company, int empRatePerHour, int numOfWorkingDays, int maxHoursPerMonth) {
        CompanyEmpWage companyEmpWage = new CompanyEmpWage(company, empRatePerHour, numOfWorkingDays, maxHoursPerMonth);
        companyEmpWageList.add(companyEmpWage);
        companyToEmpWageMap.put(company, companyEmpWage);
    }

    // ─── Instance Methods ───────────────────────────────────────────────────
    /**
     * Loops through all companies added and computes wage for each.
     */
    @Override
    public void computeEmpWage() {
        for (CompanyEmpWage companyEmpWage : companyEmpWageList) {
            companyEmpWage.setTotalEmpWage(this.computeEmpWage(companyEmpWage));
            System.out.println(companyEmpWage);
        }
    }

    @Override
    public int getTotalWage(String company) {
        return companyToEmpWageMap.get(company).totalEmpWage;
    }
    /**
     * Computes monthly employee wage for a specific company.
     * Uses Switch Case to determine daily hours based on random attendance.
     */
    private int computeEmpWage(CompanyEmpWage companyEmpWage) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Computing Wage for Company: " + companyEmpWage.company);
        System.out.println("------------------------------------------------------------");

        int totalEmpHrs  = 0;
        int day          = 0;

        while (totalEmpHrs < companyEmpWage.maxHoursPerMonth && day < companyEmpWage.numOfWorkingDays) {
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

            int dailyWage = empHrs * companyEmpWage.empRatePerHour;
            companyEmpWage.dailyWages.add(dailyWage);
            totalEmpHrs  += empHrs;
            int totalEmpWage = totalEmpHrs * companyEmpWage.empRatePerHour;

            System.out.printf("Day #%2d | Status: %s | Hours: %d | Daily Wage: Rs. %-5d | Cumulative Hours: %d%n",
                    day, status, empHrs, dailyWage, totalEmpHrs);
        }

        int totalWage = totalEmpHrs * companyEmpWage.empRatePerHour;

        // Summary
        System.out.println("------------------------------------------------------------");
        System.out.println("Exit Condition        : " +
                (totalEmpHrs >= companyEmpWage.maxHoursPerMonth
                        ? "Max hours (" + companyEmpWage.maxHoursPerMonth + " hrs) reached"
                        : "Max days  (" + companyEmpWage.numOfWorkingDays + " days) reached"));
        System.out.println("Total Days Worked     : " + day);
        System.out.println("Total Hours Worked    : " + totalEmpHrs);
        System.out.println("Total Monthly Wage    : Rs. " + totalWage);
        System.out.println("------------------------------------------------------------\n");
        return totalWage;
    }

    // ─── Entry Point ────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Wage Computation Program");
        EmpWageBuilder empWageBuilder = new EmpWageBuilder();
        empWageBuilder.addCompanyEmpWage("DMart", 20, 2, 10);
        empWageBuilder.addCompanyEmpWage("Reliance", 10, 4, 20);

        empWageBuilder.computeEmpWage();

        System.out.println("Total Wage for Reliance: Rs. " + empWageBuilder.getTotalWage("Reliance"));
        System.out.println("Total Wage for DMart: Rs. " + empWageBuilder.getTotalWage("DMart"));
    }
}
