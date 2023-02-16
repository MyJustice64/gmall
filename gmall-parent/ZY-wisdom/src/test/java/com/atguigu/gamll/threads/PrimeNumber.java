package com.atguigu.gamll.threads;

public class PrimeNumber {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 2; i <= 100; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.println(i);
                sum += i;
            }
        }
        System.out.println("The sum of all prime numbers from 1 to 100 is: " + sum);
    }
}