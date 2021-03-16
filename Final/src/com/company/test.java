package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class test {
    public static void main(String[] args) {
//        int[] b = {4, 2, 5,1 ,6, 8, 3 ,2, 10 };
//        ArrayList<Integer> a = new ArrayList<>();
//        for (int i = 0; i < b.length; i++) {
//            a.add(b[i]);
//        }
//        System.out.println(a);
//        build_MinHeap(a);
//        System.out.println(a);
//        a.remove(0);
//
//        min_heapify(a,a.size(),3);
//        System.out.println(a);
        int[] a = {1,2,3};
        Test(a, 0, a.length); // ****************** Calling Test for the 1st time ******************
    }

    private static int Test(int[] a, int start, int end) { // Test is a function with 3 inputs. Array a, start index and end index.

        int n = end - start; // Size of part of array a.
        if (n <= 1) {
            return a[n];
        }
        else{
            int newEnd = start + n / 6;
            int newEnd2 = newEnd + 2 * n / 6;
            int Sol1 = Test(a, start, newEnd);
            int Sol2 = Test(a, newEnd + 1, newEnd2);
            int Sol3 = Test(a, newEnd2 + 1, end);
            int CombineSol = Combine(a, start, newEnd, end); // Combine is a function with runtime of T(n) = O(n)
            return Math.min(Sol1,Math.min(Sol2,Math.min(Sol3,CombineSol))); // Return min of answers.
        }
    }

    private static int Combine(int[] a, int start, int newEnd, int end) {

        return 0;
    }
//
//    private static void min_heapify(ArrayList<Integer> a, int size, int i){
//        int min = i;
//        int left = (2 * i) + 1;
//        int right = (2 * i) + 2;
//        if(left < size && a.get(left) < a.get(min))
//            min = left;
//        if(right < size && a.get(right) < a.get(min))
//            min = right;
//        if(min != i){
////            Collections.swap(a,i,min);
//            swap(a,i,min);
//            min_heapify(a,size,min);
//        }
//    }
//    private static void build_MinHeap(ArrayList<Integer> a){
//        int size = a.size();
//        int start = (size/2) - 1;
//        for (int i = start; i >= 0 ; i--) {
//            min_heapify(a,size,i);
//        }
//    }
//    private static int removeRoot(ArrayList<Integer> a){
//        int value = a.get(0);
//        a.remove(0);
//        return value;
//    }
//    private static void  swap(ArrayList<Integer> a, int left, int right){
//        int temp = a.get(right);
//        a.set(right, a.get(left));
//        a.set(left,temp);
//    }
//    [1, 1, 3, 2, 6, 8, 5, 4, 10]
//        private static void min_heapify(int[] a, int size, int i){
//        int min = i;
//        int left = (2 * i) + 1;
//        int right = (2 * i) + 2;
//        if(left < size && a[left] < a[min])
//            min = left;
//        if(right < size && a[right] < a[min])
//            min = right;
//        if(min != i){
//            swap(a,i,min);
//            min_heapify(a,size,min);
//        }
//    }
//    private static void build_MinHeap(int[] a){
//        int size = a.length;
//        int start = (size/2) - 1;
//        for (int i = start; i >= 0 ; i--) {
//            min_heapify(a,size,i);
//        }
//    }
//
//    private static void  swap(int[] a, int left, int right){
//        int temp = a[right];
//        a[right] = a[left];
//        a[left] = temp;
//    }
}
