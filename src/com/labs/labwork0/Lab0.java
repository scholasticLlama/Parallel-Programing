package com.labs.labwork0;

import java.io.InputStream;
import java.util.Scanner;

public class Lab0 {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N: ");
        int N = scanner.nextInt();
        Thread1 T1 = new Thread1(N);
        Thread2 T2 = new Thread2(N);
        Thread3 T3 = new Thread3(N);

        T1.start();
        T2.start();
        T3.start();
    }
}
