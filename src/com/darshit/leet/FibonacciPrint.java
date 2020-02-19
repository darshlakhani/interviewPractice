package com.darshit.leet;

public class FibonacciPrint {

    public static void main(String arg[]) {

        System.out.println("Iterative: ");
        iterativeFibPrint(5);
        System.out.println("\n Recurse: ");
        for (int i=0; i<5; i++) {
            System.out.print(recurseFibPrint(i) + " ");
        }

        System.out.println("\n Memoized: ");
        int[] mem = {-1, -1, -1, -1, -1};
        for (int i=0; i<5; i++) {
            System.out.print(memoizedFibPrint(i, mem) + " ");
        }

        System.out.println("\n Sum of Fib:" );
        fibSumIterative(5);
    }

    public static void iterativeFibPrint(int n) {
        int sum = 0;
        int prev = 1;
        int next =0;
// if you want to start from 1 then prev = 0, next = 1 and sum = 1;
        for(int i=0; i<n; i++) {
            System.out.print(sum + " ");
            sum = prev + next;
            prev = next;
            next = sum;

        }
    }

    public static int recurseFibPrint(int n) {
        if (n == 1) {
            return 1;
        }
        if (n > 1) {
            int r = recurseFibPrint(n - 1) + recurseFibPrint(n - 2);
            return r;
        }
        return 0;
    }

    public static int memoizedFibPrint(int n, int[] mem) {
        if (mem[n] > -1) {
            return mem[n];
        }
        if (n == 0) {
            mem[n] = 0;
        }
        if (n == 1) {
            mem[n] = 1;
        }
        if (n>1) {
            mem[n] = memoizedFibPrint(n - 1, mem) + memoizedFibPrint(n - 2, mem);
        }
        return mem[n];
    }

    public static void fibSumIterative(int n) {
        int sum = 1;
        int prev = 0;
        int next = 1;
// since 0 is at 0th index.
        int totalSum = 0;
        for (int i=0; i<n; i++) {
            totalSum += sum;
            sum = prev + next;
            prev = next;
            next = sum;

        }
        System.out.println("Sum of series: " + totalSum);
    }
}
