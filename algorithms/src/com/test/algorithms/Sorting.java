package com.test.algorithms;

import java.util.Arrays;
import java.util.Random;

public class Sorting
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

    private static void mergeSort(int[] array)
    {
        mergeSort(array, 0, array.length);
    }

    private static void mergeSort(int[] array, int start, int end)
    {
        if (end - start < 2)
            return;
        int midPoint = start + (end - start) / 2;
        mergeSort(array, start, midPoint);
        mergeSort(array, midPoint, end);
        merge(array, start, midPoint, midPoint, end);
    }

    private static void merge(int[] array, int s1, int e1, int s2, int e2)
    {
        int[] temp = new int[e1 - s1 + e2 - s2];
        int i1 = s1;
        int i2 = s2;
        for (int i = 0; i < temp.length; i++)
        {
            if (i1 >= e1)
                temp[i] = array[i2++];
            else if (i2 >= e2)
                temp[i] = array[i1++];
            else
            {
                if (array[i1] < array[i2])
                    temp[i] = array[i1++];
                else
                    temp[i] = array[i2++];
            }
        }
        System.arraycopy(temp, 0, array, s1, temp.length);
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
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }
}
