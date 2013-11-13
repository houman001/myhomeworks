package com.test.atlassian.findarray;

public class MyFindArray implements FindArray
{
    public int findArray(int[] array, int[] subArray)
    {
        if (array.length < subArray.length || subArray.length < 1)
            return -1;
        int sublength = subArray.length;
        int subcount = 0;
        int count = 0;
        int index = 0;
        for (int i = 0; i < sublength; i++)
        {
            subcount += subArray[i];
            count += array[i];
        }
        while (true)
        {
            if (count == subcount)
            {
                boolean matches = true;
                for (int i = 0; i < sublength; i++)
                    if (array[index + i] != subArray[i])
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
}
