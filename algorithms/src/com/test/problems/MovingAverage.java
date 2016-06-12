package com.test.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class MovingAverage {
    private final int numberOfItemsToKeep;
    private final BlockingQueue<Integer> inputQueue;
    private final BufferedReader reader;
    private final ExecutorService executor;
    private final AtomicBoolean backgroundProcessStarted = new AtomicBoolean(false);
    private Future backgroundProcessFuture;

    MovingAverage(int numberOfItemsToKeep, BufferedReader reader) {
        this.numberOfItemsToKeep = numberOfItemsToKeep;
        this.inputQueue = new LinkedBlockingQueue<>();
        this.reader = reader;
        this.executor = Executors.newSingleThreadExecutor();
    }

    void startProcessingAverage() {
        System.out.println("Starting background process.");
        this.backgroundProcessFuture = this.executor.submit(() -> processQueue());
        System.out.println("Background process started.");
    }

    void stopProcessingAverage() {
        System.out.println("Stopping background process.");
        this.backgroundProcessFuture.cancel(true);
        this.executor.shutdown();
        System.out.println("Background process is stopped.");
    }

    void processQueue() {
        // Allow only one thread to run.
        if (!backgroundProcessStarted.compareAndSet(false, true)) {
            System.err.println("Background processing is already started!");
            return;
        }
        Queue<Integer> lastNumbers = new LinkedList<>();
        double sum = 0;
        try {
            while (true) {
                Integer number = inputQueue.poll(1, TimeUnit.SECONDS);
                if (number == null) {
                    continue;
                }
                if (lastNumbers.size() > this.numberOfItemsToKeep) {
                    System.err.println("Kept more than needed!");
                    break;
                }
                if (lastNumbers.size() == this.numberOfItemsToKeep) {
                    int numberToTakeOut = lastNumbers.poll();
                    sum -= numberToTakeOut;
                }
                lastNumbers.offer(number);
                sum += number;
                if (lastNumbers.size() == this.numberOfItemsToKeep) {
                    double average = sum / this.numberOfItemsToKeep;
                    System.out.println("Average: " + average);
                }
            }
        } catch (InterruptedException exception) {
            System.out.println("Interrupted.");
            Thread.currentThread().interrupt();
        }
    }

    void processInput() throws IOException {
        System.out.println("Start typing numbers, one per line.");
        System.out.println("I will display the moving average of last " + this.numberOfItemsToKeep + " numbers.");
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            try {
                int number = Integer.parseInt(line);
                this.inputQueue.offer(number);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number: " + line);
                break;
            }
        }
        stopProcessingAverage();
        System.out.println("Finished processing input.");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MovingAverage movingAverage = new MovingAverage(10, reader);
        movingAverage.startProcessingAverage();
        movingAverage.processInput();
    }
}
