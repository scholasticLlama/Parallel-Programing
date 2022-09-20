package com.labs.labwork0;

import java.util.Arrays;
import java.util.Scanner;

// E = A + B + C + D*(MA*MD)
//ML = SORT(MF + MG*MH)
//S= (O+P)*TRANS(MR * MT)
public class Data {
    public static double[] inputVector(int N){
        return (N<=4)?enterVector(N):generateVector(N);
    }

    public static double[][] inputMatrix(int N){
        double[][] matrix = new double[N][N];
        for (int i = 0; i < N; i++) {
            matrix[i] = (N<=4)?enterVector(N):generateVector(N);
        }
        return matrix;
    }

    private static double[] generateVector(int N) {
        double[] vector = new double[N];
        for (int i = 0; i < N; i++) {
            vector[i] = Math.random()*10 + 2;
        }
        return vector;
    }

    private static double[] enterVector(int N) {
        System.out.println("Enter vector[N]: ");
        double[] vector = new double[N];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < N; i++) {
            vector[i] = scanner.nextInt();
        }
        return vector;
    }

    public static double[] calculateFunction1(int N, double[] A, double[] B, double[] C, double[] D, double[][] MA, double[][] MD) {
        double[][] ME = matrixMultiplication(N, MA, MD);
        double[] E = matrixOnVectorMultiplication(N, D, ME);
        E = vectorsSum(N, A, B, C, E);
        return E;
    }

    public static double[][] calculateFunction2(int N, double[][] MF, double[][] MG, double[][] MH) {
        double[][] ML = matrixMultiplication(N, MG, MH);
        ML = matrixSum(N, MF, ML);
        sortedMatrix(N, ML);
        return ML;
    }

    public static double[] calculateFunction3(int N, double[] O, double[] P, double[][] MR, double[][] MT){
        double[][] M = matrixMultiplication(N, MR, MT);
        M = matrixTransposition(N, M);
        double[] S = vectorsSum(N, O, P);
        S = matrixOnVectorMultiplication(N, S, M);
        return S;
    }

    public static double[][] matrixMultiplication(int N, double[][] MA, double[][] MB) {
        double[][] ME = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int r = 0; r < N; r++) {
                    ME[i][j] += MA[i][r] * MB[r][j];
                }
            }
        }
        return ME;
    }

    public static double[] matrixOnVectorMultiplication(int N, double[] A, double[][] MA) {
        double[] E = new double[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                E[i] += A[j] * MA[j][i];
            }
        }
        return E;
    }

    public static double[] vectorsSum(int N, double[]... vectors) {
        double[] E = new double[N];
        for (double[] vector : vectors) {
            for (int i = 0; i < N; i++) {
                E[i] += vector[i];
            }
        }
        return E;
    }

    public static double[][] matrixSum(int N, double[][] M, double[][] M2) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                M[i][j] += M2[i][j];
            }
        }
        return M;
    }

    public static double[][] sortedMatrix(int N, double[][] M) {
        for (int i = 0; i < N; i++) {
            Arrays.sort(M[i]);
        }
        return M;
    }

    public static double[][] matrixTransposition(int N, double[][] M){
        double[][] transposeM = new double[N][N];
        int numberOfRow = 0;
        for (double[] row : M) {
            for (int i = 0; i < N; i++) {
                transposeM[i][numberOfRow] = row[i];
            }
            numberOfRow++;
        }
        return transposeM;
    }

    public static void main(String[] args) {
        Data data = new Data();
        double[] result1 = data.calculateFunction1(3, new double[]{1, 2, 3}, new double[]{5, 6, 7}, new double[]{0, 3, 1}, new double[]{1, 1, 1}, new double[][]{{4, 2, 7}, {5, 3, 1}, {1, 9, 6}}, new double[][]{{0, 2, 3}, {4, 6, 5}, {2, 3, 4}});
        double[][] result2 = data.calculateFunction2(3, new double[][]{{1, 2, 3}, {0, 3, -1}, {2, 1, -10}}, new double[][]{{4, 2, 7}, {5, 3, 1}, {1, 9, 6}}, new double[][]{{0, 2, 3}, {4, 6, 5}, {2, 3, 4}});
        double[] result3 = data.calculateFunction3(3, new double[]{1, 2, 3}, new double[]{5, 6, 7}, new double[][]{{4, 2, 7}, {5, 3, 1}, {1, 9, 6}}, new double[][]{{0, 2, 3}, {4, 6, 5}, {2, 3, 4}});
        System.out.println(Arrays.toString(result1));
        System.out.println();
        for (double[] row : result2) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        System.out.println(Arrays.toString(result3));
    }
}
