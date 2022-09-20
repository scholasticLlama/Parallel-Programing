package com.labs.labwork0;

//S= (O+P)*TRANS(MR * MT)
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
        O = Data.inputVector(N);
        P = Data.inputVector(N);
        MR = Data.inputMatrix(N);
        MT = Data.inputMatrix(N);

        //обчислення функції
        S = Data.calculateFunction3(N, O, P, MR, MT);

        //виведення результатів
        System.out.println("Output of the third function: ");
        for (double s : S) {
            System.out.println(s);
        }
        System.out.println("T3 is finished");
    }
}
