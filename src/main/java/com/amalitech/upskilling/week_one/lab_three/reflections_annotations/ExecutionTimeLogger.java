package com.amalitech.upskilling.week_one.lab_three.reflections_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.time.Duration;

public class ExecutionTimeLogger {
    private ExecutionTimeLogger() {
    }

    /**
     * Logs the execution time of methods annotated with @LogExecutionTime.
     *
     * @param target The object containing the methods to be analyzed.
     */
    public static void logExecutionTime(Object target) {
        System.out.println("Calculation completed");

        Class<?> targetClass = target.getClass();
        Method[] methods = targetClass.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                logMethodExecutionTime(target, method);
            }
        }

        System.out.println("Calculation completed");
    }

    /**
     * Logs the execution time of a specific method.
     *
     * @param target The object containing the method.
     * @param method The method to be analyzed.
     */
    private static void logMethodExecutionTime(Object target, Method method) {
        long startTime = System.nanoTime();
        try {
            method.setAccessible(true);  // Ensure the method is accessible
            method.invoke(target);
        } catch (Exception e) {
            System.out.println("Error invoking method " + method.getName() + ": " + e.getMessage());
            return;
        }
        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;
        Duration duration = Duration.ofNanos(executionTime);
        long seconds = duration.getSeconds();
        long nanos = duration.toNanos();

        System.out.println("Method " + method.getName() + " executed in " + seconds + " seconds or " + nanos + " nanoseconds");
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface LogExecutionTime {
}

class TestClass {

    @LogExecutionTime
    public void testMethod1() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        System.out.println("Test method 1 executed");
    }

    public void testMethod2() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        System.out.println("Test method 2 executed");
    }
}

class Main {
    public static void main(String[] args) {
        ExecutionTimeLogger.logExecutionTime(new TestClass());
    }
}
