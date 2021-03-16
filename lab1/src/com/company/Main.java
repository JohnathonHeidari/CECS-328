package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        // Request the user to enter a positive seed integer.
        Random seed = getSeed(userInput);
        labPartA(seed, userInput);
        labPartB(seed, userInput);
    }

    /**
     * @param userInput a Scanner
     * @return seed (random number) to generate similar results for testing.
     */
    private static Random getSeed(Scanner userInput) {
        System.out.println("Please enter a seed number: ");
        int n = 0;
        while (n <= 0) {
            n = userInput.nextInt();
            if (n <= 0)
                System.out.println("Invalid number: ");
        }
        Random seed = new Random();
        seed.setSeed(n);
        return seed;
    }

    private static ArrayList<Integer> generateRandomList(int n, Random seed) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            arrayList.add((seed.nextInt(1000) + 1) * (seed.nextBoolean() ? -1 : 1));
        }
        return arrayList;
    }

    private static int getNumber(int n, Scanner userInput) {
        System.out.println("Please enter a positive number: ");
        while (n <= 0) {
            n = userInput.nextInt();
            if (n <= 0)
                System.out.println("Invalid number: ");
        }
        n *= Math.pow(10, 5);
        return n;
    }

    public static boolean linearSearch(ArrayList<Integer> a, int key) {
        for (int value : a) {
            if (value == key) {
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearch(ArrayList<Integer> a, int key) {

        int mid = 0, left = 0, right = a.size() - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (a.get(mid) == key)
                return true;
            else if (a.get(mid) > key)
                left = mid + 1;
            else if (a.get(mid) < key)
                right = mid - 1;
        }
        return false;
    }


    private static void labPartA(Random seed, Scanner userInput) {
        // Request the user to enter a number of element.
        int n = getNumber(0, userInput);
        final int CONSTANT = 100;
        // Generate the array bounded {-1000,1000}
        System.out.println("------------------------------------------\nPart A");
        ArrayList<Integer> a = generateRandomList(n, seed);
        long start = System.nanoTime(), elapsed = 0, average = 0;
        for (int i = 0; i < 100; i++) {
            int key = ((seed.nextInt(1000) + 1) * (seed.nextBoolean() ? -1 : 1));
            linearSearch(a, key);
        }
        elapsed = System.nanoTime() - start;
        average = elapsed / CONSTANT;
        System.out.format("Average time linearSearch executed: %d nanoseconds\n", average);

        // Binary Search (sort algorithm for the binary only)
        Collections.sort(a);
        start = System.nanoTime();
        for (int i = 0; i < CONSTANT; i++) {
            int key = ((seed.nextInt(1000) + 1) * (seed.nextBoolean() ? -1 : 1));
            binarySearch(a, key);
        }
        elapsed = System.nanoTime() - start;
        average = elapsed / CONSTANT;
        System.out.format("Average time binarySearch executed: %d nanoseconds\n", average);

    }

    private static void labPartB(Random seed, Scanner userInput) {
        System.out.println("------------------------------------------\nPart B");
        // Request the user to enter a number of element.
        int n = getNumber(0, userInput);
        // Generate the array bounded {-1000,1000}
        ArrayList<Integer> a = generateRandomList(n, seed);
        int key = 5000;
        long start = System.nanoTime(), elapsed = 0;
        linearSearch(a, key);
        elapsed = System.nanoTime() - start;
        System.out.format("Worse time linearSearch executed: %d nanoseconds\n", elapsed);

        Collections.sort(a);
        start = System.nanoTime();
        binarySearch(a, key);
        elapsed = System.nanoTime() - start;
        System.out.format("Worse time binarySearch executed: %d nanoseconds\n", elapsed);

        // using the average time complexity from the binarySearch elapsed

        double Log2n = (Math.log(n) / Math.log(2)); // log2(inputSize)
        elapsed = (int) (elapsed / Log2n);
        System.out.format("Single line for the binarySearch executed: %d nanoseconds\n", elapsed);

        System.out.println("------------ n = 10^15 || 1 line = " + elapsed + " of nanoseconds  ------------");
        long sum = (long)(Math.pow(10, 15)); // input size n = 10^15
        Log2n = Math.log(sum) / Math.log(2);
        // Linear search calculating worse case
        System.out.format("Estimate the worse case for linearSearch executed: %d nanoseconds\n", (sum * elapsed));
        // Binary search calculating worse case
        System.out.format("Estimate the worse case for binarySearch executed: %d nanoseconds\n", ((int)(Log2n * elapsed)));

    }
}
