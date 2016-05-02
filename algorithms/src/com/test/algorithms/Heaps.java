package com.test.algorithms;

import java.util.Arrays;
import java.util.Random;

public class Heaps {
    private static int parent(int[] heap, int heapSize, int index) {
        if (heapSize > heap.length) {
            throw new RuntimeException("Invalid heap size");
        }
        if (index < 1) {
            return -1;
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
        int[] heap = Arrays.copyOf(array, array.length);
        for (int i = 1; i < array.length; i++) {
            // System.out.println("Calling maxHeapifyLeaf for sub-array " + (i + 1));
            maxHeapifyLeaf(heap, i + 1, i);
        }
        return heap;
    }

    private static void maxHeapifyRoot(int[] heap, int heapSize, int index) {
        if (heapSize > heap.length) {
            throw new RuntimeException("Invalid heap size: " + heapSize);
        }
        if (index >= heapSize) {
            throw new RuntimeException("Invalid index for heapifying: " + index);
        }
        int maxChildIndex = index;
        int leftIndex = left(heap, heapSize, index);
        int rightIndex = right(heap, heapSize, index);
        if (leftIndex > 0 && heap[leftIndex] > heap[maxChildIndex]) {
            maxChildIndex = leftIndex;
        }
        if (rightIndex > 0 && heap[rightIndex] > heap[maxChildIndex]) {
            maxChildIndex = rightIndex;
        }
        if (heap[maxChildIndex] > heap[index]) {
            swap(heap, index, maxChildIndex);
            maxHeapifyRoot(heap, heapSize, maxChildIndex);
        }
    }

    private static void maxHeapifyLeaf(int[] heap, int heapSize, int index) {
        if (heapSize > heap.length) {
            throw new RuntimeException("Invalid heap size: " + heapSize);
        }
        if (index >= heapSize) {
            throw new RuntimeException("Invalid index for heapifying: " + index);
        }
        // System.out.println("Heap: " + Arrays.toString(Arrays.copyOf(heap, heapSize)) + ", max heapifying: " + index);
        int parentIndex = parent(heap, heapSize, index);
        if (parentIndex >= 0 && heap[parentIndex] < heap[index]) {
            // System.out.println("Swapping: " + index + " with parent: " + parentIndex);
            swap(heap, index, parentIndex);
            maxHeapifyLeaf(heap, heapSize, parentIndex);
        }
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
