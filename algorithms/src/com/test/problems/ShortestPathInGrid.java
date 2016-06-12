package com.test.problems;

import java.util.HashSet;
import java.util.Set;

public class ShortestPathInGrid {
    private static class Point {
        int row;
        int column;

        Point(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    private static int findShortestPathLength(char[][] grid, Point source, Point destination) {
        int rows = grid.length;
        if (rows < 1) {
            throw new RuntimeException("Invalid grid.");
        }
        int columns = grid[0].length;
        if (columns < 1) {
            throw new RuntimeException("Invalid grid.");
        }
        if (source.row < 0 || source.row >= rows) {
            throw new RuntimeException("Invalid source");
        }
        if (source.column < 0 || source.column >= columns) {
            throw new RuntimeException("Invalid source");
        }
        if (grid[source.row][source.column] != '.') {
            throw new RuntimeException("Invalid source");
        }
        if (destination.row < 0 || destination.row >= rows) {
            throw new RuntimeException("Invalid destination");
        }
        if (destination.column < 0 || destination.column >= columns) {
            throw new RuntimeException("Invalid destination");
        }
        if (grid[destination.row][destination.column] != '.') {
            throw new RuntimeException("Invalid destination");
        }
        int[][] weights = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                weights[i][j] = Integer.MAX_VALUE;
            }
        }
        weights[source.row][source.column] = 0;
        processNode(grid, weights, source);
        printWeights(weights);
        return weights[destination.row][destination.column];
    }

    private static void processNode(char[][] grid, int[][] weights, Point point) {
        System.out.println("Processing point " + point.row + ":" + point.column);
        Set<Point> neighbors = getNeighbors(grid, point);
        Set<Point> neighborsToProcess = new HashSet<>();
        for (Point neighbor : neighbors) {
            if (weights[neighbor.row][neighbor.column] > weights[point.row][point.column] + 1) {
                weights[neighbor.row][neighbor.column] = weights[point.row][point.column] + 1;
                neighborsToProcess.add(neighbor);
            }
        }
        for (Point neighbor : neighborsToProcess) {
            processNode(grid, weights, neighbor);
        }
    }

    private static Set<Point> getNeighbors(char[][] grid, Point point) {
        int row = point.row;
        int column = point.column;
        Set<Point> neighbors = new HashSet<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (isValid(grid, row + i, column + j)) {
                    neighbors.add(new Point(row + i, column + j));
                }
            }
        }
        return neighbors;
    }

    private static boolean isValid(char[][] grid, int row, int column) {
        int rows = grid.length;
        int columns = grid[0].length;
        return (row >= 0 && row < rows && column >= 0 && column < columns && grid[row][column] == '.');
    }

    private static void printWeights(int[][] weights) {
        System.out.println("Weights:");
        for (int[] row : weights) {
            for (int entry : row) {
                System.out.print("\t" + entry);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] grid = new char[][] {
                { '.', '.', '.', '.' },
                { '*', '*', '.', '*' },
                { '*', '.', '.', '*' },
                { '.', '.', '*', '*' },
                { '*', '.', '.', '.' }
        };
        int length = findShortestPathLength(grid, new Point(0, 0), new Point(4, 3));
        System.out.println("Shortest path is " + length);
    }
}
