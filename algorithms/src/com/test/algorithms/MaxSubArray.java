package com.test.algorithms;

import java.util.Arrays;
import java.util.Random;

public class MaxSubArray {
    private static void findMaxSubArrayInLinearTime(int[] array) {
        int maxSubStartingIndex = 0;
        int maxSubEndingIndex = 0;
        int maxSubSum = Integer.MIN_VALUE;
        int endingMaxSubStartingIndex = 0;
        int endingMaxSubSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int currentItem = array[i];
            if (endingMaxSubSum <= 0) {
                endingMaxSubStartingIndex = i;
                endingMaxSubSum = currentItem;
            } else {
                endingMaxSubSum += currentItem;
            }
            if (endingMaxSubSum > maxSubSum) {
                maxSubStartingIndex = endingMaxSubStartingIndex;
                maxSubEndingIndex = i + 1;
                maxSubSum = endingMaxSubSum;
            }
        }
        System.out.println("From: " + maxSubStartingIndex + ", to: " + maxSubEndingIndex + ", sum: " + maxSubSum);
    }

    public static void main(String[] args) {
        int TEST_ARRAY_LENGTH = 16;
        int MIN_RANDOM_NUMBER = -10;
        int MAX_RANDOM_NUMBER = 10;
        int[] array = new int[TEST_ARRAY_LENGTH];
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            array[i] = new Random().nextInt(MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER + 1) + MIN_RANDOM_NUMBER;
        }
        System.out.println("Array: " + Arrays.toString(array));
        findMaxSubArrayInLinearTime(array);
        int sum = 0;
        int[] sumArray = new int[TEST_ARRAY_LENGTH];
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            sum += array[i];
            sumArray[i] = sum;
        }
        System.out.println("Sum: " + Arrays.toString(sumArray));
    }
}
