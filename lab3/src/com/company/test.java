package com.company;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        // [-31, 34, 26, 18, -81]

        // [10, 6, 8, 9, 0, 2, -5, -1]
        // [-1, -5, 0, 8, 6, 9, 10, 2]
//        int[] a = {2,6,8,-5,-1,0,9,10};
        int[] a = {4 ,2 ,0 ,10 ,1 ,6};
        System.out.println(Arrays.toString(a));
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));

        partition(a,0,a.length-1);
        System.out.println(Arrays.toString(a));

    }


    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], pivotloc = low;
        for (int i = low; i <= high; i++) {
            // inserting elements of less value
            // to the left of the pivot location
            if (arr[i] < pivot) {
                int temp = arr[i];
                arr[i] = arr[pivotloc];
                arr[pivotloc] = temp;
                pivotloc++;
            }
        }
        int temp = arr[high];
        arr[high] = arr[pivotloc];
        arr[pivotloc] = temp;

        return pivotloc;
    }

}