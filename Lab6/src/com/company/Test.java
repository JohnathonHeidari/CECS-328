package com.company;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        double[] a = {-2, -4, -1, -2, -6}; // [-6.0, -4.0, -2.0, -2.0, -1.0]
        quick_sort(a,0,a.length-1, true);
        System.out.println(Arrays.toString(a));
    }
    private static void quick_sort(double[] a, int start, int end, boolean reverse) {
        if(reverse){
            if (start < end) {
                int pivot_location = partition_reverse(a, start, end);
                quick_sort(a, start, pivot_location - 1, reverse);
                quick_sort(a, pivot_location + 1, end, reverse);
                System.out.println("k");
            }
        }
        else {
            if (start < end) {
                int pivot_location = partition(a, start, end);
                quick_sort(a, start, pivot_location - 1, reverse);
                quick_sort(a, pivot_location + 1, end, reverse);
                System.out.println("t");
            }
        }
    }
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