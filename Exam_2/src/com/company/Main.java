package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner userInput = new Scanner(System.in);
        int selection = 0;
        while (selection != -1) {
            selection = displayMenu(userInput);
            switch (selection) {
                case 1:
                    questionProblemOne(userInput, random);
                    break;
                case 2:
                    questionProblemTwo(userInput, random);
                    break;
                case -1:
                    System.out.println("Thanks for using the program");
                    System.out.println();
                    break;
                default:
                    System.out.println("Error: Invalid option");
                    System.out.println();
                    break;
            }
        }
    }
    private static void questionProblemOne(Scanner userInput, Random random) {
        int n = grabUserNumber(userInput);
        int[] a = generateArray(n, random, false); // isSort?
        System.out.println(Arrays.toString(a));
        long begin = System.nanoTime(), elapsed = 0; // (to calculate the runtime) 2 Part D
        //                                      true                     false
        String output = build_MaxHeap(a) ? "This is NOT a max-heap": "This is a max-heap";
        elapsed = System.nanoTime() - begin; // (to calculate the runtime)
        System.out.format("Time that build_MaxHeap executed: %d nanoseconds\n", elapsed); // (to calculate the runtime) nanoseconds
        System.out.println(output);
    }

    private static void questionProblemTwo(Scanner userInput, Random random) {
        int n = grabUserNumber(userInput);
        int k = random.nextInt(n); // (can't be greater than or equal to n, and not equal to n)
        int[] a = generateArray(n, random, true); // isSort?
        int[] b = generateArray(k, random, false); // isSort?
        System.out.println("Array: " + Arrays.toString(a));
        System.out.println("SubArray: " + Arrays.toString(b));
        int size = a.length-1;
        boolean status = false;
        long begin = System.nanoTime(), elapsed = 0; // (to calculate the runtime) 2 Part D
        // iterate the b array
        for (int i = 0; i <= b.length-1; i++) {
            status = binary_search(a, b[i], 0, size);
            if (!status)
                break;
        }
        elapsed = System.nanoTime() - begin; // (to calculate the runtime)
        System.out.format("Time that binarySearch executed: %d nanoseconds\n", elapsed); // (to calculate the runtime) nanoseconds
        //                            true                     false
        String output = status ? "B is a subset of A" : "B is not subset of a";
        System.out.println(output);

    }

    public static boolean binary_search(int[] a, int key, int start, int end) {
        int mid = (start + end) / 2;
        // base case(s)
        if(a[mid] == key)
            return true; // found
        else if(start > end)
            return false; // cannot find
        if(a[mid] > key)
            return binary_search(a,key,start,mid-1);
        else
            return binary_search(a,key,mid + 1,end);
    }

    /** Max_heapify and Build_Maxheap are modify version of my lab 5 assignment*/
    private static boolean max_heapify(int[] a, int size, int i){
        int max = i;
        int left = (2 * i) + 1;
        int right = (2 * i) + 2;
        if(left < size && a[left] > a[max])
            max = left;
        if(right < size && a[right] > a[max])
            max = right;
        if(max != i){
            return true; // swap require
        }
        return false; // no swap require
    }

    private static boolean build_MaxHeap(int[] a){
        int size = a.length; int start = (size/2) - 1; boolean status;
        for (int i = start; i >= 0 ; i--) {
            status = max_heapify(a,size,i); // the status of the swap require?
            if(status){
                return true;
            }
        }
        return false;
    }

    private static int[] generateArray(int n, Random random, boolean isSort) {
        HashSet<Integer> duplicates = new HashSet<>();
        if (n == 0) {
            return new int[0];
        }
        int i = 0, value;
        int[] a = new int[n];
        while (i < n) { // makes numbers distinct
            value = random.nextInt(n*10);
            if (!duplicates.contains(value)) {
                a[i] = value;
                duplicates.add(value);
                i++;
            }
        }
        if(isSort) // converts given array to be sorted
            Arrays.sort(a);
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

    public static int displayMenu(Scanner userInput) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("              -Menu-            ");
        System.out.println(" 1: Verify whether the array is a max-heap or not");
        System.out.println(" 2: Use a random array to determine if b is a subset of a");
        System.out.println("-1: Exit program");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Please enter a option: ");
        return userInput.nextInt();
    }

}
