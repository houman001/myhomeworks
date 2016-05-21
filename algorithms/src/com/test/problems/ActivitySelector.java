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
        int length = startTimes.length - 1;
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

    private static Map<Set<Integer>, Set<Integer>> interimSolutions;

    // Activities are sorted by their finish time.
    private static Collection<Integer> dynamicSelect(int[] startTimes, int[] finishTimes) {
        interimSolutions = new HashMap<>();
        Set<Integer> activities = new HashSet<>();
        for (int i = 1; i < startTimes.length; i++)
            activities.add(i);
        return dynamicSelect(startTimes, finishTimes, activities);
    }

    private static boolean isCompatible(int[] startTimes, int[] finishTimes, int a1, int a2) {
        return startTimes[a1] >= finishTimes[a2] || startTimes[a2] >= finishTimes[a1];
    }

    private static int hoursCovered(int[] startTimes, int[] finishTimes, Collection<Integer> activities) {
        int sum = 0;
        for (int activity : activities)
            sum += (finishTimes[activity] - startTimes[activity]);
        return sum;
    }

    private static Collection<Integer> dynamicSelect(int[] startTimes, int[] finishTimes, Set<Integer> activities) {
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
        Collection<Integer> compatibleActivitiesSelected = dynamicSelect(startTimes, finishTimes, compatibleActivities);
        compatibleActivitiesSelected.add(selectedActivity);
        int hoursIfSelected = hoursCovered(startTimes, finishTimes, compatibleActivitiesSelected);
        Set<Integer> otherActivities = new HashSet<>(activities);
        otherActivities.remove(selectedActivity);
        Collection<Integer> otherActivitiesSelected = dynamicSelect(startTimes, finishTimes, otherActivities);
        int hoursIfNotSelected = hoursCovered(startTimes, finishTimes, otherActivitiesSelected);
        return (hoursIfSelected > hoursIfNotSelected) ? compatibleActivitiesSelected : otherActivitiesSelected;
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
        startTimes[3] = 4;
        finishTimes[3] = 8;
        startTimes[4] = 2;
        finishTimes[4] = 10;
        Collection<Integer> recursiveGreedyResult = recursiveGreedySelect(startTimes, finishTimes);
        System.out.println("Greedy, hours covered: " + hoursCovered(startTimes, finishTimes, recursiveGreedyResult));
        int i = 1;
        for (int index : recursiveGreedyResult) {
            System.out.println("Activity #" + index + ", from " + startTimes[index] + " to " + finishTimes[index]);
            i++;
        }
        Collection<Integer> iterativeGreedyResult = iterativeGreedySelect(startTimes, finishTimes);
        if (!recursiveGreedyResult.equals(iterativeGreedyResult)) {
            throw new RuntimeException("Iterative greedy does not equal recursive greedy.");
        }
        Collection<Integer> dynamicResult = dynamicSelect(startTimes, finishTimes);
        System.out.println("Dynamic, hours covered: " + hoursCovered(startTimes, finishTimes, dynamicResult));
        i = 1;
        for (int index : dynamicResult) {
            System.out.println("Activity #" + index + ", from " + startTimes[index] + " to " + finishTimes[index]);
            i++;
        }
    }
}
