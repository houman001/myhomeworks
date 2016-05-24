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

    private static int findSubstringRabinKarp(String str, String substring) {
        System.out.println("String: " + str + ", substring: " + substring);
        // FIXME: Implement
        return 0;
    }

    public static void main(String[] args) {
        String substring = "abcdefghij";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < new Random().nextInt(16); i++) {
            sb.append(getRandomCharacter());
        }
        sb.append(substring);
        for (int i = 0; i < new Random().nextInt(16); i++) {
            sb.append(getRandomCharacter());
        }
        String str = sb.toString();
        int index = findSubstringRabinKarp(str, substring);
        System.out.println("Substring found at: " + index);
        String subStringRetrieved = str.substring(index, index + substring.length());
        if (!substring.equals(subStringRetrieved)) {
            System.err.println("Invalid answer!");
        }
    }
}
