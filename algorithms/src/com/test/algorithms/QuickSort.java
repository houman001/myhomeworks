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
        // The first element is pivot, select a random element in the array and swap it with the first elemetn.
        int randomIndex = lowIndex + new Random(System.currentTimeMillis()).nextInt(highIndex - lowIndex);
        swap(array, lowIndex, randomIndex);
        // System.out.println("Partitioning: " + Arrays.toString(array) + ", from: " + lowIndex + ", to: " + highIndex);
        int smallerItemsMaxIndex = lowIndex + 1;
        for (int i = lowIndex + 1; i < highIndex; i++) {
            // Swap this item with the first item of the bigger numbers, and move the dividing pointer forward.
            if (array[i] < array[lowIndex]) {
                swap(array, smallerItemsMaxIndex, i);
                smallerItemsMaxIndex++;
            }
        }
        swap(array, lowIndex, smallerItemsMaxIndex - 1);
        // System.out.println("Partitioning done: " + Arrays.toString(array) + ", q: " + (q - 1));
        return smallerItemsMaxIndex - 1;
    }

    private static long quickSort(int[] array, int lowIndex, int highIndex, long depth) {
        if (lowIndex > highIndex) {
            throw new RuntimeException("Low index is higher than high index.");
        }
        long maxDepth = depth;
        if (lowIndex < highIndex - 1) {
            int pivot = partition(array, lowIndex, highIndex);
            maxDepth = Math.max(maxDepth, quickSort(array, lowIndex, pivot, depth + 1));
            maxDepth = Math.max(maxDepth, quickSort(array, pivot + 1, highIndex, depth + 1));
        }
        return maxDepth;
    }

    private static void quickSort(int[] array) {
        long startTime = System.currentTimeMillis();
        long depth = quickSort(array, 0, array.length, 0);
        long now = System.currentTimeMillis();
        System.out.println("Dumb quick sort, " + (now - startTime) + " milliseconds, max depth: " + depth);
    }

    private static long smarterQuickSort(int[] array, int lowIndex, int highIndex, long depth) {
        if (lowIndex > highIndex) {
            throw new RuntimeException("Low index is higher than high index.");
        }
        long maxDepth = depth;
        while (lowIndex < highIndex - 1) {
            int pivot = partition(array, lowIndex, highIndex);
            // Only recurse on the shorter subarray
            if (pivot - lowIndex < highIndex - pivot) {
                maxDepth = Math.max(maxDepth, smarterQuickSort(array, lowIndex, pivot, depth + 1));
                lowIndex = pivot + 1;
            } else {
                maxDepth = Math.max(maxDepth, smarterQuickSort(array, pivot + 1, highIndex, depth + 1));
                highIndex = pivot;
            }
        }
        return maxDepth;
    }

    private static void smarterQuickSort(int[] array) {
        long startTime = System.currentTimeMillis();
        long maxDepth = smarterQuickSort(array, 0, array.length, 0);
        long now = System.currentTimeMillis();
        System.out.println("Smarter quick sort, " + (now - startTime) + " milliseconds, max depth: " + maxDepth);
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
        int TEST_ARRAY_LENGTH = 1000000;
        int MIN_RANDOM_NUMBER = 0;
        int MAX_RANDOM_NUMBER = 9999;
        int[] array = new int[TEST_ARRAY_LENGTH];
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            array[i] = new Random().nextInt(MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER + 1) + MIN_RANDOM_NUMBER;
        }
        // System.out.println("Array: " + Arrays.toString(array));
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        quickSort(arrayToSort);
        ensureArrayIsSorted(arrayToSort);
        arrayToSort = Arrays.copyOf(array, array.length);
        smarterQuickSort(arrayToSort);
        ensureArrayIsSorted(arrayToSort);
    }
}
