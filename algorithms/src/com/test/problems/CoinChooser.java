package com.test.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CoinChooser {
    private final Set<Integer> denominations;
    private final int sum;
    private final Map<Integer, Result> interimResults;

    private CoinChooser(Set<Integer> denominations, int sum) {
        this.denominations = denominations;
        this.sum = sum;
        interimResults = new HashMap<>(denominations.size());
    }

    private Map<Integer, Integer> solve() {
        interimResults.put(0, new Result(true, 0, 0, 0));
        for (int i = 1; i <= sum; i++) {
            // System.out.println("Solving for " + i);
            int numberOfCoins = Integer.MAX_VALUE;
            int previousSum = 0;
            int coinAdded = 0;
            for (int denomination : denominations) {
                int remainder = i - denomination;
                if (remainder < 0) {
                    continue;
                }
                if (!interimResults.containsKey(remainder)) {
                    throw new RuntimeException("Are we dynamic or what?");
                }
                Result remainderResult = interimResults.get(remainder);
                if (!remainderResult.hasSolution) {
                    continue;
                }
                if (remainderResult.numberOfCoins + 1 < numberOfCoins) {
                    numberOfCoins = remainderResult.numberOfCoins + 1;
                    previousSum = remainder;
                    coinAdded = denomination;
                }
            }
            Result result = new Result(false, 0, 0, 0);
            if (numberOfCoins < Integer.MAX_VALUE) {
                result = new Result(true, numberOfCoins, previousSum, coinAdded);
                // System.out.println("Result: " + result);
            }
            interimResults.put(i, result);

        }
        Map<Integer, Integer> coins = new HashMap<>();
        Result result = interimResults.get(sum);
        while (result.hasSolution && result.numberOfCoins > 0) {
            int coinAdded = result.coinAdded;
            int currentCount = coins.containsKey(coinAdded) ? coins.get(coinAdded) : 0;
            coins.put(coinAdded, currentCount + 1);
            result = interimResults.get(result.previousSum);
        }
        return coins;
    }

    private static class Result {
        boolean hasSolution;
        int numberOfCoins;
        int previousSum;
        int coinAdded;

        public Result(boolean hasSolution, int numberOfCoins, int previousSum, int coinAdded) {
            this.hasSolution = hasSolution;
            this.numberOfCoins = numberOfCoins;
            this.previousSum = previousSum;
            this.coinAdded = coinAdded;
        }

        @Override
        public String toString() {
            return "# " + numberOfCoins + ": coins(" + previousSum + ") + " + coinAdded;
        }
    }

    public static void main(String[] args) {
        Set<Integer> denominations = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            denominations.add(new Random().nextInt(9) + 1);
        }
        System.out.println("Denominations: " + denominations);
        int sum = new Random().nextInt(30) + 10;
        System.out.println("Sum: " + sum);
        CoinChooser coinChooser = new CoinChooser(denominations, sum);
        Map<Integer, Integer> coins = coinChooser.solve();
        for (Map.Entry<Integer, Integer> entry : coins.entrySet()) {
            System.out.println("" + entry.getValue() + " x " + entry.getKey());
        }
    }
}
