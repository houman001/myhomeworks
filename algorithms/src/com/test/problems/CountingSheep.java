package com.test.problems;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class CountingSheep {
    // https://code.google.com/codejam/contest/6254486/dashboard#s=p0

    private static final Collection<Integer> ALL_DIGITS = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    private static void addNumbersSeen(Set<Integer> numberSet, int number) {
        while (number > 0) {
            numberSet.add(number % 10);
            number /= 10;
        }
    }

    private static Optional<Integer> whenWillSheFallAsleep(int number) {
        if (number < 1) {
            return Optional.empty();
        }
        Set<Integer> numbersSeen = new HashSet<>();
        int currentNumber = number;
        while (currentNumber > 0) {
            addNumbersSeen(numbersSeen, currentNumber);
            if (numbersSeen.containsAll(ALL_DIGITS)) {
                return Optional.of(currentNumber);
            }
            currentNumber += number;
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        int TEST_ARRAY_LENGTH = 1000;
        int MIN_RANDOM_NUMBER = 0;
        int MAX_RANDOM_NUMBER = 999;
        int[] array = new int[TEST_ARRAY_LENGTH];
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            array[i] = new Random().nextInt(MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER + 1) + MIN_RANDOM_NUMBER;
        }
        for (int number : array) {
            Optional<Integer> answer = whenWillSheFallAsleep(number);
            if (!answer.isPresent()) {
                System.err.println("" + number + ": INSOMNIA");
            }
            // System.out.println("" + number + ": " + (answer.isPresent() ? answer.get() : "INSOMNIA"));
        }
    }
}
