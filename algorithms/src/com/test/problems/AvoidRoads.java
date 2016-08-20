package com.test.problems;

import java.util.HashSet;
import java.util.Set;

public class AvoidRoads {
    private static class Path {
        int x1;
        int y1;
        int x2;
        int y2;

        Path(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Path path = (Path) o;

            if (x1 != path.x1) {
                return false;
            }
            if (y1 != path.y1) {
                return false;
            }
            if (x2 != path.x2) {
                return false;
            }
            return y2 == path.y2;
        }

        @Override
        public int hashCode() {
            int result = x1;
            result = 31 * result + y1;
            result = 31 * result + x2;
            result = 31 * result + y2;
            return result;
        }
    }

    private static int numWays(int width, int height, String[] bads) {
        Set<Path> badSet = new HashSet<>();
        for (String bad : bads) {
            String[] tokens = bad.split(" ");
            badSet.add(new Path(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]),
                                Integer.parseInt(tokens[3])));
            badSet.add(new Path(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[0]),
                                Integer.parseInt(tokens[1])));
        }
        return numWays(width, height, badSet);
    }

    private static int numWays(int width, int height, Set<Path> bads) {
        int[][] ways = new int[width + 1][height + 1];
        ways[0][0] = 1;
        for (int k = 0; k < width + height; k++) {
            for (int i = k, j = 0; i >= 0; i--, j++) {
                if (i < 0 || i > width || j < 0 || j > height) {
                    continue;
                }
                System.out.println("" + i + " : " + j);
                if (i < width) {
                    if (bads.contains(new Path(i, j, i + 1, j))) {
                        System.out.println("Path blocked to right.");
                    } else {
                        ways[i + 1][j] += ways[i][j];
                    }
                }
                if (j < height) {
                    if (bads.contains(new Path(i, j, i, j + 1))) {
                        System.out.println("Path blocked to up.");
                    } else {
                        ways[i][j + 1] += ways[i][j];
                    }
                }
            }
        }
        return ways[width][height];
    }

    public static void main(String[] args) {
        int num = numWays(6, 6, new String[] { "0 0 0 1", "6 6 5 6" });
        // int num = numWays(3, 2, new String[] {  });
        System.out.println("Ways: " + num);
    }
}
