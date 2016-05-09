package com.test.algorithms;

import java.util.Arrays;
import java.util.Random;

public class RadixSort {

    private static int getRadix(int number, int radix) {
        for (int i = 0; i < radix; i++) {
            number /= 10;
        }
        return number % 10;
    }

    private static int[] radixSort(int[] array, int maxNumberLength, int radix) {
        for (int element : array) {
            if (Math.log10(element) > maxNumberLength) {
                throw new RuntimeException("Array element is longer than max length.");
            }
        }
        int[] sortedArray = new int[array.length];
        int copyPosition = 0;
        for (int digit = 0; digit < 10; digit++) {
            for (int element : array) {
                if (getRadix(element, radix) == digit) {
                    sortedArray[copyPosition] = element;
                    copyPosition++;
                }
            }
        }
        return sortedArray;
    }

    private static int[] radixSort(int[] array, int maxNumberLength) {
        for (int radix = 0; radix < maxNumberLength; radix++) {
            array = radixSort(array, maxNumberLength, radix);
        }
        return array;
    }

    private static void ensureArrayIsSorted(int[] array) {
        // System.out.println("Sorted array: " + Arrays.toString(array));
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                System.err.println("Array not sorted at index " + i);
            }
        }
    }

    public static void main(String[] args) {
        int TEST_ARRAY_LENGTH = 100;
        int MIN_RANDOM_NUMBER = 0;
        int MAX_RANDOM_NUMBER = 99;
        int maxNumberLength = (int) Math.ceil(Math.log10(MAX_RANDOM_NUMBER));
        int[] array = new int[TEST_ARRAY_LENGTH];
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            array[i] = new Random().nextInt(MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER + 1) + MIN_RANDOM_NUMBER;
        }
        System.out.println("Array: " + Arrays.toString(array));
        int[] arrayToSort = radixSort(array, maxNumberLength);
        System.out.println("Sorted array: " + Arrays.toString(arrayToSort));
        ensureArrayIsSorted(arrayToSort);
    }
}
