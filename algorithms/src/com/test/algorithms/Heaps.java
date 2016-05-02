package com.test.algorithms;

import java.util.Arrays;
import java.util.Random;

public class Heaps {
    private static int parent(int[] heap, int heapSize, int index) {
        if (heapSize > heap.length) {
            throw new RuntimeException("Invalid heap size");
        }
        if (index < 1) {
            throw new RuntimeException("No parent for root");
        }
        return index / 2;
    }

    private static int left(int[] heap, int heapSize, int index) {
        if (heapSize > heap.length) {
            throw new RuntimeException("Invalid heap size");
        }
        if (index * 2 >= heapSize) {
            return -1;
        }
        return index * 2;
    }

    private static int right(int[] heap, int heapSize, int index) {
        if (heapSize > heap.length) {
            throw new RuntimeException("Invalid heap size");
        }
        if (index * 2 + 1 >= heapSize) {
            return -1;
        }
        return index * 2 + 1;
    }

    private static void swap(int[] array, int i1, int i2) {
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    private static int[] generateMaxHeap(int[] array) {
        // Find the next 2^n - 1 number that can contain a complete binary tree.
        int heapArrayLength = (int) Math.pow(2, (int) (Math.log(array.length) / Math.log(2) + 1)) - 1;
        int[] heap = new int[heapArrayLength];
        System.arraycopy(array, 0, heap, 0, array.length);
        for (int i = array.length - 1; i >= 0; i--) {
            maxHeapify(heap, array.length, i);
        }
        return heap;
    }

    private static void maxHeapify(int[] heap, int heapSize, int index) {
        if (heapSize > heap.length) {
            throw new RuntimeException("Invalid heap size: " + heapSize);
        }
        if (index >= heapSize) {
            throw new RuntimeException("Invalid index for heapifying: " + index);
        }
        int maxChildIndex = index;
        int maxChild = heap[index];
        int leftIndex = left(heap, heapSize, index);
        int rightIndex = right(heap, heapSize, index);
        if (leftIndex > 0 && heap[leftIndex] > maxChild) {
            maxChildIndex = leftIndex;
            maxChild = heap[leftIndex];
        }
        if (rightIndex > 0 && heap[rightIndex] > maxChild) {
            maxChildIndex = rightIndex;
            maxChild = heap[rightIndex];
        }
        if (maxChild > heap[index]) {
            swap(heap, index, maxChildIndex);
            maxHeapify(heap, heapSize, maxChildIndex);
        }
    }

    public static void main(String[] args) {
        int TEST_ARRAY_LENGTH = 16;
        int MIN_RANDOM_NUMBER = 0;
        int MAX_RANDOM_NUMBER = 20;
        int[] array = new int[TEST_ARRAY_LENGTH];
        for (int i = 0; i < TEST_ARRAY_LENGTH; i++) {
            array[i] = new Random().nextInt(MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER + 1) + MIN_RANDOM_NUMBER;
        }
        System.out.println("Array: " + Arrays.toString(array));
        int heapSize = array.length;
        int[] heap = generateMaxHeap(array);
        System.out.println("Max heap: " + Arrays.toString(heap));
        for (int i = 0; i < heap.length / 2; i++) {
            int leftIndex = left(heap, heapSize, i);
            int rightIndex = right(heap, heapSize, i);
            if (leftIndex > 0 && heap[leftIndex] > heap[i]) {
                System.err.println("Left child of element index " + i + " is bigger!");
            }
            if (rightIndex > 0 && heap[rightIndex] > heap[i]) {
                System.err.println("Right child of element index " + i + " is bigger!");
            }
        }
    }
}
