package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Stuff {

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
        System.out.println(Arrays.toString(a));
//        int start = 0, end = a.length-1, mid = (start+end) / 2;
//        mpss_middle(a,start,end,mid);
        double answer = mpss(a,0,a.length-1);
        System.out.println("Answer: " + answer);
    }

    private static double mpss(double[] a, int start, int end) {

        if(start == end){
//            System.out.println("DDDDDDD: " + a[start]);
            return a[start] > 0 ? a[start]: 0; // is it negative?
        }
        else if (a.length-1 > 1){
            System.out.println("Start: " + start + ", end: " + end + "-----------------------------------------------------------");
            int mid = (start + end) / 2;
            double mpss_left = mpss(a, start, mid);
            double mpss_right = mpss(a, mid + 1, end);
            double mpss_mid = mpss_middle(a, start, end, mid);
            if(mpss_left <= 0)
                mpss_left = Double.MAX_VALUE; // is it negative?
            if(mpss_mid <= 0)
                mpss_mid = Double.MAX_VALUE; // is it negative?
            if(mpss_right <= 0)
                mpss_right = Double.MAX_VALUE; // is it negative?
//            System.out.println("---------------------Mss_left: " + mpss_left + ". Mss_right: " + mpss_right + ", mss_mid: " + mpss_mid + " ---------------------");

            return Math.min(mpss_left, Math.min(mpss_right, mpss_mid));
        }
        else{
            return a[0] > 0 ? a[0] : 0; // is it negative?
        }
    }

    private static double mpss_middle(double[] a, int start, int end, int mid) {
//        System.out.println("===========================================================\nmid: " + mid + ", start: " + start + ", end: " + end);
        int mxL = 101, sum = 0; // impossible value so it can allow a change.
//        System.out.println("i: " + mid + ", start: " + start );
        for (int i = mid; i >= start; i--) {
            sum += a[i];
            if(mxL > sum)
                mxL = sum;
        }

        int mxR = 101; sum = 0;
//        System.out.println("i: " + mid + ", end: " + end );
        for (int i = mid+1; i <= end; i++) {
            sum += a[i];
            if(mxR > sum)
                mxR = sum;
        }
        System.out.println("mxL: " + mxL + ", mxR: " + mxR + "\n");
        return Math.max(Math.min(mxL,mxR), mxL+mxR);
    }

    private static double mpssConverter(double[] a, int j, int start, int end, boolean flag) {
        int mid = (start+end)/2;
//        System.out.println("===========================================================\nmid: " + mid + ", start: " + start + ", end: " + end);
        double min = 101, sum = 0; // impossible value so it can allow a change.
//        System.out.println("i: " + mid + ", start: " + start );
        // S_L
        if(flag) {
            for (int i = (mid) + j; i <= end; i++) {
//                System.out.println("i: " + i + ", j: " + j + ", a[i]: " + a[i] + ", end: " + end);
                sum += a[i];
//                System.out.println("i: " + i  + "j: " + j + ", a[i]: " + a[i] + ", end: " + end + ", sum: " + sum);

                if (min > sum)
                    min = sum;
            }
            return (double) min;
        }

//        System.out.println("i: " + mid + ", start: " + start + ", j: " + j);
        // S_L
        for (int i = mid - j; i <= mid; i++) {
            sum += a[i];
//            System.out.println("i: " + i  + "j: " + j + ", a[i]: " + a[i] + ", start: " + start + ", sum: " + sum);
            if(min > sum)
                min = sum;
        }
//        System.out.println("mxL: " + mxL + ", mxR: " + mxR + "\n");
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
    private static void max_heapify(double[] a, int size, int i){
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

    private static void min_heapify(double[] a, int size, int i){
        int min = i;
        int left = (2 * i) + 1;
        int right = (2 * i) + 2;
        if(left < size && a[left] < a[min])
            min = left;
        if(right < size && a[right] < a[min])
            min = right;
        if(min != i){
            swap(a,i,min);
            max_heapify(a,size,min);
        }
    }
    private static void build_MaxHeap(double[] a, boolean flag){
        int size = a.length;
        int start = (size/2) - 1;
        for (int i = start; i >= 0 ; i--) {
            if(flag)
                max_heapify(a,size,i);
            min_heapify(a,size,i);

        }
    }

    private static void heap_sort(double[] a, boolean flag){
        int size = a.length - 1;
        build_MaxHeap(a, flag);
        for (int i = size; i > 0 ; i--) {
            swap(a,i,0);
            if(flag)
                max_heapify(a,i, 0);
            min_heapify(a,i,0);
        }
    }
    //    private static void quick_sort(double[] a, int start, int end) {
//        if(start < end){
//            int pivot = medianpivot(a, start, end);
//            int pivot_location = partion(a,start,end,pivot);
//            quick_sort(a, start, pivot_location - 1);
//            quick_sort(a, pivot_location + 1, end);
//        }
//    }
//    private static int partion(double[] a, int start, int end, int pivot){
//        swap(a, pivot, end);
//        int i = (start - 1); // index of smaller element
//        for (int j=start; j<end; j++)
//        {
//            // If current element is smaller than the pivot
//            if (a[j] < pivot)
//            {
//                i++;
//
//                // swap arr[i] and arr[j]
//                swap(a,i,j);
//            }
//        }
//        // swap arr[i+1] and arr[high] (or pivot)
//        swap(a,i+1,end);
//        return i+1;
//    }
//
//    private static int medianpivot(double[] a, int start, int end) {
//        int mid = (start + end) / 2;
//        // a a[start] a[mid] a[end]
//        if ((a[start] > a[mid]) != (a[start] > a[end])) {
////            return a[start];
//            return start;
//        }
//        else if ((a[mid] > a[start]) != (a[mid] > a[end])) {
////            return a[mid];
//            return mid;
//        }
//        else {
////            return a[end];
//            return end;
//        }
//    }
    private static void swap(double[] a, int i, int k) {
        double temp = a[i];
        a[i] = a[k];
        a[k] = temp;
    }
}


//    private static double mpss_middle(double[] a, int start, int end, int mid) {
//        if(mid==0)
//            return 0;
//        mid = (end-start) /2 + 1;
//        System.out.println((end-start)/2 + " MID: " + (mid) + " start: " + start + ", end: " + end) ;
//        double[] S_L = new double[mid], S_R = new double[mid];
//        System.out.println(Arrays.toString(S_L));
//        System.out.println(Arrays.toString(S_R));
//
//        System.out.println("\nCreating S_L: ");
//        int counter = 0;
//        for (int i = 0; i < S_L.length-1; i++) {
//            S_L[counter] = mpssConverter(a,i,mid,false);
//            counter++;
//        }
//        counter=0;
//        System.out.println("Left: " + Arrays.toString(S_L));
//        heap_sort(S_L, false);
//        System.out.println("ascending order: " + Arrays.toString(S_L));
//
//        System.out.println("\nCreating S_R: ");
//        for (int i = 0; i < S_R.length-1; i++) {
//            S_R[counter] = mpssConverter(a,i,mid,true);
//            counter++;
//        }
//        System.out.println("Right: " + Arrays.toString(S_R));
//        heap_sort(S_R, true);
//        System.out.println("descending order: " + Arrays.toString(S_R) + "\n");
//
//        // define two markers
////        int i = 0, j = S_R.length-1; // i = S_L, J = S_R
//        double s_min = Double.POSITIVE_INFINITY, sum;
//        int i = 0, j = 0;
//        while(i != S_L.length-1 && j != S_R.length-1) {
//            sum = S_L[i] + S_R[j];
//
//            // check if the sum is less than smin, then update smin.
//            if(sum <= 0) {
//
//                // increment the marker for ascending array.
//                i++;
//            } else if(sum < s_min) {
//
//                // assign new value of smin.
//                s_min = sum;
//                // increment marker for descending order array.
//                j++;
//
//            }
//        }
//        return s_min;
////        while(i <= mid && j <= high) {
////        while (j != i) {
////            sum = S_L[i] + S_R[j];
////            System.out.println("i: " + i +  ", j: " + j +", sum: " + sum );
////             if (sum < s_min) {
////                 s_min = sum;
////                i++;
////            }
////            else
////                j--;
////        }
////        while (j != (S_R.length-1) && i != (S_L.length-1)) {
////            sum = S_L[i] + S_R[j];
////            System.out.println("i: " + i +  ", j: " + j +", sum: " + sum );
////            if (sum <= 0)
////                i++;
////            else if (sum < s_min) {
////                s_min = sum;
////                j++;
////            }
////            else
////                j++;
////        }
////        System.out.println("\n sum:  "+ sum + " i:" + S_L[i] + ", j: " + S_R[j]);
////        System.out.println(S_R[j] > 0 ? S_R[j] : 0);
////
////        if(S_L[i] < 0){
////            return S_R[j] > 0 ? S_R[j] : 0;
////        }
////        else if(S_R[i] < 0){
////            System.out.println("K0");
////            return S_L[i] > 0 ? S_L[i] : 0;
////        }
////        return 0;
//    }
//
//    private static double mpssConverter(double[] a, int start, int mid, boolean flag) {
////        System.out.println("===========================================================\nmid: " + mid + ", start: " + start + ", end: " + end);
//        double min = 101, sum = 0; // impossible value so it can allow a change.
////        System.out.println("i: " + mid + ", start: " + start );
//        if(flag) {
//            for (int i = start+mid; i < a.length; i++) {
//                sum += a[i];
//                if (min > sum)
//                    min = sum;
//            }
//            return (double) min;
//        }
//
////        System.out.println("i: " + mid + ", end: " + end );
//        for (int i = start; i <= mid; i++) {
//            sum += a[i];
//            if(min > sum)
//                min = sum;
//        }
////        System.out.println("mxL: " + mxL + ", mxR: " + mxR + "\n");
//        return (double) min;
//    }