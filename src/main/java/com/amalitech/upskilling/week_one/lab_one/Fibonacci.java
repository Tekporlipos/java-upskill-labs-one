package com.amalitech.upskilling.main;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {
    private final Integer inputNumber;

    public Fibonacci(int inputNumber) {
        this.inputNumber = inputNumber;
    }

    @Override
    protected Integer compute() {
        if (inputNumber <= 1) {
            return inputNumber;
        }
        Fibonacci f1 = new Fibonacci(inputNumber - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(inputNumber - 2);
        return f2.compute() + f1.join();
    }

    public static void main(String[] args) {
        int inputNumber = 20;
        Fibonacci fibonacci = new Fibonacci(inputNumber);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            int result = forkJoinPool.invoke(fibonacci);
            System.out.println("Fibonacci number " + inputNumber + " is: " + result);
        }
    }
}
