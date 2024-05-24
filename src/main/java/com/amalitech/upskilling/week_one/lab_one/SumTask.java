package com.amalitech.upskilling.main;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {
    private final long[] array;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 1000;

    public SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length < THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        }

        int middle = start + length / 2;
        SumTask leftTask = new SumTask(array, start, middle);
        SumTask rightTask = new SumTask(array, middle, end);
        leftTask.fork();
        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();

        return leftResult + rightResult;
    }

    public static void main(String[] args) {
        int size = 10000;
        long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }

        long result;
        try (ForkJoinPool pool = new ForkJoinPool()) {
            SumTask task = new SumTask(array, 0, array.length);
            result = pool.invoke(task);
        }
        System.out.println("Sum of array elements: " + result);
    }
}
