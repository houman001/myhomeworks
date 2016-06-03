package com.test.problems;

import java.util.Random;

public class SortRowsAndColumns {

    private static void printArray(String label, int[][] array) {
        System.out.println(label);
        int rows = array.length;
        int columns = array[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("\t" + array[i][j]);
            }
            System.out.println();
        }
    }

    private static void sortRowsAndColumns(int[][] array) {
        int rows = array.length;
        int columns = array[0].length;
        for (int row = 0; row < rows; row++) {
            // Buuble sort the row
            for (int col = 0; col < columns; col++) {
                for (int j = col + 1; j < columns; j++) {
                    if (array[row][col] > array[row][j]) {
                        int temp = array[row][col];
                        array[row][col] = array[row][j];
                        array[row][j] = temp;
                    }
                }
            }
        }
        // printArray("Rows Sorted", array);
        for (int col = 0; col < columns; col++) {
            // Buuble sort the column
            for (int row = 0; row < rows; row++) {
                for (int i = row + 1; i < rows; i++) {
                    if (array[row][col] > array[i][col]) {
                        int temp = array[row][col];
                        array[row][col] = array[i][col];
                        array[i][col] = temp;
                    }
                }
            }
        }
        printArray("Sorted Array", array);
    }

    private static void findValue(int[][] array, int value) {
        int rows = array.length;
        int columns = array[0].length;
        boolean found = findValue(array, value, 0, rows, 0, columns);
        if (!found) {
            System.out.println("Couldn't find " + value);
        }
    }

    private static boolean findValue(int[][] array, int value, int rowFrom, int rowTo, int columnFrom, int columnTo) {
        // Check validity of arguments
        if (rowFrom >= rowTo || columnFrom >= columnTo) {
            return false;
        }
        // Binary search if rows or columns equal 1
        if (rowFrom == rowTo - 1 && columnFrom == columnTo - 1) {
            if (array[rowFrom][columnFrom] == value) {
                System.out.println("Found " + value + " at " + rowFrom + ":" + columnFrom);
                return true;
            }
            return false;
        }
        int midRow = rowFrom + (rowTo - rowFrom) / 2;
        int midColumn = columnFrom + (columnTo - columnFrom) / 2;
        if (array[midRow][midColumn] == value) {
            System.out.println("Found " + value + " at " + midRow + ":" + midColumn);
            return true;
        } else if (value > array[midRow][midColumn]) {
            return findValue(array, value, rowFrom, rowTo, midColumn + 1, columnTo) ||
                   findValue(array, value, midRow + 1, rowTo, columnFrom, midColumn + 1);
        } else { // value < array[midRow][midColumn]
            return findValue(array, value, rowFrom, rowTo, columnFrom, midColumn) ||
                   findValue(array, value, rowFrom, midRow, midColumn + 1, columnTo);
        }
    }

    public static void main(String[] args) {
        int size = 8;
        int maxNumber = 100;
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int number = new Random().nextInt(maxNumber);
                array[i][j] = number;
            }
        }
        printArray("Original Array", array);
        sortRowsAndColumns(array);
        // Verify array is sorted
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (array[i][j] > array[i][j + 1] ||
                    array[i][j] > array[i + 1][j] ||
                    array[i][j] > array[i + 1][j + 1]) {
                    System.err.println("Element at " + i + ":" + j + " is not ordered.");
                }
            }
        }
        // Find random values
        for (int i = 0; i < 10; i++) {
            int randomValue = new Random().nextInt(maxNumber);
            findValue(array, randomValue);
        }
    }
}
