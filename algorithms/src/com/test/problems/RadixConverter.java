package com.test.problems;

import java.util.Random;

public class RadixConverter {
    private static long convertBinaryToBase(int number, int radix) {
        if (number < 2) {
            return number;
        }
        return convertBinaryToBase(number / 2, radix) * radix + (number % 2);
    }

    public static void main(String[] args) {
        int number = new Random().nextInt(9999);
        for (int radix = 2; radix <= 10; radix++) {
            String numberString = Integer.toString(number, 2);
            long parsedNumber = Long.parseLong(numberString, radix);
            long convertedNumber = convertBinaryToBase(number, radix);
            if (convertedNumber != parsedNumber) {
                System.err.println("Invalid conversion for " + numberString + " and radix " + radix);
            } else {
                System.out.println("Number " + numberString + " in radix " + radix + " is: " + convertedNumber);
            }
        }
    }
}
