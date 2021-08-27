package com.navinya;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A program to find the max cheese that a mouse can eat
 * Problem Statement:
 * There are blocks of cheese of different weights placed in a line. A mouse would like to eat the cheese, but with some rules. The mouse can’t eat two consecutive cheese blocks. The mouse would like to eat maximum cheese from the line of cheese blocks. Weight of each cheese block in Kg is given as a[i] in an integer array.
 *
 * Input:
 * The first line of input contains an integer T denoting the number of test cases. Each test case contains an integer n which denotes the number of elements in the array a[]. Next line contains space separated n elements in the array a[].
 *
 * Output:
 * Print an integer which denotes the maximum amount of cheese that the mouse can have.
 */
public class MaxCheeseCalc {

    public static void main(String[] args) {
        // code for reading basic inputs
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(reader.readLine().trim());
            MaxCheeseCalc maxCheeseCalc = new MaxCheeseCalc();
            maxCheeseCalc.validate(T, 1, 200);
            for(int i =0 ; i < T; i++) {
                int n = Integer.parseInt(reader.readLine().trim());
                maxCheeseCalc.validate(n, 1, 1000);
                int []a = new int[n];
                String elements = reader.readLine();
                String []arrElements = elements.split("\\s+");
                for(int j=0; j < n; j++) {
                    a[j] = Integer.parseInt(arrElements[j]);
                    maxCheeseCalc.validate(a[j], 1, 10000);
                }
                int maxCheese = maxCheeseCalc.findMaxCheese(a, n);
                System.out.println(maxCheese);
            }
        } catch (Exception ex) {
            System.out.println("Exception occurred, message = " + ex.getMessage());
        }

    }

    private void validate(int num, int start, int end) {
        if(num < start || num > end) {
            throw new RuntimeException("Failed validation. Constraint: " + start + " <= " + num + " <=" + end);
        }
    }

    /**
     * Algorithm :
     * 1. If there are no elements in an array, return 0;
     * 2. If there is only one element in an array, return that element
     * 3. If there are two elements in an array, return max of two
     * 4. Initialize values sum1 to a[0] & sum2 to max of a[0],a[1]
     * 5. Initialize maxCheese to 0.
     * 6. Update maxCheese to max of sum1 + a[i], sum2.
     * 7. Update sum1 to sum2 to sum2 to maxCheese
     * @param a
     * @return maxCheese
     */
    public int findMaxCheese(int[] a, int n) {
        if(a.length == 0) {
            return 0;
        }
        if(a.length == 1) {
            return a[0];
        }

        if(a.length == 2) {
            return Math.max(a[0], a[1]);
        }

        int sum1 = a[0], sum2 = Math.max(a[0], a[1]);
        int maxCheese = 0;
        for(int i=2; i < n; i++) {
            // Check if adding the current element
            maxCheese = Math.max(sum1 + a[i], sum2);
            sum1 = sum2;
            sum2 = maxCheese;
        }
        return maxCheese;
    }
}
