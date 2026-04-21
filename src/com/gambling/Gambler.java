package com.gambling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gambler {
    private double stake;
    private final double startingStake;
    private final double betAmount;
    private final double highLimit;
    private final double lowLimit;
    private final Random random;

    public Gambler() {
        this.startingStake = 100.0;
        this.stake = startingStake;
        this.betAmount = 1.0;
        this.highLimit = 150.0;
        this.lowLimit = 50.0;
        this.random = new Random();
    }

    public void resetStake() {
        this.stake = startingStake;
    }

    public double getStake() {
        return stake;
    }

    public double getStartingStake() {
        return startingStake;
    }

    public double getHighLimit() {
        return highLimit;
    }

    public double getLowLimit() {
        return lowLimit;
    }

    public boolean playGame() {
        boolean win = random.nextBoolean();
        if (win) {
            stake += betAmount;
        } else {
            stake -= betAmount;
        }
        return win;
    }

    public boolean hasReachedLimit() {
        return stake >= highLimit || stake <= lowLimit;
    }

    public double playDay() {
        resetStake();
        while (!hasReachedLimit()) {
            playGame();
        }
        return stake - startingStake;
    }

    public List<DayResult> playMonth() {
        List<DayResult> results = new ArrayList<>();
        for (int day = 1; day <= 20; day++) {
            double netResult = playDay();
            results.add(new DayResult(day, netResult));
        }
        return results;
    }

    public boolean shouldContinueGambling(double totalNet) {
        return totalNet > 0;
    }
}
