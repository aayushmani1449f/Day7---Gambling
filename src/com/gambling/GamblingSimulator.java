package com.gambling;

import java.util.List;

public class GamblingSimulator {

    public static void main(String[] args) {
        Gambler gambler = new Gambler();

        System.out.println("=== UC1: Gambler Bets $1 Per Game ===");
        gambler.resetStake();
        System.out.println("Starting stake: $" + gambler.getStake());

        for (int i = 1; i <= 10; i++) {
            boolean won = gambler.playGame();
            System.out.println("Game " + i + ": " + (won ? "Won" : "Lost") + " | Stake: $" + gambler.getStake());
        }

        System.out.println();
        System.out.println("=== UC2: Calculative Gambler Exits at +-50% Stake ===");
        gambler.resetStake();
        System.out.println("Starting stake: $" + gambler.getStake());
        System.out.println("High limit: $" + gambler.getHighLimit() + " | Low limit: $" + gambler.getLowLimit());

        int gameCount = 0;
        while (!gambler.hasReachedLimit()) {
            gambler.playGame();
            gameCount++;
        }

        double finalStake = gambler.getStake();
        String reason = finalStake >= gambler.getHighLimit()
                ? "Won 50% of stake - Resigned for the day!"
                : "Lost 50% of stake - Resigned for the day!";

        System.out.println("Games played: " + gameCount);
        System.out.println("Final stake: $" + finalStake);
        System.out.println("Reason: " + reason);

        System.out.println();
        System.out.println("=== UC3: 20-Day Total Won/Lost ===");
        List<DayResult> monthResults = gambler.playMonth();

        double totalNet = 0;
        for (DayResult day : monthResults) {
            totalNet += day.getNetResult();
        }

        System.out.println("Days played: " + monthResults.size());
        if (totalNet > 0) {
            System.out.println("Total WON over 20 days: $" + totalNet);
        } else if (totalNet < 0) {
            System.out.println("Total LOST over 20 days: $" + Math.abs(totalNet));
        } else {
            System.out.println("Broke even over 20 days.");
        }

        System.out.println();
        System.out.println("=== UC4: Monthly Report - Days Won and Lost ===");
        System.out.printf("%-6s %-10s %-10s%n", "Day", "Result", "Amount");
        System.out.println("---------------------------");

        int daysWon = 0;
        int daysLost = 0;
        double totalWon = 0;
        double totalLost = 0;

        for (DayResult day : monthResults) {
            String result = day.isWon() ? "WON" : "LOST";
            double amount = Math.abs(day.getNetResult());
            System.out.printf("%-6d %-10s $%-9.1f%n", day.getDayNumber(), result, amount);
            if (day.isWon()) {
                daysWon++;
                totalWon += day.getNetResult();
            } else {
                daysLost++;
                totalLost += Math.abs(day.getNetResult());
            }
        }

        System.out.println("---------------------------");
        System.out.println("Days Won : " + daysWon + " | Total Won  : $" + totalWon);
        System.out.println("Days Lost: " + daysLost + " | Total Lost : $" + totalLost);

        System.out.println();
        System.out.println("=== UC5: Luckiest and Unluckiest Day ===");

        DayResult luckiestDay = monthResults.get(0);
        DayResult unluckiestDay = monthResults.get(0);

        for (DayResult day : monthResults) {
            if (day.getNetResult() > luckiestDay.getNetResult()) {
                luckiestDay = day;
            }
            if (day.getNetResult() < unluckiestDay.getNetResult()) {
                unluckiestDay = day;
            }
        }

        System.out.println("Luckiest Day  : Day " + luckiestDay.getDayNumber()
                + " | Won $" + Math.abs(luckiestDay.getNetResult()));
        System.out.println("Unluckiest Day: Day " + unluckiestDay.getDayNumber()
                + " | Lost $" + Math.abs(unluckiestDay.getNetResult()));

        System.out.println();
        System.out.println("=== UC6: Continue or Stop Gambling ===");

        boolean continueNext = gambler.shouldContinueGambling(totalNet);
        if (continueNext) {
            System.out.println("Result: You WON $" + totalNet + " this month.");
            System.out.println("Decision: Continue gambling next month!");
        } else if (totalNet < 0) {
            System.out.println("Result: You LOST $" + Math.abs(totalNet) + " this month.");
            System.out.println("Decision: Stop gambling.");
        } else {
            System.out.println("Result: You broke even this month.");
            System.out.println("Decision: Stop gambling.");
        }

        System.out.println();
        System.out.println("=== UC7: Multi-Month Gambling Simulation ===");

        int monthNumber = 1;
        double grandTotal = 0;
        boolean continueGambling = true;

        while (continueGambling) {
            System.out.println("-- Month " + monthNumber + " --");
            List<DayResult> monthSim = gambler.playMonth();

            double monthNet = 0;
            for (DayResult day : monthSim) {
                monthNet += day.getNetResult();
            }

            grandTotal += monthNet;

            if (monthNet > 0) {
                System.out.println("Month " + monthNumber + ": WON $" + monthNet
                        + " | Cumulative: $" + grandTotal);
                System.out.println("Decision: Continue next month.");
                monthNumber++;
            } else {
                System.out.println("Month " + monthNumber + ": LOST $" + Math.abs(monthNet)
                        + " | Cumulative: $" + grandTotal);
                System.out.println("Decision: Stop gambling.");
                continueGambling = false;
            }

            if (monthNumber > 12) {
                System.out.println("Played 12 months. Retiring for the year.");
                continueGambling = false;
            }
        }

        System.out.println();
        System.out.println("Grand Total after all months: $" + grandTotal);
        if (grandTotal > 0) {
            System.out.println("Overall: Profitable gambler!");
        } else {
            System.out.println("Overall: The house wins.");
        }
    }
}
