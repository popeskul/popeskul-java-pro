package org.example.hw22;

import java.util.Scanner;

public class MyMath {
    public static final int ODD = 1;
    public static final int PRIME = 2;
    public static final int PALINDROME = 3;

    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

    // Lambda expression to check if a number is odd
    public static PerformOperation isOdd() {
        return n -> n % 2 != 0;
    }

    // Lambda expression to check if a number is prime
    public static PerformOperation isPrime() {
        return n -> {
            if (n < 2) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        };
    }

    // Lambda expression to check if a number is a palindrome
    public static PerformOperation isPalindrome() {
        return n -> {
            int reversed = 0;
            int original = n;
            while (n != 0) {
                reversed = reversed * 10 + n % 10;
                n /= 10;
            }
            return original == reversed;
        };
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int operationType = in.nextInt();
            int numberToCheck = in.nextInt();
            if (operationType == MyMath.ODD) {
                System.out.println(MyMath.checker(MyMath.isOdd(), numberToCheck) ? "ODD" : "EVEN");
            } else if (operationType == MyMath.PRIME) {
                System.out.println(MyMath.checker(MyMath.isPrime(), numberToCheck) ? "PRIME" : "COMPOSITE");
            } else {
                System.out.println(MyMath.checker(MyMath.isPalindrome(), numberToCheck) ? "PALINDROME" : "NOT PALINDROME");
            }
        }
        in.close();
    }
}
