package com.test.problems;

import java.util.Random;

public class sortTwoDimensionalArray {

    private static int[][] sort(int[][] array) {
        return array;
    }

    public static void main(String[] args) {
        int size = 4;
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int number = new Random().nextInt(100);
                array[i][j] = number;
            }
        }
        int[][] orderedArray = sort(array);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("\t" + array[i][j]);
            }
            System.out.println();
        }
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (orderedArray[i][j] > orderedArray[i][j + 1] ||
                    orderedArray[i][j] > orderedArray[i + 1][j] ||
                    orderedArray[i][j] > orderedArray[i + 1][j + 1]) {
                    System.out.println("Element at " + i + ":" + j + " is not ordered.");
                }
            }
        }
    }
}
