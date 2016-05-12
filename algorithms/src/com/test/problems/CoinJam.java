package com.test.problems;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CoinJam {
    // https://code.google.com/codejam/contest/6254486/dashboard#s=p2

    private static final String INPUT_FILE = "/tmp/C-small-practice.in";
    private static final String OUTPUT_FILE = "/tmp/C-small-practice.out";

    private static Optional<Integer> findFactor(long number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    private static Optional<List<Integer>> getCoinJamFactors(long number, int desiredLength) {
        if (number % 2 == 0) {
            return Optional.empty();
        }
        String numberString = Long.toString(number, 2);
        if (numberString.length() != desiredLength) {
            return Optional.empty();
        }
        List<Integer> factors = new ArrayList<>();
        // System.out.println(numberString);
        for (int radix = 2; radix <= 10; radix++) {
            long numberInRadix = Long.parseLong(numberString, radix);
            Optional<Integer> factorOptional = findFactor(numberInRadix);
            if (!factorOptional.isPresent()) {
                // System.out.println("On radix " + radix + " is prime: " + numberInRadix);
                return Optional.empty();
            }
            factors.add(factorOptional.get());
        }
        return Optional.of(factors);
    }

    private static Map<Long, List<Integer>> findCoinJams(int length, int numberOfCases) {
        if (length < 2 || numberOfCases < 1) {
            return new HashMap<>();
        }
        Map<Long, List<Integer>> coinJams = new HashMap<>();
        long minNumber = (int) Math.pow(2, length - 1);
        long maxNumber = (int) Math.pow(2, length);
        for (long num = minNumber + 1; num < maxNumber; num += 2) {
            Optional<List<Integer>> coinJamFactors = getCoinJamFactors(num, length);
            if (coinJamFactors.isPresent()) {
                coinJams.put(num, coinJamFactors.get());
            }
            if (coinJams.size() == numberOfCases) {
                break;
            }
        }
        return coinJams;
    }

    private static void printAnswer(PrintStream printStream, Map<Long, List<Integer>> answer) {
        for (Map.Entry<Long, List<Integer>> answerEntry : answer.entrySet()) {
            printStream.print(Long.toString(answerEntry.getKey(), 2));
            for (int factor : answerEntry.getValue()) {
                printStream.print(" " + factor);
            }
            printStream.println();
        }
    }

    private static void testInput() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE)));
        PrintStream printStream = new PrintStream(new FileOutputStream(OUTPUT_FILE));
        int numberOfInput = Integer.parseInt(bufferedReader.readLine());
        List<String> inputStrings = new ArrayList<>();
        for (int i = 0; i < numberOfInput; i++) {
            inputStrings.add(bufferedReader.readLine());
        }
        int index = 1;
        for (String inputString : inputStrings) {
            String[] tokens = inputString.split(" ");
            if (tokens.length < 2) {
                throw new RuntimeException("Invalid input: " + inputString);
            }
            int length = Integer.parseInt(tokens[0]);
            int numberOfCases = Integer.parseInt(tokens[1]);
            Map<Long, List<Integer>> answer = findCoinJams(length, numberOfCases);
            printStream.println("Case #" + index + ":");
            printAnswer(printStream, answer);
            index++;
        }
        System.out.println("Done!");
    }

    private static void testSampleInput() {
        Map<Long, List<Integer>> answer = findCoinJams(6, 20);
        printAnswer(System.out, answer);
    }

    public static void main(String[] args) throws IOException {
        // testSampleInput();
        testInput();
    }
}
