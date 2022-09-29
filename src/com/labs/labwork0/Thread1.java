package com.labs.labwork0;

import java.util.Arrays;

// E = A + B + C + D*(MA*MD)
public class Thread1 implements Runnable {
    int N;
    double[] A, B, C, D, E;
    double[][] MA, MD;

    public Thread1(int N) {
        this.N = N;
    }

    public void run() {
        System.out.println("T1 is started");
        // введення даних
        A = Data.inputVector(N, "vector-A for T1", 1);
        B = Data.inputVector(N, "vector-B for T1", 1);
        C = Data.inputVector(N, "vector-C for T1", 1);
        D = Data.inputVector(N, "vector-D for T1", 1);
        MA = Data.inputMatrix(N, "matrix-MA for T1", 1);
        MD = Data.inputMatrix(N, "matrix-MD for T1", 1);

        //обчислення функції
        E = Data.calculateFunction1(N, A, B, C, D, MA, MD);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) { }

        //виведення результатів
        System.out.println("Output of the first function: " + Arrays.toString(E));
        System.out.println("T1 is finished");
    }
}
