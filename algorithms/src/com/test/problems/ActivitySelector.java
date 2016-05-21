package com.test.problems;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActivitySelector {
    // GREEDY

    // Activities are sorted by their finish time.
    private static Collection<Integer> iterativeGreedySelect(int[] startTimes, int[] finishTimes) {
        List<Integer> result = new ArrayList<>();
        int length = startTimes.length;
        result.add(1);
        int k = 1;
        for (int m = 2; m < length; m++) {
            if (startTimes[m] >= finishTimes[k]) {
                result.add(m);
                k = m;
            }
        }
        return result;
    }

    // Activities are sorted by their finish time.
    private static Collection<Integer> recursiveGreedySelect(int[] startTimes, int[] finishTimes) {
        return recursiveGreedySelect(startTimes, finishTimes, 0);
    }

    private static Collection<Integer> recursiveGreedySelect(int[] startTimes, int[] finishTimes, int k) {
        int m = k + 1;
        int length = startTimes.length;
        while (m < length && startTimes[m] < finishTimes[k]) {
            m++;
        }
        if (m < length) {
            return Stream.concat(Collections.singleton(m).stream(),
                    recursiveGreedySelect(startTimes, finishTimes, m).stream())
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    // DYNAMIC

    // Activities are sorted by their finish time.
    private static Collection<Integer> recursiveDynamicSelect(int[] startTimes, int[] finishTimes) {
        Map<Set<Integer>, Set<Integer>> interimSolutions = new HashMap<>();
        Set<Integer> activities = new HashSet<>();
        for (int i = 1; i < startTimes.length; i++)
            activities.add(i);
        return recursiveDynamicSelect(interimSolutions, startTimes, finishTimes, activities);
    }

    private static boolean isCompatible(int[] startTimes, int[] finishTimes, int a1, int a2) {
        return a1 != a2 && (startTimes[a1] >= finishTimes[a2] || startTimes[a2] >= finishTimes[a1]);
    }

    private static int hoursCovered(int[] startTimes, int[] finishTimes, Collection<Integer> activities) {
        int sum = 0;
        for (int activity : activities)
            sum += (finishTimes[activity] - startTimes[activity]);
        return sum;
    }

    private static Collection<Integer> recursiveDynamicSelect(Map<Set<Integer>, Set<Integer>> interimSolutions,
                                                              int[] startTimes, int[] finishTimes,
                                                              Set<Integer> activities) {
        // System.out.println("Called for " + activities);
        if (activities.size() < 1)
            return new ArrayList<>();
        if (activities.size() == 1)
            return new ArrayList<>(activities);
        if (interimSolutions.containsKey(activities))
            return interimSolutions.get(activities);
        // System.out.println("Solving for " + activities);
        int selectedActivity = activities.iterator().next();
        Set<Integer> compatibleActivities = activities.stream().filter(activityToCheck ->
                isCompatible(startTimes, finishTimes, selectedActivity, activityToCheck)).collect(Collectors.toSet());
        // System.out.println("Compatible activities with " + selectedActivity + ": " + compatibleActivities);
        Collection<Integer> compatibleActivitiesSelected = recursiveDynamicSelect(interimSolutions, startTimes,
                finishTimes, compatibleActivities);
        compatibleActivitiesSelected.add(selectedActivity);
        int hoursIfSelected = hoursCovered(startTimes, finishTimes, compatibleActivitiesSelected);
        Set<Integer> otherActivities = new HashSet<>(activities);
        otherActivities.remove(selectedActivity);
        Collection<Integer> otherActivitiesSelected = recursiveDynamicSelect(interimSolutions, startTimes, finishTimes,
                otherActivities);
        int hoursIfNotSelected = hoursCovered(startTimes, finishTimes, otherActivitiesSelected);
        return (hoursIfSelected > hoursIfNotSelected) ? compatibleActivitiesSelected : otherActivitiesSelected;
    }

    private static void collectIterativeDynamicResult(Set<Integer> resultCollector, int[][] ks, int i, int j, int k) {
        if (k < 1)
            return;
        resultCollector.add(k);
        collectIterativeDynamicResult(resultCollector, ks, i, k, ks[i][k]);
        collectIterativeDynamicResult(resultCollector, ks, k, j, ks[k][j]);
    }

    private static Collection<Integer> iterativeDynamicSelect(int[] startTimes, int[] finishTimes) {
        int length = startTimes.length;
        int[][] cs = new int[length][length];
        int[][] ks = new int[length][length];
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++) {
                cs[i][j] = 0;
                ks[i][j] = 0;
            }
        for (int i = 1; i < length; i++) {
            for (int row = 0, col = i; col < length; row++, col++) {
                int maxC = 0;
                int bestK = 0;
                Set<Integer> activitiesInBetween = findActivitiesInBetween(startTimes, finishTimes, row, col);
                for (int k : activitiesInBetween) {
                    int c = cs[row][k] + cs[k][col] + (finishTimes[k] - startTimes[k]);
                    if (c > maxC) {
                        maxC = c;
                        bestK = k;
                    }
                }
                cs[row][col] = maxC;
                ks[row][col] = bestK;
            }
        }
        Set<Integer> result = new HashSet<>();
        int i = 0;
        int j = length - 1;
        int firstK = ks[i][j];
        collectIterativeDynamicResult(result, ks, i, j, firstK);
        return result;
    }

    private static Set<Integer> findActivitiesInBetween(int[] startTimes, int[] finishTimes, int from, int to) {
        Set<Integer> activities = new HashSet<>();
        for (int i = 0; i < startTimes.length; i++) {
            if (startTimes[i] >= finishTimes[from] && finishTimes[i] <= startTimes[to])
                activities.add(i);
        }
        return activities;
    }

    public static void main(String[] args) {
        int numberOfActivities = 80;
        int[] startTimes = new int[numberOfActivities + 2];
        int[] finishTimes = new int[numberOfActivities + 2];
        startTimes[0] = 0;
        finishTimes[0] = 0;
        int lastFinishTime = 1;
        System.out.print("Activities: ");
        for (int i = 1; i <= numberOfActivities; i++) {
            if (i % 5 == 1)
                System.out.println("");
            else if (i > 1)
                System.out.print(" | ");
            int finishTime = lastFinishTime + new Random().nextInt(4);
            int duration = new Random().nextInt(12);
            int startTime = finishTime - duration;
            if (startTime < 1)
                startTime = 1;
            if (finishTime <= startTime)
                finishTime = startTime + 1;
            startTimes[i] = startTime;
            finishTimes[i] = finishTime;
            lastFinishTime = finishTime;
            System.out.print("#" + i + " " + startTime + "-" + finishTime);
        }
        startTimes[numberOfActivities + 1] = lastFinishTime;
        finishTimes[numberOfActivities + 1] = lastFinishTime;
        System.out.println("");
        Collection<Integer> recursiveGreedyResult = recursiveGreedySelect(startTimes, finishTimes);
        System.out.println("\nGreedy, hours covered: " + hoursCovered(startTimes, finishTimes, recursiveGreedyResult) +
                ", count: " + recursiveGreedyResult.size());
        for (int index : recursiveGreedyResult) {
            System.out.println("Activity #" + index + ", from " + startTimes[index] + " to " + finishTimes[index]);
        }
        Collection<Integer> iterativeGreedyResult = iterativeGreedySelect(startTimes, finishTimes);
        if (!recursiveGreedyResult.equals(iterativeGreedyResult)) {
            throw new RuntimeException("Iterative greedy " + iterativeGreedyResult +
                    " does not equal recursive greedy " + recursiveGreedyResult + ".");
        }
        if (false) {
            Collection<Integer> recursiveDynamicResult = recursiveDynamicSelect(startTimes, finishTimes);
            System.out.println("\nDynamic, hours covered: " + hoursCovered(startTimes, finishTimes, recursiveDynamicResult) +
                    ", count: " + recursiveDynamicResult.size());
            for (int index : recursiveDynamicResult) {
                System.out.println("Activity #" + index + ", from " + startTimes[index] + " to " + finishTimes[index]);
            }
        }
        Collection<Integer> iterativeDynamicResult = iterativeDynamicSelect(startTimes, finishTimes);
        System.out.println("\nDynamic, hours covered: " + hoursCovered(startTimes, finishTimes, iterativeDynamicResult) +
                ", count: " + iterativeDynamicResult.size());
        for (int index : iterativeDynamicResult) {
            System.out.println("Activity #" + index + ", from " + startTimes[index] + " to " + finishTimes[index]);
        }
    }
}
