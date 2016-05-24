package com.test.problems;

public class OpenCloseBrackets {
    private static void printBrackets(int length, boolean print) {
        printBrackets("", length, length, print);
    }

    private static void printBrackets(String str, int openBrackets, int closeBrackets, boolean print) {
        if (openBrackets < 0 || closeBrackets < 0) {
            System.err.println("INVALID STATE!");
        } else if (openBrackets == 0 && closeBrackets == 0) {
            if (checkString(str) && print) {
                System.out.println(str);
            }
        } else {
            if (closeBrackets > 0 && closeBrackets > openBrackets) {
                printBrackets(str + ")", openBrackets, closeBrackets - 1, print);
            }
            if (openBrackets > 0) {
                printBrackets(str + "(", openBrackets - 1, closeBrackets, print);
            }
        }
    }

    private static boolean checkStringUsingStack(String str) {
        // FIXME: Implement
        return false;
    }

    private static boolean checkString(String str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                sum++;
            } else if (c == ')') {
                sum--;
            } else {
                System.err.println("INVALID CHARACTER: " + c);
            }
            if (sum < 0) {
                System.err.println("INVALID STRING GENERATED: " + str);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int length = 1; length < 20; length++) {
            long start = System.currentTimeMillis();
            printBrackets(length, false);
            long end = System.currentTimeMillis();
            System.out.println("Time to process for " + length + ":" + (end - start));
        }
    }
}
