package com.gambling;

public class DayResult {
    private final int dayNumber;
    private final double netResult;
    private final boolean won;

    public DayResult(int dayNumber, double netResult) {
        this.dayNumber = dayNumber;
        this.netResult = netResult;
        this.won = netResult > 0;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public double getNetResult() {
        return netResult;
    }

    public boolean isWon() {
        return won;
    }
}
