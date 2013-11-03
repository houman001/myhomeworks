package com.test.algorithms;

import java.util.Arrays;
import java.util.Random;

public class Trees
{
    private static void quickSort(int[] array)
    {
        quickSort(array, 0, array.length);
    }

    private static void quickSort(int[] array, int start, int end)
    {
        if (end - start < 2)
            return;
        int pivot = new Random().nextInt(end - start) + start;
        swap(array, start, pivot);
        int p = start;
        for (int i = start + 1; i < end; i++)
        {
            if (array[i] < array[start])
            {
                swap(array, p + 1, i);
                p++;
            }
        }
        swap(array, start, p);
        quickSort(array, start, p);
        quickSort(array, p + 1, end);
    }

    private static void swap(int[] array, int pos1, int pos2)
    {
        int i = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = i;
    }

    public static void main(String[] args)
    {
        int[] array = new int[] { 14, 5, 16, 17, 7, 19, 8, 10, 4, 12, 2, 11, 3, 6, 20, 15, 9, 1, 18, 13 };
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
