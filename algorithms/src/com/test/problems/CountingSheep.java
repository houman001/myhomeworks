package com.test.problems;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CountingSheep {
    // https://code.google.com/codejam/contest/6254486/dashboard#s=p0

    private static final String INPUT_FILE = "/tmp/A-large-practice.in";
    private static final String OUTPUT_FILE = "/tmp/A-large-practice.out";
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

    private static void testInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE)));
        PrintStream bw = new PrintStream(new FileOutputStream(OUTPUT_FILE));
        int numberOfCases = Integer.parseInt(br.readLine());
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < numberOfCases; i++) {
            numbers.add(Integer.parseInt(br.readLine()));
        }
        int index = 1;
        for (int number : numbers) {
            Optional<Integer> answer = whenWillSheFallAsleep(number);
            String answerString = answer.isPresent() ? answer.get().toString() : "INSOMNIA";
            bw.println("Case #" + index + ": " + answerString);
            index++;
        }
        System.out.println("Done!");
    }

    public static void main(String[] args) throws IOException {
        testInput();
    }
}
