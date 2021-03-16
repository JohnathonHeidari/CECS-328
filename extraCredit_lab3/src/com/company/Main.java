package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        // Implement 2 functions to calculate the MSS of a given array one with running time of O(n) and one
        Random random = new Random();
        Scanner userInput = new Scanner(System.in);
        int n = grabUserNumber(userInput);
        System.out.println("Time compexity of n");
        int[] a = generateRandomNum(n, random);
//        int[] a = {-1,-2,6,-8,4,1,-3,2,-1};
//        int[] a = {12, 26, -79, 19, -93, -26, 70, 97, -21, 44};
        System.out.println(Arrays.toString(a));
        // time complexity of T(n) = n

        System.out.println(mss(a));
        // time compexity of T(n) = nlogn
        System.out.println("Time compexity of nlogn");
        System.out.println(Arrays.toString(a));
        System.out.println(mss(a,0,a.length-1));
    }

    private static int mss(int[] a) {
        int sum = 0, mss = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if(sum < 0)
                sum = 0;
            if(mss < sum)
                mss = sum;
        }
        return mss;
    }

    private static int mss(int[] a, int start, int end) {
        if(start == end)
            return a[start];
        else{
            int mid = (start + end) / 2;
            int mss_left = mss(a,start,mid);
            int mss_right = mss(a,mid+1,end);
            int mss_mid = mss_middle(a,start,end,mid);
//            System.out.println("Mss_left: " + mss_left + ". Mss_right: " + mss_right + ", mss_mid: " + mss_mid);
            return Math.max(mss_left, Math.max(mss_right,mss_mid));
        }
    }

    private static int mss_middle(int[] a, int start, int end, int mid) {
//        System.out.println("===========================================================\nmid: " + mid + ", start: " + start + ", end: " + end);
        int mxL = -101, sum = 0; // impossible value so it can allow a change.
//        System.out.println("i: " + mid + ", start: " + start );
        for (int i = mid; i >= start; i--) {
            sum += a[i];
            if(mxL < sum)
                mxL = sum;
        }

        int mxR = -101; sum = 0;
//        System.out.println("i: " + mid + ", end: " + end );
        for (int i = mid+1; i <= end; i++) {
            sum += a[i];
            if(mxR < sum)
                mxR = sum;
        }
//        System.out.println("mxL: " + mxL + ", mxR: " + mxR + "\n");
        return Math.max(Math.max(mxL,mxR), mxL+mxR);
    }

    private static int[] generateRandomNum(int n, Random random) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = (random.nextInt(100) * (random.nextBoolean() ? 1:-1));
        }
        return a;
    }


    private static int grabUserNumber(Scanner userInput) {
        int num = - 1;
        System.out.println("Enter a number for the array size: ");
        while (num < 0) {
            num = userInput.nextInt();
        }
        return num;
    }

}
