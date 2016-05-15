package com.test.problems;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CoinJamBigInteger {
    // https://code.google.com/codejam/contest/6254486/dashboard#s=p2
    // This doesn't use any smarts, just uses big integers instead of longs.
    // I thought it would be fun to see how slow big integers are ;-)

    private static final String INPUT_FILE = "/tmp/C-large-practice.in";
    private static final String OUTPUT_FILE = "/tmp/C-large-practice.out";
    private static final BigInteger TWO = new BigInteger("2");

    private static Optional<BigInteger> findFactor(BigInteger number) {
        // number.sqrt() would be better
        for (BigInteger i = TWO; i.compareTo(number.divide(TWO)) <= 0; i = i.add(BigInteger.ONE)) {
            if (number.remainder(i).equals(BigInteger.ZERO)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    private static Optional<List<BigInteger>> getCoinJamFactors(BigInteger number, int desiredLength) {
        // System.out.println("Finding factors for: " + number);
        if (number.remainder(TWO).equals(BigInteger.ZERO)) {
            return Optional.empty();
        }
        String numberString = number.toString(2);
        if (numberString.length() != desiredLength) {
            // System.out.println("Desired length: " + desiredLength + ", number length: " + numberString.length());
            return Optional.empty();
        }
        List<BigInteger> factors = new ArrayList<>();
        // System.out.println("Binary representation: " + numberString);
        for (int radix = 2; radix <= 10; radix++) {
            // System.out.println("Radix: " + radix);
            BigInteger numberInRadix = new BigInteger(numberString, radix);
            Optional<BigInteger> factorOptional = findFactor(numberInRadix);
            if (!factorOptional.isPresent()) {
                // System.out.println("On radix " + radix + " is prime: " + numberInRadix);
                return Optional.empty();
            }
            factors.add(factorOptional.get());
        }
        return Optional.of(factors);
    }

    private static Map<BigInteger, List<BigInteger>> findCoinJams(int length, int numberOfCases) {
        if (length < 2 || numberOfCases < 1) {
            return new HashMap<>();
        }
        Map<BigInteger, List<BigInteger>> coinJams = new HashMap<>();
        // System.out.println("Length: " + length);
        BigInteger minNumber = TWO.pow(length - 1).add(BigInteger.ONE);
        BigInteger maxNumber = TWO.pow(length).subtract(BigInteger.ONE);
        // System.out.println("Min: " + minNumber + ", Max: " + maxNumber);
        for (BigInteger num = minNumber; num.compareTo(maxNumber) < 0; num = num.add(TWO)) {
            Optional<List<BigInteger>> coinJamFactors = getCoinJamFactors(num, length);
            if (coinJamFactors.isPresent()) {
                System.out.println("Found one: " + num.toString(2));
                coinJams.put(num, coinJamFactors.get());
            }
            if (coinJams.size() == numberOfCases) {
                break;
            }
        }
        return coinJams;
    }

    private static void printAnswer(PrintStream printStream, Map<BigInteger, List<BigInteger>> answer) {
        for (Map.Entry<BigInteger, List<BigInteger>> answerEntry : answer.entrySet()) {
            printStream.print(answerEntry.getKey().toString(2));
            for (BigInteger factor : answerEntry.getValue()) {
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
            Map<BigInteger, List<BigInteger>> answer = findCoinJams(length, numberOfCases);
            printStream.println("Case #" + index + ":");
            printAnswer(printStream, answer);
            index++;
        }
        System.out.println("Done!");
    }

    private static void testSampleInput() {
        Map<BigInteger, List<BigInteger>> answer = findCoinJams(10, 20);
        printAnswer(System.out, answer);
    }

    public static void main(String[] args) throws IOException {
        testSampleInput();
//        testInput();
    }
}
