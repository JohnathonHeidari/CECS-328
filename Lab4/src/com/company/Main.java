package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Random random = new Random();
        questionPartA(userInput,random);

    }

    private static void questionPartA(Scanner userInput, Random random) {
        // request user enter a positive integer call it n.
        int n = grabUserNumber(userInput);
        // generate random integer between -100, 100
        int[] a = generateRandomNum(n, random);
//        int[] a ={10, 4, 2, 15, 18};
//        int[] a = {25, 3, 1, 8, 7, 2, 32};
         // request a user to enter a number between
        System.out.format("Enter a number between 1 to %d (k closest to the median): \n", n);
        int k = generateKey(a,userInput, n);
        int size = a.length-1;
        System.out.println("Generated array " + Arrays.toString(a));

        // Quick Select the median
        int indexMedian = quickSelect(a,size/2, 0, size, 0);
        // Creating the diff array
        int[] diff = differenceToMedian(a,indexMedian);
        System.out.format("%d is the median of the array. \n", a[indexMedian]);

        // finding the smallest K numbers
        int median = a[indexMedian];
        closeKnumbers(diff,k,median);
//        Arrays.toString(a);
//        System.out.println("Testing array with the sorted version: " + Arrays.toString(a));
    }

    private static int[] differenceToMedian(int[] a, int indexPiviot) {
        int[] diff = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            diff[i] = (a[i] - a[indexPiviot]);
        }
        return diff;
    }

    private static void closeKnumbers(int[] diff, int k, int median) {
        int size = diff.length - 1;
        int printIndex = quickSelect(diff,k, 0, size, 1);
        System.out.format("Closest numbers to the median. k = %d [", (k + 1));


        for (int i = 0; i <= printIndex; i++) {
            if(i < printIndex)
                System.out.format("%d, ", (diff[i] + median) );
            else {
                System.out.format("%d]\n",(diff[i] + median) );
            }
        }
    }

    private static int quickSelect(int[] a, int k, int left, int right, int flag) {
        int pivotIndex;
        if(flag == 0)
            pivotIndex = partition(a,left,right);
        else
            pivotIndex = abs_partition(a,left,right);


        if (pivotIndex == k)
            return pivotIndex;
        else if (pivotIndex < k) // pivot is on right hand side
            return quickSelect(a, k, pivotIndex + 1, right, flag);
        else // pivot is on left hand side
            return quickSelect(a, k, left, pivotIndex - 1, flag);
    }

    private static int partition(int[] a, int leftMarker, int rightMarker) {
        int pivot = a[leftMarker], pivotIndex = leftMarker; // index of the pivot

        for(int i = leftMarker + 1; i <= rightMarker; i++){

            if(pivot >= a[i]){  // if the next element is smaller than the pivot then it'll switch it to the new location
                swap(a,i,i-1);
                pivotIndex = i;
            }
            else if(pivot < a[i]){  // else if next element is larger than pivot then it'll switch to the rightside element
                swap(a,i--,rightMarker--);
            }
        }
        return pivotIndex;
    }

    private static int abs_partition(int[] a, int leftMarker, int rightMarker) {
        int pivot = Math.abs(a[leftMarker]), pivotIndex = leftMarker; // index of the pivot

        for(int i = leftMarker + 1; i <= rightMarker; i++){

            if(pivot >= Math.abs(a[i])){  // if the next element is smaller than the pivot then it'll switch it to the new location
                swap(a,i,i-1);
                pivotIndex = i;
            }
            else if(pivot < Math.abs(a[i])){  // else if next element is larger than pivot then it'll switch to the rightside element
                swap(a,i--,rightMarker--);
            }
        }
        return pivotIndex;
    }
    private static void  swap(int[] a, int left, int right){
        int temp = a[right];
        a[right] = a[left];
        a[left] = temp;
    }

    private static int generateKey(int[] a, Scanner userInput, int n) {
        while (true) {
            int key = userInput.nextInt();
            if(key <= 0){
                System.out.println("Error");
            }
            else if (a.length >= key) {
                return key - 1;
            }
            else{
                System.out.println("Error");
            }
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
}

