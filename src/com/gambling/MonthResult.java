package com.gambling;

public class MonthResult {
    private final int monthNumber;
    private final double netResult;
    private final boolean won;

    public MonthResult(int monthNumber, double netResult) {
        this.monthNumber = monthNumber;
        this.netResult = netResult;
        this.won = netResult > 0;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public double getNetResult() {
        return netResult;
    }

    public boolean isWon() {
        return won;
    }
}
