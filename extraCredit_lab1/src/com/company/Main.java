package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int selection = 0;
        while (selection != -1) {
            selection = displayMenu(userInput);
            switch (selection) {
                case 1:
                    findPositionSplit();
                    break;
                case 2:
                    findMedianTwoArray();
                    break;
                case -1:
                    System.out.println("Thanks for using the program.");
                    break;
                default:
                    System.out.println("Error: Invalid option");
                    System.out.println();
                    break;
            }
        }
    }

    private static void findMedianTwoArray() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter the size of the array: ");
        int itr = userInput.nextInt();
        int[] a = generateArray(itr);
        int[] b = generateArray(itr);
//        int[] a = {0, 2, 10, 26, 68};
//        int[] b = {1, 11, 18, 20, 41};
//        int[] a = {3, 41, 88, 100};
//        int[] b = {5, 6, 14, 26};
//        int[] a = {5, 10};
//        int[] b = {2, 41};
        System.out.println("A: " + Arrays.toString(a));
        System.out.println("B: " + Arrays.toString(b));
        if(a.length > 1) {
            System.out.println("The median is: " + findMedianTwoArray(a, b, 0, 0, a.length - 1, b.length - 1));
        }
        else if(a.length == 1){
            System.out.println("The median is: " + ((a[0] + b[0]) / 2));
        }
        else{
            System.out.println("The median is: not found");
        }
    }

    private static int findPositionSplit(int[] a, int start, int end){
        int mid = (start + end) / 2;
//        System.out.println( "mid: " + mid + ", mid[a]: " + a[mid] +", start: " + start + ", end: " + end);
        // base case(s)
        if(a.length <= 2){
            if(a[start] == 1 || a[end] == 1)
                return a[start] == 1 ? start:end;
            else
                return -1;
        }
        else if((a[mid] == 1) & (a[mid-1] == 0)){
            return mid;
        }
        else if(start > end){
            return -1;
        }
        if(a[mid] == 0){
            return findPositionSplit(a, mid + 1, end); // Increase
        }
        else{
            return findPositionSplit(a, start, mid-1); // Decrease
        }
    }

    private static double findMedianTwoArray(int[] a, int[] b, int a_start, int b_start, int a_end, int b_end){
        if(a_start + 1 == a_end && b_start + 1 == b_end){
            return (double)(Math.max(a[a_start], b[b_start]) + Math.min(a[a_end],b[b_end])) / 2;
        }
        int mida = (a_start + a_end) / 2;
        int midb = (b_start + b_end) / 2;
        int meda = findMedianTwoArray(a,0,a.length-1);
        int medb = findMedianTwoArray(b,0,b.length-1);

        if((a.length-1) % 2 == 1){
            // median from the array(s) is between two elements e.g ( (6+14) / 2)

            if (meda < medb) {
                // median of a is greater, thus... meda 2nd element rather than the first
                if (meda != (a[mida] + a[mida + 1])) {
                    return findMedianTwoArray(a, b, mida + 1, b_start, a_end, midb);
                } else {
                    return findMedianTwoArray(a, b, mida, b_start + 1, a_end - 1, midb);
                }
            }
             else {
                // median of b is greater, thus... medb  2nd element rather than the first
                if (meda != (b[midb] + b[midb + 1])) {
                    return findMedianTwoArray(a, b, a_start, midb + 1, mida, b_end);
                } else {
                    return findMedianTwoArray(a, b, a_start + 1, midb, mida, b_end - 1);
                }
            }
        }
        else {
            // median from the array(s) is define
            if (a[meda] < b[medb]) {
                if (a[meda] != a[a_start]|| a.length == 3) {
//                    System.out.println("Yes: " + a[meda] + " , " + a[mida]);
                    return findMedianTwoArray(a, b, meda, b_start, a_end, medb);
                } else {
                    return findMedianTwoArray(a, b, meda, b_start + 1, a_end - 1, medb);
                }
            } else {
//                System.out.println("NO");
                if (a[meda] != a[a_end] || a.length == 3) {
                    return findMedianTwoArray(a, b, a_start, medb, meda, b_end);
                } else {
                    return findMedianTwoArray(a, b, a_start + 1, medb, meda, b_end - 1);
                }
            }
        }
    }
    private static int findMedianTwoArray(int[] a, int a_start, int a_end){
        int size = a.length - 1;
        if(size % 2 == 0){
            return size/2;
        }
        else {
            return (a[size/2] + a[(size/2) + 1]) / 2;
        }
    }
    private static void findPositionSplit() {
        int k;
        int[] a = generateBinaryArray();
        System.out.println("Finding the smallest index in the array.");
        System.out.println(Arrays.toString(a));
        if(a.length == 0) {
            k = -1;
        } else {
            k = findPositionSplit(a, 0, a.length - 1);
        }
        System.out.println("The first 1's binary index: " + k);
    }

    private static int[] generateArray(int itr) {
        Random random = new Random();
        HashSet<Integer> duplicates = new HashSet<>();
        if (itr == 0) {
            return new int[0];
        }
        int i = 0, value;
        int[] a = new int[itr];
        while(i < itr){
            value = random.nextInt(25);
            if(!duplicates.contains(value)){
                a[i] = value;
                duplicates.add(value);
                i++;
            }
        }
        Arrays.sort(a);
        return a;
    }

    private static int[] generateBinaryArray(){
        Random random = new Random();
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter a binary array size: ");
        int itr = userInput.nextInt();
        int k =  (random.nextInt(itr) + 1);
        if(itr==0){
            return new int[0];
        } else {
            int[] a = new int[itr];
            for (int i = 0; i < itr; i++) {
                if (i < k) {
                    a[i] = 0;
                } else {
                    a[i] = 1;
                }
            }
            return a;

        }
    }

    private static int displayMenu(Scanner userInput) {
        System.out.println("---------------------------------");
        System.out.println("              -Menu-            ");
        System.out.println(" 1: Finding Binary Array");
        System.out.println(" 2: Finding the median of two arrays");
        System.out.println("-1: Exit program");
        System.out.println("---------------------------------");
        System.out.println("Please enter a option: ");
        return userInput.nextInt();
    }
}
// test
//        System.out.println("Median: " + meda + ", ( aStart: " + a_start + ", aEnd: " + a_end + " ) , Middle: " + a[mida]);
//        System.out.println("Median: " + medb + ", ( bStart: " + b_start + ", bEnd: " + b_end+ " ) , Middle: " + midb);
//        System.out.println("Median: " + a[meda] + ", ( aStart: " + a_start + ", aEnd: " + a_end + " ) , Middle: " + a[mida]);
//
//            System.out.println("Max: (" + a[a_start] + ", " + b[b_start] + ") Min: (" + a[a_end] + ", " + b[b_end] + ") ;" );

//                ->
//    int[] a [5, 6, 14, 26]
//                <-
//    int[] b [3, 41, 88, 100]
//    Median: 6, aStart: 0, aEnd: 3
//    Median: 41, bStart: 0, bEnd: 3
//    Max : 41 check a
//                   -
//    int[] a [5, 6, 14, 26]
//                -
//    int[] b [3, 41, 88, 100]
//    41 > 14, check next element
//    aStart: 3 aEnd: 3
//    --------------------------------------
//                   -
//    int[] a [0, 2, 10, 26, 68], a[meda] = 10             1. Determine the max median
//                    -
//    int[] b [1, 11, 18, 20, 41], b[medb]= 18
//    start_a = 0 , end_a = 4, a[mid] = 10
//    start_b = 0, end_b = 4, b[mid] = 18
//    if(meda < medb) the max med is b...         so  markers
//                   ->      <-
//    int[] a [0, 2, 10, 26, 68], a[meda]= 10              2. potential median?
//             ->     <-
//    int[] b [1, 11, 18, 20, 41], b[medb] = 18            2. potential median?
//
//    if(meda < medb) true, so..
//    start_a = 0 + meda =  2, end_a = 4 - 1 = 3, a[mid] = 10
//    start_b = 0 + 1 = 1, end_b = medb = 2, b[mid] = 18
//                    -> <-
//    int[] a [0, 2, 10, 26, 68], a[meda] = 26             3. Decrease end
//                ->  <-                                          | both cursor meet up
//    int[] b [1, 11, 18, 20, 41], b[medb] = 11            3. increase start
//
//    base condition ----
//    if(start_a + 1 == start_b) true, so..             max(start_a, start_b), min(end_a,end_b)
//    return [max(10,11) + min(18,26)] / 2 = 14.5
//     can I do this,
//
//
//    --------------------------------------
//              -
//    a[] = {5, 6, 14, 26} , m1 = 6+14 = 10
//              -
//    b[] = {3, 41, 88, 100}  , m2 = 41+88 = 64.5
//    if(m1<m2)
//              -> <-
//    a[] = {5, 6, 14, 26} , m1 = 6+14 = 10
//           -> <-
//    b[] = {3, 41, 88, 100}  , m2 = 41+88 = 64.5
//    return [max(6,3) + min(14,41)] / 2 = 20