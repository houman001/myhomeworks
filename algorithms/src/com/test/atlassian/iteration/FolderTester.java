package com.test.atlassian.iteration;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class FolderTester
{
    public static void main(String[] args)
    {
        MyFolder<Integer, Long> folder = new MyFolder<Integer, Long>();
        Random random = new Random();
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < 1000000; i++)
            queue.offer(random.nextInt(100));
        Long result = folder.fold(0l, queue, new MyFunction2());
        System.out.println("Result: " + result);
    }
}
