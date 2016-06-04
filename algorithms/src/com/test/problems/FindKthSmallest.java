package com.test.problems;

import java.util.Arrays;
import java.util.Random;

public class FindKthSmallest {
    private static int findKthSmallest(int[] array, int k) {
        return findKthSmallest(array, k, 0, array.length);
    }

    private static int findKthSmallest(int[] array, int k, int left, int right) {
        System.out.println("Looking for " + k + "th from " + left + " to " + right);
        if (left > right) {
            throw new RuntimeException("We messed up!");
        }
        if (left == right) {
            throw new RuntimeException("Array is empty!");
        }
        if (left == right - 1) {
            return array[left];
        }
        int pivot = left + new Random().nextInt(right - left);
        int pivotValue = array[pivot];
        // System.out.println("Pivot is " + pivotValue + " at " + pivot);
        // Swap pivot with the first item
        swap(array, left, pivot);
        // System.out.println("Array after swapping pivot: " + Arrays.toString(array));
        int smallerItemsAreBeforeThis = left + 1;
        int biggerItemsBeginAfterThis = right - 1;
        boolean allEqual = true;
        while (smallerItemsAreBeforeThis <= biggerItemsBeginAfterThis) {
            if (array[smallerItemsAreBeforeThis] != pivotValue || array[biggerItemsBeginAfterThis] != pivotValue) {
                allEqual = false;
            }
            // The last item in the lower sub-array definitely in its place.
            if (array[smallerItemsAreBeforeThis] < pivotValue) {
                smallerItemsAreBeforeThis++;
            } else if (array[biggerItemsBeginAfterThis] > pivotValue) {
                biggerItemsBeginAfterThis--;
            } else {
                // System.out.println("Swapping: " + smallerItemsAreBeforeThis + " with " + biggerItemsBeginAfterThis);
                swap(array, smallerItemsAreBeforeThis, biggerItemsBeginAfterThis);
                smallerItemsAreBeforeThis++;
                biggerItemsBeginAfterThis--;
            }
        }
        // Bring back pivot
        swap(array, left, smallerItemsAreBeforeThis - 1);
        // System.out.println("Re-arranged Array: " + Arrays.toString(array));
        // System.out.println("Partitioned at " + (smallerItemsAreBeforeThis - 1));
        if (allEqual || smallerItemsAreBeforeThis == k + 1) {
            return pivotValue;
        }
        if (k < smallerItemsAreBeforeThis) {
            return findKthSmallest(array, k, left, smallerItemsAreBeforeThis - 1);
        }
        return findKthSmallest(array, k, biggerItemsBeginAfterThis + 1, right);
    }

    private static void swap(int[] array, int i1, int i2) {
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    public static void main(String[] args) {
        int TEST_ARRAY_LENGTH = 1000000;
        int MIN_RANDOM_NUMBER = 0;
        int MAX_RANDOM_NUMBER = 1000;
        int[] array = new int[TEST_ARRAY_LENGTH];
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            array[i] = new Random().nextInt(MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER + 1) + MIN_RANDOM_NUMBER;
        }
        int k = TEST_ARRAY_LENGTH / 4 + new Random().nextInt(TEST_ARRAY_LENGTH / 2);
        // System.out.println("Array: " + Arrays.toString(array));
        int kth = findKthSmallest(array, k);
        System.out.println("" + k + "th: " + kth);
        Arrays.sort(array);
        if (array[k] != kth) {
            System.err.println("BOOM! We were expecting: " + array[k]);
        }
    }
}
