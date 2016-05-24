package com.test.problems;

import java.util.Random;

public class StringMatching {
    private static char[] ALL_CHARACTERS = new char[26];

    static {
        "abcdefghijklmnopqrstuvwxyz".getChars(0, 26, ALL_CHARACTERS, 0);
    }

    private static char getRandomCharacter() {
        int index = new Random().nextInt(ALL_CHARACTERS.length);
        return ALL_CHARACTERS[index];
    }

    private static int findSubstringRabinKarp(String text, String pattern) {
        System.out.println("String is " + text.length() + " characters long, pattern: " + pattern);
        int textLength = text.length();
        int patternLength = pattern.length();
        int base = 8;
        int bigPrimeNumber = 15485863;

        if (textLength < patternLength) {
            System.out.println("Text is not long enough!");
            return -1;
        }

        // calculate the hash value of the pattern
        int patternHash = 0;
        for (int i = 0; i < patternLength; i++) {
            patternHash = getRemainder(patternHash * base + pattern.charAt(i), bigPrimeNumber);
        }

        int rollingHash = 0;
        for (int i = 0; i < patternLength; i++) {
            rollingHash = getRemainder(rollingHash * base + text.charAt(i), bigPrimeNumber);
        }

        int index = 0;
        int reductionFactor = 1;
        for (int i = 0; i < patternLength - 1; i++) {
            reductionFactor = getRemainder(reductionFactor * base, bigPrimeNumber);
        }
        while (true) {
            // Check the text at the current position
            if (rollingHash == patternHash) {
                if (patternExists(text, pattern, index)) {
                    return index;
                }
            }
            // Index points at the first character to compare
            index++;
            if (index < 0) {
                System.err.println("Index underflow!");
                return -1;
            }
            if (index > textLength - patternLength) {
                break;
            }
            // Remove the character that just fell out
            rollingHash = getRemainder(
                    rollingHash - getRemainder(text.charAt(index - 1) * reductionFactor, bigPrimeNumber),
                    bigPrimeNumber);
            // Shift the values of existing characters up
            rollingHash = getRemainder(rollingHash * base, bigPrimeNumber);
            // Add the new character to hash
            rollingHash = getRemainder(rollingHash + text.charAt(index + patternLength - 1), bigPrimeNumber);
        }
        System.out.println("Not found!");
        return -1;
    }

    private static boolean patternExists(String text, String pattern, int index) {
        int patternLength = pattern.length();
        if (text.length() < index + patternLength) {
            System.out.println("Text is not long enough!");
            return false;
        }
        if (!text.substring(index, index + pattern.length()).equals(pattern)) {
            System.out.println("Substring " + text.substring(index, index + pattern.length()) + " not equal!");
            return false;
        }
        return true;
    }

    // correctly calculates a mod b even if a < 0
    private static int getRemainder(int a, int b) {
        return (a % b + b) % b;
    }

    public static void main(String[] args) {
        String substring = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100000000 + new Random().nextInt(1000000); i++) {
            sb.append(getRandomCharacter());
        }
        sb.append(substring);
        for (int i = 0; i < 100 + new Random().nextInt(100); i++) {
            sb.append(getRandomCharacter());
        }
        String str = sb.toString();
        int index = findSubstringRabinKarp(str, substring);
        System.out.println("Substring found at: " + index);
        if (index < 0) {
            System.err.println("Couldn't find substring!");
            return;
        }
        String subStringRetrieved = str.substring(index, index + substring.length());
        if (!substring.equals(subStringRetrieved)) {
            System.err.println("Invalid answer!");
        }
    }
}
