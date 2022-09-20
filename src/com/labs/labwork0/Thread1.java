package com.labs.labwork0;

// E = A + B + C + D*(MA*MD)
public class Thread1 extends Thread {
    int N;
    double[] A, B, C, D, E;
    double[][] MA, MD;

    public Thread1(int N) {
        this.N = N;
    }

    public void run() {
        System.out.println("T1 is started");
        // введення даних
        A = Data.inputVector(N);
        B = Data.inputVector(N);
        C = Data.inputVector(N);
        D = Data.inputVector(N);
        MA = Data.inputMatrix(N);
        MD = Data.inputMatrix(N);

        //обчислення функції
        E = Data.calculateFunction1(N, A, B, C, D, MA, MD);

        //виведення результатів
        System.out.println("Output of the first function: ");
        for (double e : E) {
            System.out.println(e);
        }
        System.out.println("T1 is finished");
    }
}
