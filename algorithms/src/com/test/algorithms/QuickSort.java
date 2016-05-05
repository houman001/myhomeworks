package com.test.algorithms;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private static void swap(int[] array, int i1, int i2) {
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    private static int partition(int[] array, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            throw new RuntimeException("Low index is higher than high index.");
        }
        if (lowIndex == highIndex) {
            return lowIndex;
        }
        // System.out.println("Partitioning: " + Arrays.toString(array) + ", from: " + lowIndex + ", to: " + highIndex);
        int q = lowIndex + 1;
        for (int i = lowIndex + 1; i < highIndex; i++) {
            if (array[i] < array[lowIndex]) {
                swap(array, q, i);
                q++;
            }
        }
        swap(array, lowIndex, q - 1);
        // System.out.println("Partitioning done: " + Arrays.toString(array) + ", q: " + (q - 1));
        return q - 1;
    }

    private static void quickSort(int[] array, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            throw new RuntimeException("Low index is higher than high index.");
        }
        if (lowIndex == highIndex) {
            return;
        }
        int q = partition(array, lowIndex, highIndex);
        quickSort(array, lowIndex, q);
        quickSort(array, q + 1, highIndex);
    }

    private static void quickSort(int[] array) {
        quickSort(array, 0, array.length);
    }

    public static void main(String[] args) {
        int TEST_ARRAY_LENGTH = 20;
        int MIN_RANDOM_NUMBER = 0;
        int MAX_RANDOM_NUMBER = 99;
        int[] array = new int[TEST_ARRAY_LENGTH];
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            array[i] = new Random().nextInt(MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER + 1) + MIN_RANDOM_NUMBER;
        }
        System.out.println("Array: " + Arrays.toString(array));
        quickSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                System.err.println("Array not sorted at index " + i);
            }
        }
    }
}
