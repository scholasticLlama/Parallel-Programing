package com.labs.labwork0;

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
        MF = Data.inputMatrix(N);
        MG = Data.inputMatrix(N);
        MH = Data.inputMatrix(N);

        //обчислення функції
        ML = Data.calculateFunction2(N, MF, MG, MH);

        //виведення результатів
        System.out.println("Output of the second function: ");
        for (double[] vector : ML) {
            for (double elem : vector) {
                System.out.println(elem);
            }
        }
        System.out.println("T2 is finished");
    }
}
