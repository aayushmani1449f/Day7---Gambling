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
        System.out.println("=== UC2: Calculative Gambler Exits at ±50% Stake ===");
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
    }
}
