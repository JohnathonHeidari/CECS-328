package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Random random = new Random();
        int selection = 0;
        while (selection != -1) {
            selection = displayMenu(userInput);
            switch (selection) {
                case 1:
                    questionPartA(userInput, random);
                    System.out.println();
                    break;
                case 2:
                    questionPartB(userInput, random);
                    System.out.println();
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

    private static void questionPartA(Scanner userInput, Random random) {
        // request user enter a positive integer call it n.
        int n = grabUserNumber(userInput);
        // generate random integer between -100, 100
        int[] a = generateRandomNum(n, random);
        // request a user to enter a number between
        System.out.format("Enter a number between 1 to %d (k least element): \n", n);
        int key = generateKey(a,userInput, n);
        System.out.println("Generated array " + Arrays.toString(a));
        // Quick Select
        int indexPiviot = quickSelect(a,key, 0, a.length-1);
        System.out.format("The %d element least number is %d. \n" ,(key + 1), a[indexPiviot]);

    }

    private static void questionPartB(Scanner userInput, Random random) {
        // request user enter a positive integer call it n.
        int n = grabUserNumber(userInput);
        // generate random integer between -100, 100
//        int[] a = generateRandomNum(n, random);
        int[] a = {0,2,6,5,100};

        // request a user to enter a number between size of the array
        System.out.format("Enter a number between 1 to %d (to find the k max element(s)): \n", n);
        int key = generateKey(a,userInput, n);
        System.out.println("Generated array " + Arrays.toString(a));
        // Quick Select
        maxKnumbers(a, key);

        Arrays.sort(a);
        System.out.println(        Arrays.toString(a));
    }

    private static void maxKnumbers(int[] a,  int key) {
        int size = a.length - 1, index = size - key;
        int printIndex = quickSelect(a,index, 0, size);
        System.out.format("Max of k numbers. k = %d [", (key + 1));
        for (int i = printIndex; i <= size; i++) {
            if(i < size)
                System.out.format("%d, ", a[i]);
            else {
                System.out.format("%d]\n",a[i]);
            }
        }
    }

    private static int quickSelect(int[] a, int key, int left, int right) {
        int pivotIndex = partition(a,left,right);
        if (pivotIndex == key)
            return pivotIndex;
        else if (pivotIndex < key) // pivot is on right hand side
            return quickSelect(a, key, pivotIndex + 1, right);
        else // pivot is on left hand side
            return quickSelect(a, key, left, pivotIndex - 1);
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
        System.out.println("Enter a a number for the array size: ");
        while (num < 0) {
            num = userInput.nextInt();
        }
        return num;
    }

    private static int displayMenu(Scanner userInput) {
        System.out.println("---------------------------------");
        System.out.println("              -Menu-            ");
        System.out.println(" 1: kth least element");
        System.out.println(" 2: max k number");
        System.out.println("-1: Exit program");
        System.out.println("---------------------------------");
        System.out.println("Please enter a option: ");
        return userInput.nextInt();
    }

}



// [3, 51, 1, -91, -39]    pivot = 3

//     ^            ^
// [3, 51, 1, -91, -39]  pivot = 3
// creates the left and right markers
//     ^             ^
// [3, 51, 1, -91, -39]
// i element is bigger than pivot switch rhs marker  (element i doesn't move up), (right marker move down)
//       ^      ^
// [3, -39, 1, -91, 51]  pivotlocation = 0
// i element is less than pivot switch the previous element
//          ^    ^
// [-39, 3, 1, -91, 51] pivotlocation = 1
// i element is less than pivot switch the previous element
//              ^
//              ^
// [-39, 1, 3, -91, 51] pivotlocation = 2
// i element is bigger than pivot switch rhs marker  (element i doesn't move up), (right marker move down)
//           ^   ^
// [-39, 1, -91, 3, 51] pivotlocation = 3
// for loop condition is meet the loop ends

// [-91, -39, 3, 51, 1]]

//        System.out.println(leftMarker + " : pivot: " + pivot);
//            System.out.println(Arrays.toString(a) + " index: " + i);
//        System.out.println(Arrays.toString(a) + "  pivotIndex: " + pivotIndex + " key " + key + " left " + left + " right: " + right);
//        int[] a = {4, 2, 0, 10, 1, 6}; // [4, 2, 0, 10, 1, 6]
//        int[] a = {1,0,5,10,2,8,6};

//        System.out.println("===========================================");
//         System.out.println("\n Testing if it is correct: " + Arrays.toString(a));