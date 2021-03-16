package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int[] a = generateArray(userInput);
        int[] b = a.clone();
        questionOne(userInput,a);
        questionTwo(userInput,b);
    }

    private static void questionTwo(Scanner userInput, int[] a) {
//        int[] a = generateArray(userInput);
        System.out.println(Arrays.toString(a));
        quick_sort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }
    private static void questionOne(Scanner userInput, int[] a) {
//        int[] a = generateArray(userInput);
        System.out.println(Arrays.toString(a));
        insertion_sort(a);
        System.out.println(Arrays.toString(a) +"\n==============================");

    }

    private static void quick_sort(int[] a, int start, int end) {
        if(start < end){
            int pivot = medianpivot(a, start, end);
            System.out.println("pivot: " + a[pivot]);
            int pivot_location = partion(a,start,end,pivot);
            quick_sort(a, start, pivot_location - 1);
            quick_sort(a, pivot_location + 1, end);
        }
    }

    private static int partion(int[] a, int start, int end, int pivot){
        swap(a, pivot, end);
        int i = (start - 1); // index of smaller element
        System.out.println("Start: " + (start - 1));
        for (int j=start; j<end; j++)
        {
            // If current element is smaller than the pivot
            if (a[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                swap(a,i,j);
            }
        }
        // swap arr[i+1] and arr[high] (or pivot)
        swap(a,i+1,end);
        System.out.println(i+1);
        return i+1;
    }

    private static int medianpivot(int[] a, int start, int end) {
        int mid = (start + end) / 2;
        // a a[start] a[mid] a[end]
        if ((a[start] > a[mid]) != (a[start] > a[end])) {
//            return a[start];
            return start;
        }
        else if ((a[mid] > a[start]) != (a[mid] > a[end])) {
//            return a[mid];
            return mid;
        }
        else {
//            return a[end];
            return end;
        }
    }

    private static void insertion_sort(int[] a) {
        int itr = a.length, k;
        for (int i = 0; i < itr; i++) {
            k = i-1;

            while (k>=0 && a[k] > a[i]) {
                swap(a,i,k);
                i=k;
                k--;
            }
        }
    }

    private static void swap(int[] a, int i, int k) {
        int temp = a[i];
        a[i] = a[k];
        a[k] = temp;
    }

    private static int[] generateArray(Scanner userInput) {
        Random random = new Random();

        HashSet<Integer> duplicates = new HashSet<>();
        System.out.println("Please enter the size of the array: ");
//        int itr = userInput.nextInt() * 1000;
        int itr = userInput.nextInt();
        if (itr == 0) {
            return new int[0];
        }
        int i = 0, value;
        int[] a = new int[itr];
        while (i < itr) {
//            value = (random.nextInt(5000) + 1) * (random.nextBoolean() ? -1:1);
            value = (random.nextInt(10) + 1) * (random.nextBoolean() ? -1:1);

            if (!duplicates.contains(value)) {
                a[i] = value;
                duplicates.add(value);
                i++;
            }
        }
        return a;
    }
}
//    private static void insertion_sort(int[] a) {
//        int itr = a.length, k, min;
//        for (int i = 0; i < itr; i++) {
//            min = a[i];
//            System.out.println("Min: " + min);
//            k = i-1;
//
//            while (k>=0 && a[k] > min)
//            {
//                a[k+1] = a[k];
//                System.out.println(a[k] + ", " + a[k+1]);
//
//                k--;
//            }
//
//            a[k+1] = min;
//        }