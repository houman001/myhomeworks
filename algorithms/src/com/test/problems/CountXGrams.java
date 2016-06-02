package com.test.problems;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CountXGrams {
    private static Map<String, Integer> countMonograms(String text) {
        return countGrams(text, 1);
    }

    private static Map<String, Integer> countBigrams(String text) {
        return countGrams(text, 2);
    }

    private static Map<String, Integer> countTrigrams(String text) {
        return countGrams(text, 3);
    }

    private static boolean isValid(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isAlphabetic(text.codePointAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static Map<String, Integer> countGrams(String text, int gramLength) {
        Map<String, Integer> counts = new HashMap<>();
        for (int i = 0; i <= text.length() - gramLength; i++) {
            String subString = text.substring(i, i + gramLength).toLowerCase();
            if (!isValid(subString)) {
                continue;
            }
            int count = counts.containsKey(subString) ? counts.get(subString) : 0;
            counts.put(subString, count + 1);
        }
        return counts;
    }

    private static Stream<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> map) {
        return map.entrySet().stream().sorted(Map.Entry.comparingByValue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }));
    }

    private static void printTop(Map<String, Integer> map) {
        sortByValue(map).limit(5).forEachOrdered(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }

    public static void main(String[] args) {
        // Check  results against http://practicalcryptography.com/cryptanalysis/text-characterisation/monogram-bigram-and-trigram-frequency-counts/
        String sampleText = "How do I love thee? Let me count the ways.\n"
                            + "\n"
                            + "I love thee to the depth and breadth and height\n"
                            + "\n"
                            + "My soul can reach, when feeling out of sight\n"
                            + "\n"
                            + "For the ends of Being and ideal Grace\n"
                            + "\n"
                            + "I love thee to the level of everyday's\n"
                            + "\n"
                            + "Most quiet need, by sun and candle-light.\n"
                            + "\n"
                            + "I love thee freely, as men strive for Right;\n"
                            + "\n"
                            + "I love thee purely, as they turn from Praise.\n"
                            + "\n"
                            + "I love thee with the passion put to use\n"
                            + "\n"
                            + "In my old griefs, and with my childhood's faith\n"
                            + "\n"
                            + "I love thee with a love I seem to love\n"
                            + "\n"
                            + "With my lost saints, - I love thee with the breath,\n"
                            + "\n"
                            + "Smiles, tears, of all my life! - and, if God choose,\n"
                            + "\n"
                            + "I shall but love thee better after death.\n"
                            + "\n";
        printTop(countMonograms(sampleText));
        printTop(countBigrams(sampleText));
        printTop(countTrigrams(sampleText));
    }
}
