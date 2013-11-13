package com.test.algorithms;

public class Strings
{
    private static int findArray(int[] array, int[] subarray)
    {
        if (array.length < subarray.length || subarray.length < 1)
            return -1;
        int sublength = subarray.length;
        int subcount = 0;
        int count = 0;
        int index = 0;
        for (int i = 0; i < sublength; i++)
        {
            subcount += subarray[i];
            count += array[i];
        }
        while (true)
        {
            if (count == subcount)
            {
                boolean matches = true;
                for (int i = 0; i < sublength; i++)
                    if (array[index + i] != subarray[i])
                    {
                        matches = false;
                        break;
                    }
                if (matches)
                    return index;
            }
            index++;
            if (index > array.length - sublength)
                break;
            count -= array[index - 1];
            count += array[index + sublength - 1];
        }
        return -1;
    }

    public static void main(String[] args)
    {
        int[] array = new int[] { 2, 3, 0, 4, 3, 0, 4, 2, 1, 2, 1, 0, 4, 1, 3, 0, 2, 3, 4, 0 };
        int[] subarray = new int[] { 2, 3, 0 };
        int index = findArray(array, subarray);
        System.out.println("Index: " + index);
    }
}
