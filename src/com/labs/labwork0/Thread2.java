package com.labs.labwork0;

import java.util.Arrays;

//ML = SORT(MF + MG*MH)
public class Thread2 extends Thread {
    int N;
    double[][] MF, MG, MH, ML;

    public Thread2(int N) {
        this.N = N;
    }

    public void run(){
        System.out.println("T2 is started");
        // введення даних
        MF = Data.inputMatrix(N, "matrix-MF  for T2", 2);
        MG = Data.inputMatrix(N, "matrix-MG for T2", 2);
        MH = Data.inputMatrix(N, "matrix-MH for T2", 2);

        //обчислення функції
        ML = Data.calculateFunction2(N, MF, MG, MH);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) { }

        //виведення результатів
        System.out.println("Output of the second function: ");
        for (double[] vector : ML) {
            System.out.println(Arrays.toString(vector));
        }
        System.out.println("T2 is finished");
    }
}
