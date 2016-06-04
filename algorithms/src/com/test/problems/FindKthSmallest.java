package com.test.problems;

import java.util.Arrays;
import java.util.Random;

public class FindKthSmallest {
    private static int findKthSmallest(int[] array, int k) {
        return findKthSmallest(array, k, 0, array.length);
    }

    private static int findKthSmallest(int[] array, int k, int left, int right) {
//        System.out.println("Array: " + Arrays.toString(array));
//        System.out.println("Looking for " + k + "th from " + left + " to " + right);
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
        int smallerItemsAreBeforeThis = left;
        int biggerItemsBeginAfterThis = right - 1;
        boolean allEqualPivot = true;
        while (smallerItemsAreBeforeThis <= biggerItemsBeginAfterThis) {
            if (array[smallerItemsAreBeforeThis] != pivotValue || array[biggerItemsBeginAfterThis] != pivotValue) {
                allEqualPivot = false;
            }
            if (array[smallerItemsAreBeforeThis] <= pivotValue) {
                smallerItemsAreBeforeThis++;
            } else if (array[biggerItemsBeginAfterThis] > pivotValue) {
                biggerItemsBeginAfterThis--;
            } else {
                swap(array, smallerItemsAreBeforeThis, biggerItemsBeginAfterThis);
                smallerItemsAreBeforeThis++;
                biggerItemsBeginAfterThis--;
            }
        }
//        System.out.println("Pivot is " + pivotValue + ", smaller items are before " + smallerItemsAreBeforeThis +
//                           " bigger items are after " + biggerItemsBeginAfterThis);
        if (allEqualPivot) {
            return pivotValue;
        }
        if (k < smallerItemsAreBeforeThis) {
            return findKthSmallest(array, k, left, smallerItemsAreBeforeThis);
        }
        return findKthSmallest(array, k, biggerItemsBeginAfterThis + 1, right);
    }

    private static void swap(int[] array, int i1, int i2) {
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    public static void main(String[] args) {
        int TEST_ARRAY_LENGTH = 10000000;
        int MIN_RANDOM_NUMBER = 0;
        int MAX_RANDOM_NUMBER = 10000;
        int[] array = new int[TEST_ARRAY_LENGTH];
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            array[i] = new Random().nextInt(MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER + 1) + MIN_RANDOM_NUMBER;
        }
//        int[] array = new int[] { 51, 38, 58, 1, 9, 69, 55, 69 };
        int k = TEST_ARRAY_LENGTH / 4 + new Random().nextInt(TEST_ARRAY_LENGTH / 2);
//        System.out.println("Array: " + Arrays.toString(array));
        int kth = findKthSmallest(array, k);
        System.out.println("" + k + "th: " + kth);
        Arrays.sort(array);
        if (array[k] != kth) {
            System.err.println("BOOM! We were expecting: " + array[k]);
        }
    }
}
