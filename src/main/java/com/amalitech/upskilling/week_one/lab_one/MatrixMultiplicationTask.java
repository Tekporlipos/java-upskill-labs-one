package com.amalitech.upskilling.week_one.lab_one;

import java.util.concurrent.RecursiveTask;

class MatrixMultiplicationTask extends RecursiveTask<int[][]> {
    private static final int THRESHOLD = 100;
    private final int[][] matrix1;
    private final int[][] matrix2;
    private final int rowStart;
    private final int rowEnd;
    private final int columnStart;
    private final int columnEnd;

    public MatrixMultiplicationTask(int[][] matrix1, int[][] matrix2,
                                    int rowStart, int rowEnd, int columnStart, int columnEnd) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.columnStart = columnStart;
        this.columnEnd = columnEnd;
    }

    @Override
    protected int[][] compute() {
        int numRowsA = rowEnd - rowStart;
        int numColsB = columnEnd - columnStart;
        int[][] result = new int[numRowsA][numColsB];

        if (numRowsA <= THRESHOLD && numColsB <= THRESHOLD) {
            for (int i = rowStart; i < rowEnd; i++) {
                for (int j = columnStart; j < columnEnd; j++) {
                    for (int k = 0; k < matrix2.length; k++) {
                        result[i - rowStart][j - columnStart] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
        } else {
            int midRow = (rowStart + rowEnd) / 2;
            int midCol = (columnStart + columnEnd) / 2;

            MatrixMultiplicationTask topLeft = new MatrixMultiplicationTask(matrix1, matrix2, rowStart, midRow, columnStart, midCol);
            MatrixMultiplicationTask topRight = new MatrixMultiplicationTask(matrix1, matrix2, rowStart, midRow, midCol, columnEnd);
            MatrixMultiplicationTask bottomLeft = new MatrixMultiplicationTask(matrix1, matrix2, midRow, rowEnd, columnStart, midCol);
            MatrixMultiplicationTask bottomRight = new MatrixMultiplicationTask(matrix1, matrix2, midRow, rowEnd, midCol, columnEnd);

            invokeAll(topLeft, topRight, bottomLeft, bottomRight);

            int[][] topLeftResult = topLeft.join();
            int[][] topRightResult = topRight.join();
            int[][] bottomLeftResult = bottomLeft.join();
            int[][] bottomRightResult = bottomRight.join();

            combineResults(result, topLeftResult, topRightResult, bottomLeftResult, bottomRightResult);
        }

        return result;
    }

    private void combineResults(int[][] result, int[][] topLeftResult, int[][] topRightResult,
                                int[][] bottomLeftResult, int[][] bottomRightResult) {
        int midRow = result.length / 2;
        int midCol = result[0].length / 2;

        for (int i = 0; i < midRow; i++) {
            System.arraycopy(topLeftResult[i], 0, result[i], 0, midCol);
            System.arraycopy(topRightResult[i], 0, result[i], midCol, result[0].length - midCol);
        }

        for (int i = midRow; i < result.length; i++) {
            System.arraycopy(bottomLeftResult[i - midRow], 0, result[i], 0, midCol);
            System.arraycopy(bottomRightResult[i - midRow], 0, result[i], midCol, result[0].length - midCol);
        }
    }
}
