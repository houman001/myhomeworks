package com.test.problems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActivitySelector {
    private static Collection<Integer> iterativeGreedySelect(int[] startTimes, int[] finishTimes, int n) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        int k = 1;
        for (int m = 2; m <= n; m++) {
            if (startTimes[m] >= finishTimes[k]) {
                result.add(m);
                k = m;
            }
        }
        return result;
    }

    private static Collection<Integer> recursiveGreedySelect(int[] startTimes, int[] finishTimes, int length) {
        return recursiveGreedySelect(startTimes, finishTimes, 0, length);
    }

    private static Collection<Integer> recursiveGreedySelect(int[] startTimes, int[] finishTimes, int k, int n) {
        int m = k + 1;
        while (m <= n && startTimes[m] < finishTimes[k]) {
            m++;
        }
        if (m <= n) {
            return Stream.concat(Collections.singleton(m).stream(),
                                 recursiveGreedySelect(startTimes, finishTimes, m, n).stream())
                         .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private static Collection<Integer> dynamicSelect(int[] startTimes, int[] finishTimes, int length) {
        // TODO: Implement
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        int numberOfActivities = 4;
        int[] startTimes = new int[numberOfActivities + 1];
        int[] finishTimes = new int[numberOfActivities + 1];
        startTimes[0] = 0;
        finishTimes[0] = 0;
        startTimes[1] = 1;
        finishTimes[1] = 4;
        startTimes[2] = 2;
        finishTimes[2] = 7;
        startTimes[3] = 3;
        finishTimes[3] = 8;
        startTimes[4] = 2;
        finishTimes[4] = 10;
        Collection<Integer> recursiveGreedyResult = recursiveGreedySelect(startTimes, finishTimes, 4);
        int i = 1;
        for (int index : recursiveGreedyResult) {
            System.out.println("Activity #" + i + ", from " + startTimes[index] + " to " + finishTimes[index]);
            i++;
        }
        Collection<Integer> iterativeGreedyResult = recursiveGreedySelect(startTimes, finishTimes, 4);
        if (!recursiveGreedyResult.equals(iterativeGreedyResult)) {
            throw new RuntimeException("Iterative greedy does not equal recursive greedy.");
        }
    }
}
