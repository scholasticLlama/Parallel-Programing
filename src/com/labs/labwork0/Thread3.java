package com.labs.labwork0;

import java.util.Arrays;

//S = (O+P)*TRANS(MR * MT)
public class Thread3 extends Thread{
    int N;
    double[] O, P, S;
    double[][] MR, MT;

    public Thread3(int N){
        this.N = N;
    }

    public void run(){
        System.out.println("T3 is started");
        // введення даних
        O = Data.inputVector(N, "vector-O for T3", 3);
        P = Data.inputVector(N, "vector-P for T3", 3);
        MR = Data.inputMatrix(N, "matrix-MR for T3", 3);
        MT = Data.inputMatrix(N, "matrix-MT for T3", 3);

        //обчислення функції
        S = Data.calculateFunction3(N, O, P, MR, MT);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) { }

        //виведення результатів
        System.out.println("Output of the third function: " + Arrays.toString(S));
        System.out.println("T3 is finished");
    }
}
