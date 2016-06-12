package com.test.problems;

import java.util.Random;

public class RevertBitSequence {
    private static int swap(int number, int pos1, int pos2) {
        // System.out.println("Checking " + pos1 + " and " + pos2);
        if (pos1 < 0 || pos1 > Integer.SIZE - 1) {
            System.err.println("Invalid index: " + pos1);
        }
        if (pos2 < 0 || pos2 > Integer.SIZE - 1) {
            System.err.println("Invalid index: " + pos2);
        }
        int intPos1 = (1 << pos1);
        int intPos2 = (1 << pos2);
        int numberPos1 = intPos1 & number;
        int numberPos2 = intPos2 & number;
        // System.out.println("number at " + pos1 + ": " + convertToString(numberPos1));
        // System.out.println("number at " + pos2 + ": " + convertToString(numberPos2));
        if (numberPos1 == numberPos2) {
            return number;
        }
        // System.out.println("Swapping " + pos1 + " with " + pos2);
        // Clear and fill bits at pos1 and pos2
        int newNumber = (number ^ intPos1) ^ intPos2;
        if (numberPos1 != 0) {
            newNumber |= intPos2;
        }
        if (numberPos2 != 0) {
            newNumber |= intPos1;
        }
        // System.out.println("Bit sequence: " + convertToString(newNumber));
        return newNumber;
    }

    private static String convertToString(int number) {
        return String.format("%32s", Integer.toBinaryString(number)).replace(' ', '0');
    }

    private static int revert(int number) {
        int newNumber = number;
        for (int i = 0; i < Integer.SIZE / 2; i++) {
            newNumber = swap(newNumber, i, Integer.SIZE - 1 - i);
        }
        return newNumber;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int number = new Random().nextInt(Integer.MAX_VALUE);
            String bitSequence = convertToString(number);
            System.out.println("Number: " + number);
            System.out.println("Original bit sequence: " + bitSequence);
            int revertedNumber = revert(number);
            String reverseBitSequence = convertToString(revertedNumber);
            System.out.println("Reversed bit sequence: " + reverseBitSequence);
            String reverseBitSequenceToCheck = new StringBuffer(bitSequence).reverse().toString();
            if (!reverseBitSequence.equals(reverseBitSequenceToCheck)) {
                System.err.println("Bit sequence is not reversed properly");
                System.err.println("Expecting: " + reverseBitSequenceToCheck);
                System.err.println("But found: " + reverseBitSequence);
            }
        }
    }
}
