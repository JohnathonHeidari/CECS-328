package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Random random = new Random();
        questionPartA(userInput,random);
        questionPartB(userInput,random);
    }

    private static void questionPartA(Scanner userInput, Random random) {
        System.out.println("Question part A");
        // request user enter a positive integer call it n.
        int n = grabUserNumber(userInput);
        // generate random integer between -100, 100
        int[] a = generateRandomNum(n, random);
//        int[] a = {55,10,5,2,-1,15,57,20,26,100,200};
//        int[] a = {87, 21, 0, -1, -22, 10, 17, 11, -3, 10};
        System.out.println("Original Array: " + Arrays.toString(a));
        heap_sort(a);
        System.out.println("Call heap sort to user desire size");
        System.out.println("Sorted Array: " + Arrays.toString(a) + "\nCalculate the average runtime for heap_sort and selection_sort");

        // Determine the average
        // -running time of heap_sort function for n=1000, and 100 repetitions.
        n = 1000;
        final int itr = 100;
        long start = System.nanoTime(), elapsed, average;
        for (int i = 0; i < itr; i++) {
            a = generateRandomNum(n, random);
            heap_sort(a);
        }
        elapsed = System.nanoTime() - start;
        average = elapsed / itr;
        System.out.format("Average time heap_sort executed: %d nanoseconds\n", average);

        // Determine the average-running time of selection_sort
        start = System.nanoTime();
        for (int i = 0; i < itr; i++) {
            a = generateRandomNum(n, random);
            selection_sort(a);
        }

        // Compare the average-running time of heap_sort and selection sort
        elapsed = System.nanoTime() - start;
        average = elapsed / itr;
        System.out.format("Average time selection_sort executed: %d nanoseconds\n", average);
        System.out.println("----------------------------\nPart B");
    }

    private static void questionPartB(Scanner userInput, Random random) {
        System.out.println("Sort the numbers using the array size of 10.");
        int n = 10;
        // generate random integer between -100, 100
        int[] a = generateRandomNum(n, random);
        System.out.println("Original Array: " + Arrays.toString(a));
        heap_sort(a);
        System.out.println("Sorted Array: " + Arrays.toString(a));
    }

    private static void max_heapify(int[] a, int size, int i){
        int max = i;
        int left = (2 * i) + 1;
        int right = (2 * i) + 2;
        if(left < size && a[left] > a[max])
            max = left;
        if(right < size && a[right] > a[max])
            max = right;
        if(max != i){
            swap(a,i,max);
            max_heapify(a,size,max);
        }
    }
    private static void build_MaxHeap(int[] a){
        int size = a.length;
        int start = (size/2) - 1;
        for (int i = start; i >= 0 ; i--) {
            max_heapify(a,size,i);
        }
    }

    private static void heap_sort(int[] a){
        int size = a.length - 1;
        build_MaxHeap(a);
        for (int i = size; i > 0 ; i--) {
            swap(a,i,0);
            max_heapify(a,i, 0);
        }
    }
    private static void selection_sort(int[] a ){
        int size = a.length, min;
        for (int i = 0; i < size; i++) {
            min = i;
            for (int j = i+1; j < size; j++) {
                if(a[min] > a[j])
                    min = j;
            }
            swap(a,i,min);
        }

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

    private static void  swap(int[] a, int left, int right){
        int temp = a[right];
        a[right] = a[left];
        a[left] = temp;
    }
}
