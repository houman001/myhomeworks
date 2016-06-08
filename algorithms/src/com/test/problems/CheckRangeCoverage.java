package com.test.problems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckRangeCoverage {
    private static class Range implements Comparable<Range> {
        private int start;
        private int finish;

        private Range(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }

        @Override
        public String toString() {
            return "" + start + "-" + finish;
        }

        @Override
        public int compareTo(Range o) {
            if (o == null) {
                return 1;
            }
            return start - o.start;
        }
    }

    private static void printRanges(List<Range> ranges) {
        System.out.println("Ranges:");
        ranges.forEach(System.out::println);
    }

    private static boolean isRangeCovered(Collection<Range> sortedRanges, Range rangeToCover) {
        int coveredUpTo = rangeToCover.start;
        for (Range range : sortedRanges) {
            if (coveredUpTo >= rangeToCover.finish) {
                break;
            }
            // Doesn't help, we are already covered;
            if (range.finish < coveredUpTo) {
                continue;
            }
            // This doesn't help, and nothing else in the list does, since it is sorted by start.
            if (range.start > coveredUpTo) {
                break;
            }
            // This range actually helps.
            coveredUpTo = range.finish;
        }
        return coveredUpTo >= rangeToCover.finish;
    }

    private static void test(List<Range> ranges, Range rangeToCover) {
        boolean isCovered = isRangeCovered(ranges, rangeToCover);
        System.out.println("Covers " + rangeToCover + "? " + isCovered);
        Set<Integer> allThePoints = new HashSet<>();
        for (Range range : ranges) {
            for (int i = range.start; i <= range.finish; i++) {
                allThePoints.add(i);
            }
        }
        boolean foundTheHardWay = true;
        for (int i = rangeToCover.start; i <= rangeToCover.finish; i++) {
            if (!allThePoints.contains(i)) {
                foundTheHardWay = false;
                break;
            }
        }
        if (foundTheHardWay != isCovered) {
            System.err.println("BOOM!");
        }
    }

    public static void main(String[] args) {
        List<Range> ranges = new ArrayList();
        for (int i = 0; i < 10; i++) {
            int start = 1 + new Random().nextInt(90);
            int duration = 1 + new Random().nextInt(20);
            ranges.add(new Range(start, start + duration));
        }
        ranges = ranges.stream().sorted().collect(Collectors.toList());
        printRanges(ranges);
        test(ranges, new Range(1, 20));
        test(ranges, new Range(21, 40));
        test(ranges, new Range(41, 60));
        test(ranges, new Range(61, 80));
        test(ranges, new Range(81, 100));
    }
}
