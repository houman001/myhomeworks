package com.test.problems;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class LongestZigZag {
    private static int longestZigZag(int[] numbers) {
        if (numbers.length < 1) {
            return 0;
        }
        int[] diffs = new int[numbers.length - 1];
        for (int i = 0; i < numbers.length - 1; i++) {
            diffs[i] = numbers[i + 1] - numbers[i];
        }
        for (int i = 0; i < diffs.length; i++) {
            System.out.print(diffs[i] + " ");
        }
        System.out.println();
        return longestZigZagFromDiffs(diffs);
    }

    private static int longestZigZagFromDiffs(int[] diffs) {
        final AtomicInteger lastSign = new AtomicInteger(0);
        final AtomicInteger signChanges = new AtomicInteger(0);
        IntStream.range(0, diffs.length).mapToObj(i -> diffs[i]).forEachOrdered(diff -> {
            int sign = diff < 0 ? -1 : diff > 0 ? 1 : 0;
            if (sign != 0 && lastSign.get() != sign) {
                signChanges.incrementAndGet();
                lastSign.set(sign);
            }
        });
        return signChanges.incrementAndGet();
    }

    public static void main(String[] args) {
        int longestZigZag = longestZigZag(new int[] {
                374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
                600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
                67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
                477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
                249, 22, 176, 279, 23, 22, 617, 462, 459, 244
        });
        System.out.println("Longest zigzag: " + longestZigZag);
    }
}
