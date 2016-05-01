package com.test.algorithms;

public class OpenCloseBrackets {
    private static void printBrackets(int length) {
        printBrackets("", length, length);
    }

    private static void printBrackets(String str, int openBrackets, int closeBrackets) {
        if (openBrackets < 0 || closeBrackets < 0) {
            System.err.println("INVALID STATE!");
        } else if (openBrackets == 0 && closeBrackets == 0) {
            if (checkString(str)) {
                System.out.println(str);
            }
        } else {
            if (closeBrackets > 0 && closeBrackets > openBrackets) {
                printBrackets(str + ")", openBrackets, closeBrackets - 1);
            }
            if (openBrackets > 0) {
                printBrackets(str + "(", openBrackets - 1, closeBrackets);
            }
        }
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
        printBrackets(5);
    }
}
