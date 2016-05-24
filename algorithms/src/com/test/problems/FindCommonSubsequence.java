package com.test.problems;

import java.util.Random;

public class FindCommonSubsequence {
    private static char[] ALL_CHARACTERS = new char[26];

    static {
        "abcdefghijklmnopqrstuvwxyz".getChars(0, 26, ALL_CHARACTERS, 0);
    }

    private static char getRandomCharacter() {
        int index = new Random().nextInt(ALL_CHARACTERS.length);
        return ALL_CHARACTERS[index];
    }

    private static String findCommonSubsequence(String s1, String s2) {
        System.out.println("S1: " + s1 + " (" + s1.length() + "), S2: " + s2 + " (" + s2.length() + ")");
        int[][] maxes = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            maxes[i][0] = 0;
        }
        for (int j = 0; j <= s2.length(); j++) {
            maxes[0][j] = 0;
        }
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            int row = i + 1;
            for (int j = 0; j < s2.length(); j++) {
                char c2 = s2.charAt(j);
                int col = j + 1;
                if (c1 == c2) {
                    maxes[row][col] = maxes[row - 1][col - 1] + 1;
                } else if (maxes[row - 1][col] >= maxes[row][col - 1]) {
                    maxes[row][col] = maxes[row - 1][col];
                } else {
                    maxes[row][col] = maxes[row][col - 1];
                }
            }
        }
        for (int i = -1; i <= s1.length(); i++) {
            for (int j = -1; j <= s2.length(); j++) {
                if (j == -1) {
                    System.out.print(i);
                } else if (i == -1) {
                    System.out.print("\t" + j);
                } else {
                    System.out.print("\t" + maxes[i][j]);
                }
            }
            System.out.println();
        }
        int row = s1.length();
        int col = s2.length();
        String result = "";
        while (row > 0 && col > 0) {
            int currentCell = maxes[row][col];
            int up = maxes[row - 1][col];
            int left = maxes[row][col - 1];
            int upLeft = maxes[row - 1][col - 1];
            if (currentCell == up) {
                System.out.print("^");
                row--;
            } else if (currentCell == left) {
                System.out.print("<");
                col--;
            } else if (currentCell == upLeft + 1) {
                System.out.print("\\");
                if (s1.charAt(row - 1) != s2.charAt(col - 1)) {
                    System.err.println("Invalid table!");
                }
                result = s1.charAt(row - 1) + result;
                row--;
                col--;
            } else {
                System.err.println("Invalid table entry at " + row + "," + col);
            }
        }
        System.out.println("");
        return result;
    }

    public static void main(String[] args) {
        String startingSubsequence = "abcdefgh";
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        for (int i = 0; i < startingSubsequence.length(); i++) {
            for (int j = 0; j < 4; j++) {
                int random1 = new Random().nextInt(2);
                int random2 = new Random().nextInt(2);
                if (random1 == 0) {
                    sb1.append(getRandomCharacter());
                }
                if (random2 == 0) {
                    sb2.append(getRandomCharacter());
                }
            }
            sb1.append(startingSubsequence.charAt(i));
            sb2.append(startingSubsequence.charAt(i));
        }
        String s1 = sb1.toString();
        String s2 = sb2.toString();
        String commonSubsequence = findCommonSubsequence(s1, s2);
        System.out.println("Common subsequence: " + commonSubsequence + " (" + commonSubsequence.length() + ")");
        if (commonSubsequence.length() < startingSubsequence.length()) {
            System.err.println("Invalid answer!");
        }
    }
}
