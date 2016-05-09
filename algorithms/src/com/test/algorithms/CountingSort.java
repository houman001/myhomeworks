package com.test.algorithms;

import java.util.Arrays;
import java.util.Random;

public class CountingSort {

    private static int[] countingSort(int[] array, int maxValue) {
        int[] indexes = new int[maxValue + 1];
        Arrays.fill(indexes, 0);
        for (int element : array) {
            if (element > maxValue) {
                throw new RuntimeException("Array element is bigger than max.");
            }
            indexes[element]++;
        }
        for (int i = 1; i <= maxValue; i++) {
            indexes[i] += indexes[i - 1];
        }
        System.out.println("Indexes: " + Arrays.toString(indexes));
        int[] sortedArray = new int[array.length];
        int pos = 0;
        for (int j = 0; j <= maxValue; j++) {
            while (pos < indexes[j]) {
                sortedArray[pos] = j;
                pos++;
            }
        }
        return sortedArray;
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
        int TEST_ARRAY_LENGTH = 1000;
        int MIN_RANDOM_NUMBER = 0;
        int MAX_RANDOM_NUMBER = 99;
        int[] array = new int[TEST_ARRAY_LENGTH];
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            array[i] = new Random().nextInt(MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER + 1) + MIN_RANDOM_NUMBER;
        }
        System.out.println("Array: " + Arrays.toString(array));
        int[] arrayToSort = countingSort(array, MAX_RANDOM_NUMBER);
        System.out.println("Sorted array: " + Arrays.toString(arrayToSort));
        ensureArrayIsSorted(arrayToSort);
    }
}
