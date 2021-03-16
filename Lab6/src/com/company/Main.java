package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Please enter the size of the array: ");
        int n = grabUserNumber(userInput);
//        double[] a = generateRandomNum(n,random);
//        double[] a = {-34,49,-58,76,29,-71,-54,63};
        double[] a ={2, -3, 1, 4, -6, 10, -12, 5.2, 3.6, -8};
//        double[] a = {1.2, -2, 2.5, 1};
//        [-2.0, -4.0, -1.0, 0.0] left
//        [-8.0, -2.0, -12.0, 0.0] right
        System.out.println("Generate Array: " + Arrays.toString(a));
         double answer = mpss(a,0,a.length-1);

//         System.out.println("Minimum subsequence sum: " + answer);
        System.out.format("Minimum subsequence sum: %2f", answer);
    }

    private static double mpss(double[] a, int start, int end) {
        if(start == end){ // Base Case
            return a[start] > 0 ? a[start]: 0; // is it negative?
        }
        else if (a.length-1 > 1){
//            System.out.println("Start: " + start + ", end: " + end + "---");
            int mid = (start + end) / 2;
            double mpss_left = mpss(a, start, mid);
            double mpss_right = mpss(a, mid + 1, end);
            double mpss_mid = mpss_middle(a, start, end, mid);
            if(mpss_left <= 0)
                mpss_left = Double.POSITIVE_INFINITY; // is it negative?
            if(mpss_right <= 0)
                mpss_right = Double.POSITIVE_INFINITY; // is it negative?
//            System.out.println("---------------------Mss_left: " + mpss_left + ". Mss_right: " + mpss_right + ", mss_mid: " + mpss_mid + " ---------------------");
            return Math.min(mpss_left, Math.min(mpss_right, mpss_mid));
        }
        else{ // Base Case
            return a[0] > 0 ? a[0] : 0; // is it negative?
        }
    }

    private static double mpss_middle(double[] a, int start, int end, int mid) {
        if(mid==0) // base case is it small?
            return 0;
        mid = (end-start) / 2 + 1; // creating half array
        double[] S_L = new double[mid], S_R = new double[mid];
//        System.out.println("\nCreating S_L: ");
        int counter = 0;
        for (int i = 0; i < S_L.length; i++) {
            S_L[counter] = mpssConverter(a,i,start, end,false);
            counter++;
        }
        counter=0;
//        System.out.println("Left: " + Arrays.toString(S_L));
        quick_sort(S_L,0,S_L.length-1,false);  // ascending order: [-1.0, -2.0, -2.0, -4.0, -6.0]
//        System.out.println("ascending order: " + Arrays.toString(S_L));

//        System.out.println("\nCreating S_R: ");
        for (int i = 0; i < S_R.length; i++) {
            S_R[counter] = mpssConverter(a,i,start, end, true);
            counter++;
        }
//        System.out.println("Right: " + Arrays.toString(S_R));
        quick_sort(S_R,0,S_R.length-1,true); // descending order: [0.8000000000000007, -2.0, -8.0, -4.4, -12.0]
//        System.out.println("descending order: " + Arrays.toString(S_R) + "\n");


        double s_min = Double.POSITIVE_INFINITY, sum;
        int i = 0, j = 0; // define two markers
        while(i != S_L.length-1 && j != S_R.length-1) {
            sum = S_L[i] + S_R[j];
            if(sum <= 0) {
                // increment the  ascending array.
                i++;
            }
            else if(sum < s_min) {
                // assign new value of smin.
                s_min = sum;
                // increment the descending array.
                j++;
            }
            else{
                // if sum > s_min
                // increment the descending array.
                j++;
            }
        }
        if(S_L[i] < 0){
            s_min = S_R[j] > 0 ? S_R[j] : s_min; // is it negative?
        }
        else if (S_R[j] < 0){
            s_min = S_L[i] > 0 ? S_L[i] : s_min; // is it negative?
        }
        return s_min;
    }

    private static double mpssConverter(double[] a, int j, int start, int end, boolean flag) {
        int mid = (start+end)/2;
//        System.out.println("===========================================================\nmid: " + mid + ", start: " + start + ", end: " + end);
        double min = 101, sum = 0; // impossible value so it can allow a change.
        // S_R
        if(flag) {
            for (int i = (mid) + j; i <= end; i++) {
                sum += a[i];
                if (min > sum)
                    min = sum;
            }
            return (double) min;
        }

        // S_L
        for (int i = mid - j; i <= mid; i++) {
            sum += a[i];
            if(min > sum)
                min = sum;
        }
        return (double) min;
    }

    private static double[] generateRandomNum(int n, Random random) {
        double[] a = new double[n];
        for (int i = 0; i < n; i++) {
            random.nextDouble();
            a[i] = ((double)random.nextInt(20) * (random.nextBoolean() ? 1:-1));
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
//    [-1.0, -2.0, -2.0, -4.0, -6.0]
//    [-6.0, -4.0, -2.0, -2.0, -1.0]
    private static void quick_sort(double[] a, int start, int end, boolean reverse) {
        if(reverse){
            if (start < end) {
                int pivot_location = partition_reverse(a, start, end);
                quick_sort(a, start, pivot_location - 1, reverse);
                quick_sort(a, pivot_location + 1, end, reverse);
            }
        }
        else {
            if (start < end) {
                int pivot_location = partition(a, start, end);
                quick_sort(a, start, pivot_location - 1, reverse);
                quick_sort(a, pivot_location + 1, end, reverse);
            }
        }
    }
//     descending
    private static int partition_reverse(double[] a, int leftMarker, int rightMarker) {
        double pivot = a[leftMarker], pivotIndex = leftMarker; // index of the pivot

        for(int i = leftMarker + 1; i <= rightMarker; i++){

            if(pivot < a[i]){  // if the next element is larger than the pivot then it'll switch it to the new location
                swap(a,i,i-1);
                pivotIndex = i;
            }
            else if(pivot >= a[i]){  // else if next element is smaller than pivot then it'll switch to the rightside element
                swap(a,i--,rightMarker--);
            }
        }
        return (int)pivotIndex;
    }
//    ascending
    private static int partition(double[] a, int leftMarker, int rightMarker) {
        double pivot = a[leftMarker], pivotIndex = leftMarker; // index of the pivot

        for(int i = leftMarker + 1; i <= rightMarker; i++){

            if(pivot >= a[i]){  // if the next element is smaller than the pivot then it'll switch it to the new location
                swap(a,i,i-1);
                pivotIndex = i;
            }
            else if(pivot < a[i]){  // else if next element is larger than pivot then it'll switch to the rightside element
                swap(a,i--,rightMarker--);
            }
        }
        return (int)pivotIndex;
    }
    private static void swap(double[] a, int i, int k) {
        double temp = a[i];
        a[i] = a[k];
        a[k] = temp;
    }
}
