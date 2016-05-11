package com.test.problems;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RevengeOfThePancakes {
    // https://code.google.com/codejam/contest/6254486/dashboard#s=p1

    private static final String INPUT_FILE = "/tmp/B-large-practice.in";
    private static final String OUTPUT_FILE = "/tmp/B-large-practice.out";

    private static void swap(char[] array, int i1, int i2) {
        char temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    private static String flipPancakes(String pancakes, int flipPosition) {
        // System.out.println("Flipping at " + flipPosition);
        char[] array = pancakes.toCharArray();
        for (int i = 0; i < flipPosition / 2; i++) {
            swap(array, i, flipPosition - i - 1);
        }
        for (int i = 0; i < flipPosition; i++) {
            array[i] = array[i] == '+' ? '-' : '+';
        }
        String newPancakes = new String(array);
        // System.out.println(newPancakes);
        return newPancakes;
    }

    private static int findFlipPosition(String pancakes, int topPosition) {
        if (topPosition >= pancakes.length()) {
            throw new RuntimeException("Invalid position " + topPosition + " for pancakes " + pancakes);
        }
        char topPancake = pancakes.charAt(0);
        for (int i = topPosition; i < pancakes.length(); i++) {
            if (pancakes.charAt(i) != topPancake) {
                return i;
            }
        }
        return pancakes.length();
    }

    private static int countFlips(String pancakes) {
        if (pancakes.length() < 1) {
            return 0;
        }
        // System.out.println(pancakes);
        int numberOfFlips = 0;
        int startIndex = 0;
        while (true) {
            int flipPosition = findFlipPosition(pancakes, startIndex);
            if (flipPosition >= pancakes.length()) {
                break;
            }
            pancakes = flipPancakes(pancakes, flipPosition);
            numberOfFlips++;
            startIndex = flipPosition;
        }
        if (pancakes.charAt(0) == '-') {
            flipPancakes(pancakes, pancakes.length());
            numberOfFlips++;
        }
        return numberOfFlips;
    }

    private static void testInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE)));
        PrintStream bw = new PrintStream(new FileOutputStream(OUTPUT_FILE));
        int numberOfCases = Integer.parseInt(br.readLine());
        List<String> inputStrings = new ArrayList<>();
        for (int i = 0; i < numberOfCases; i++) {
            inputStrings.add(br.readLine());
        }
        int index = 1;
        for (String pancakes : inputStrings) {
            int answer = countFlips(pancakes);
            bw.println("Case #" + index + ": " + answer);
            index++;
        }
        System.out.println("Done!");
    }

    private static void testRandomString() {
        int TEST_ARRAY_LENGTH = 100;
        char[] array = new char[TEST_ARRAY_LENGTH];
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            array[i] = new Random().nextInt(2) == 1 ? '+' : '-';
        }
        String pancakes = new String(array);
        int count = countFlips(pancakes);
        System.out.println("Flips: " + count);
    }

    public static void main(String[] args) throws IOException {
        // testRandomString();
        testInput();
    }
}
