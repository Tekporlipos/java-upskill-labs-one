package com.amalitech.upskilling.main;

import com.amalitech.upskilling.OutPut;

import java.util.concurrent.ForkJoinPool;

public class ParallelMatrixMultiplication {
    public static void main(String[] args) {
        int[][] inputMatrixA = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] inputMatrixB = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        int numRowsA = inputMatrixA.length;
        int numColsB = inputMatrixB[0].length;

        int[][] result;
        try (ForkJoinPool pool = new ForkJoinPool()) {
            MatrixMultiplicationTask task = new MatrixMultiplicationTask(inputMatrixA, inputMatrixB,
                    0, numRowsA, 0, numColsB);
            result = pool.invoke(task);
        }

        OutPut.printColoredTextBlock("Matrix A:", OutPut.Colors.MAGENTA);
        printMatrix(inputMatrixA);
        OutPut.printColoredTextBlock("Matrix B:", OutPut.Colors.MAGENTA);
        printMatrix(inputMatrixB);
        OutPut.printColoredTextBlock("Result:", OutPut.Colors.MAGENTA);
        printMatrix(result);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
