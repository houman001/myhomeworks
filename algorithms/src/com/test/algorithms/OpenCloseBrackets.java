package com.test.algorithms;

public class OpenCloseBrackets {
    private static void printBrackets(int length) {
        printBrackets("", length, length);
    }

    private static void printBrackets(String str, int openBrackets, int closeBrackets) {
        if (openBrackets < 0 || closeBrackets < 0) {
            System.out.println("INVALID STATE!");
        } else if (openBrackets == 0 && closeBrackets == 0) {
            System.out.println(str);
        } else {
            if (closeBrackets > 0 && closeBrackets > openBrackets) {
                printBrackets(str + ")", openBrackets, closeBrackets - 1);
            }
            if (openBrackets > 0) {
                printBrackets(str + "(", openBrackets - 1, closeBrackets);
            }
        }
    }

    public static void main(String[] args) {
        printBrackets(5);
    }
}
