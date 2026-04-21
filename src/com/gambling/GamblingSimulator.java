package com.gambling;

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
    }
}
