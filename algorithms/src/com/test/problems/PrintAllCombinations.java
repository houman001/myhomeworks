package com.test.problems;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PrintAllCombinations {
    private static void printCombinations(Set<Integer> numbers) {
        printCombinations("", numbers);
    }

    private static void printCombinations(String prefix, Set<Integer> numbers) {
        if (numbers.isEmpty()) {
            System.out.println(prefix);
            return;
        }
        for (final int numberToPick : numbers) {
            printCombinations(prefix + " " + numberToPick, numbers.stream().filter(new Predicate<Integer>() {
                @Override
                public boolean test(Integer number) {
                    return numberToPick != number;
                }
            }).collect(Collectors.toSet()));
        }
    }

    public static void main(String[] args) {
        int length = 6;
        Set<Integer> numbers = new HashSet<>();
        for (int i = 1; i <= length; i++) {
            numbers.add(i);
        }
        printCombinations(numbers);
    }
}
