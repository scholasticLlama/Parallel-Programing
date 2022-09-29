package com.labs.labwork0;

import java.util.Scanner;
/*
Parallel Programing
Laboratory work №0
First function: E = A + B + C + D*(MA*MD)
Second function: ML = SORT(MF + MG*MH)
Third function: S = (O+P)*TRANS(MR * MT)
@author IO-02 Yaroslava Kozhemiako
@date 29.09.2022
 */

public class Lab0 {
    public static void main(String[] args) {
        System.out.println("main is started");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N: ");
        int N = scanner.nextInt();
        Thread T1 = new Thread(new Thread1(N));
        Thread2 T2 = new Thread2(N);
        Thread3 T3 = new Thread3(N);

        T1.setPriority(Thread.MIN_PRIORITY);
        T2.setPriority(Thread.MAX_PRIORITY);
        T3.setPriority(Thread.NORM_PRIORITY);

        T1.start();
        T2.start();
        T3.start();

        try{
            T1.join();
            T2.join();
            T3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("main is finished");
    }
}
