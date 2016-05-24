package com.test.problems;

import java.util.Stack;

public class OpenCloseBrackets {
    private static void printBrackets(int length, boolean print) {
        printBrackets("", 2 * length, 0, print);
    }

    private static void printBrackets(String str, int remainingLength, int balance, boolean print) {
        if (remainingLength < balance || balance < 0) {
            System.err.println("INVALID STATE!");
        } else if (remainingLength == 0) {
            boolean check = checkStringUsingCount(str);
            // boolean check = checkStringUsingStack(str);
            if (check && print) {
                System.out.println(str);
            }
        } else {
            if (remainingLength > balance) {
                printBrackets(str + "(", remainingLength - 1, balance + 1, print);
            }
            if (balance > 0) {
                printBrackets(str + ")", remainingLength - 1, balance - 1, print);
            }
        }
    }

    private static boolean checkStringUsingStack(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.empty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.empty();
    }

    private static boolean checkStringUsingCount(String str) {
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
        for (int length = 1; length < 10; length++) {
            long start = System.currentTimeMillis();
            printBrackets(length, false);
            long end = System.currentTimeMillis();
            System.out.println("Time to process for " + length + ":" + (end - start));
        }
    }
}
