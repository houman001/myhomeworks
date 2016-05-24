package com.test.problems;

import java.util.Random;

public class FindCommonPattern {
    private static char[] ALL_CHARACTERS = new char[26];

    static {
        "abcdefghijklmnopqrstuvwxyz".getChars(0, 26, ALL_CHARACTERS, 0);
    }

    private static char getRandomCharacter() {
        int index = new Random().nextInt(ALL_CHARACTERS.length);
        return ALL_CHARACTERS[index];
    }

    private static String findCommonPattern(String s1, String s2) {
        System.out.println("S1: " + s1 + ", S2: " + s2);
        // FIXME: Implement
        return null;
    }

    public static void main(String[] args) {
        String substring = "abcdefghij";
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        for (int i = 0; i < substring.length(); i++) {
            int random1 = new Random().nextInt(3);
            int random2 = new Random().nextInt(3);
            if (random1 == 0) {
                sb1.append(getRandomCharacter());
            }
            if (random2 == 0) {
                sb2.append(getRandomCharacter());
            }
            sb1.append(substring.charAt(i));
            sb2.append(substring.charAt(i));
        }
        String s1 = sb1.toString();
        String s2 = sb2.toString();
        String commonPattern = findCommonPattern(s1, s2);
        System.out.println("Max substring: " + commonPattern);
        if (!substring.equals(findCommonPattern(commonPattern, substring))) {
            System.err.println("Invalid answer!");
        }
    }
}
