package com.test.problems;

import java.util.Random;

public class MaxSumInArray {
    private static void findMaxSubArray(int[] array) {
        long startTime = System.currentTimeMillis();
        MaxSubArray max = findMaxSubArrayRecursively(array, 0, array.length);
        long now = System.currentTimeMillis();
        long timeToProcess = now - startTime;
        System.out.println(
                "RECURSIVE: From: " + max.startIndex + ", to: " + max.endIndex + ", sum: " + max.sum + ", time: " +
                timeToProcess);
    }

    private static MaxSubArray findMaxSubArrayRecursively(int[] array, int left, int right) {
        if (left == right) {
            return new MaxSubArray(left, right, Integer.MIN_VALUE);
        } else if (left == right - 1) {
            return new MaxSubArray(left, right, array[left]);
        }
        int mid = left + (right - left) / 2;
        MaxSubArray crossingResult = findMaxCrossingSubArrayRecursively(array, left, right, mid);
        MaxSubArray leftResult = findMaxSubArrayRecursively(array, left, mid);
        MaxSubArray rightResult = findMaxSubArrayRecursively(array, mid, right);
        if (crossingResult.sum > leftResult.sum && crossingResult.sum > rightResult.sum) {
            return crossingResult;
        }
        if (leftResult.sum > rightResult.sum) {
            return leftResult;
        }
        return rightResult;
    }

    private static MaxSubArray findMaxCrossingSubArrayRecursively(int[] array, int left, int right, int mid) {
        if (left >= right || mid < left || mid > right) {
            System.err.println("Invalid parameters calling findMaxSubArrayRecursively");
            return new MaxSubArray(left, right, Integer.MIN_VALUE);
        }
        int maxLeftSum = Integer.MIN_VALUE;
        int maxLeftIndex = mid;
        int sum = 0;
        for (int i = mid - 1; i >= left; i--) {
            sum += array[i];
            if (sum > maxLeftSum) {
                maxLeftSum = sum;
                maxLeftIndex = i;
            }
        }
        int maxRightSum = Integer.MIN_VALUE;
        int maxRightIndex = mid;
        sum = 0;
        for (int i = mid; i < right; i++) {
            sum += array[i];
            if (sum > maxRightSum) {
                maxRightSum = sum;
                maxRightIndex = i + 1;
            }
        }
        int crossingSum = Integer.MIN_VALUE;
        if (maxLeftSum > Integer.MIN_VALUE || maxRightSum > Integer.MIN_VALUE) {
            if (maxLeftSum > Integer.MIN_VALUE && maxRightSum > Integer.MIN_VALUE) {
                crossingSum = maxLeftSum + maxRightSum;
            } else {
                crossingSum = Math.max(maxLeftSum, maxRightSum);
            }
        }
        return new MaxSubArray(maxLeftIndex, maxRightIndex, crossingSum);
    }

    private static class MaxSubArray {
        int startIndex;
        int endIndex;
        int sum;

        private MaxSubArray(int startIndex, int endIndex, int sum) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.sum = sum;
        }
    }

    private static void findMaxSubArrayInLinearTime(int[] array) {
        long startTime = System.currentTimeMillis();
        int maxSubStartingIndex = 0;
        int maxSubEndingIndex = 0;
        int maxSubSum = Integer.MIN_VALUE;
        int endingMaxSubStartingIndex = 0;
        int endingMaxSubSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int currentItem = array[i];
            if (endingMaxSubSum <= 0 && endingMaxSubSum < currentItem) {
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
        long now = System.currentTimeMillis();
        long timeToProcess = now - startTime;
        System.out.println(
                "LINEAR: From: " + maxSubStartingIndex + ", to: " + maxSubEndingIndex + ", sum: " + maxSubSum +
                ", time: " + timeToProcess);
    }

    public static void main(String[] args) {
        int TEST_ARRAY_LENGTH = 10000000;
        int MIN_RANDOM_NUMBER = -10;
        int MAX_RANDOM_NUMBER = 10;
        int[] array = new int[TEST_ARRAY_LENGTH];
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            array[i] = new Random().nextInt(MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER + 1) + MIN_RANDOM_NUMBER;
        }
        // System.out.println("Array: " + Arrays.toString(array));
        findMaxSubArray(array);
        findMaxSubArrayInLinearTime(array);
        int sum = 0;
        int[] sumArray = new int[TEST_ARRAY_LENGTH];
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            sum += array[i];
            sumArray[i] = sum;
        }
        // System.out.println("Sum: " + Arrays.toString(sumArray));
    }
}
