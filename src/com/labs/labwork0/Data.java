package com.labs.labwork0;

import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;


public class Data {
    static {
        File directory = new File("src/files");
        Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .filter(Predicate.not(File::isDirectory))
                .forEach(File::delete);
    }

    public static double[] inputVector(int N, String name, int variant){
        double[] vector = (N<=4)?enterVector(N, name):autoCreationOfVector(variant, N, name);
        writeVectorInFile(vector, name);
        return vector;
    }

    public static double[][] inputMatrix(int N, String name, int variant){
        double[][] matrix;
        matrix = (N<=4)?enterMatrix(N, name):autoCreationOfMatrix(variant, N, name);
        writeMatrixInFile(matrix, name);
        return matrix;
    }

    private static double[] autoCreationOfVector(int variant, int N, String name){
        switch(variant){
            case 1: return writeAndReadGeneratedVectorInFile(N, name);
            case 2: return fillVectorWithOneNumber(N);
            case 3: return generateVector(N);
        }
        return null;
    }

    private static double[][] autoCreationOfMatrix(int variant, int N, String name){
        switch(variant){
            case 1: return writeAndReadGeneratedMatrixInFile(N, name);
            case 2: return fillMatrixWithOneNumber(N);
            case 3: return generateMatrix(N);
        }
        return null;
    }

    private static double[] fillVectorWithOneNumber(int N) {
        double[] vector = new double[N];
        Arrays.fill(vector, 1);
        return vector;
    }

    private static double[][] fillMatrixWithOneNumber(int N) {
        double[][] matrix = new double[N][N];
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(matrix[i], 1);
        }
        return matrix;
    }

    private static double[] generateVector(int N) {
        double[] vector = new double[N];
        for (int i = 0; i < N; i++) {
            vector[i] = (int)((Math.random()*10 + 2) * 10000.0) / 10000.0;
        }
        //System.out.println(Arrays.toString(vector));
        return vector;
    }

    private static double[][] generateMatrix(int N) {
        double[][] matrix = new double[N][N];
        for (int i = 0; i < N; i++) {
            matrix[i] = generateVector(N);
        }
        return matrix;
    }

    private static double[] enterVector(int N, String name) {
        System.out.println("Enter " + name + ": ");
        double[] vector = new double[N];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < N; i++) {
                try{
                    vector[i] = scanner.nextInt();
                } catch (InputMismatchException e){
                    System.out.println("Wrong input!");
                }


        }
        return vector;
    }

    private static double[][] enterMatrix(int N, String name) {
        double[][] matrix = new double[N][N];
        for (int i = 0; i < N; i++) {
            matrix[i] = enterVector(N, "row " + (i + 1) + " for matrix " + name);
        }
        return matrix;
    }

    private static double[] writeAndReadGeneratedVectorInFile(int N, String name){
        writeVectorInFile(generateVector(N), name);
        return readVectorFromFile(name);
    }

    private static double[][] writeAndReadGeneratedMatrixInFile(int N, String name){
        writeMatrixInFile(generateMatrix(N), name);
        return readMatrixFromFile(N, name);
    }

    private static double[][] readMatrixFromFile(int N, String name) {
        double[][] matrix = new double[N][N];
        try{
            FileReader file = new FileReader("src/files/" + name + ".txt");
            Scanner scan = new Scanner(file);
            int i = 0;
            while(scan.hasNextLine()){
                String[] vectorInLine = scan.nextLine().split(",");
                for (int j = 0; j < vectorInLine.length; j++) {
                    matrix[i][j] = Double.parseDouble(vectorInLine[j]);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return matrix;
    }

    private static double[] readVectorFromFile(String name) {
        double[] vector;
        try{
            FileReader file = new FileReader("src/files/" + name + ".txt");
            Scanner scan = new Scanner(file);
            String[] vectorInLine = scan.nextLine().substring(1).split(",");
            vector = new double[vectorInLine.length];
            for (int i = 0; i < vector.length; i++) {
                vector[i] = Double.parseDouble(vectorInLine[i]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return vector;
    }

    private static void writeVectorInFile(double[] vector, String name){
        try{
            FileWriter file = new FileWriter("src/files/" + name + ".txt");
            file.write(Arrays.toString(vector).substring(1, Arrays.toString(vector).length() - 1));

            file.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void writeMatrixInFile(double[][] matrix, String name){
        try {
            FileWriter file = new FileWriter("src/files/" + name + ".txt");
            int length = matrix.length - 1;
            for (int i = 0; i < length; i++) {
                file.write(Arrays.toString(matrix[i]).substring(1, Arrays.toString(matrix[i]).length() - 1) + "\r\n");
            }
            file.write(Arrays.toString(matrix[length]).substring(1, Arrays.toString(matrix[length]).length() - 1));
            file.close();
        } catch(IOException e){
            e.printStackTrace();
        }
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

}
