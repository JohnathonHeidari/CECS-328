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
                    questionTwo(userInput);
                    break;
                case 2:
                    questionThree(userInput);
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
    /** PROBLEM 3 --- FIND THE MODE AND REPEATED NUMBERS (RANDOM ARRAY) --- **/
    private static void questionThree(Scanner userInput) {
        int[] a = generateRandomArray(userInput);
//        int[] a = {-5, -2, -1, 0, 0, 1, 5, 7, 7};
        System.out.println("Original array: " + Arrays.toString(a));
        long begin = System.nanoTime(), elapsed = 0; // (to calculate the runtime) 2 Part D
        countingSortArray(a); // modify counting sort
        elapsed = System.nanoTime() - begin; // (to calculate the runtime) input size 100 array
        System.out.format("Time that countingSort executed: %d nanoseconds\n", elapsed); // (to calculate the runtime) 49862801 nanoseconds
    }

    private static void countingSortArray(int[] a) {
        int size = a.length, max = a[0];
        // find the max number
        for (int i = 0; i < size; i++) {
            if(max < a[i]){
                max = a[i];
            }
        }
        // defining array k+1  (note θ(1))
        int[] b = new int[max+1];
        int mode = 0;
        for (int i = 0; i < size; i++){
            b[a[i]] += 1;
            if(mode < b[a[i]]){
                mode = b[a[i]];
            }
        }
        System.out.println("Counting array: " + Arrays.toString(b));
        System.out.println();


        // find the repetition and mode using the (new array) frequency of number
        String output1 = "", output2 = "";
        boolean status = false;
        for (int i = 0; i <b.length ; i++) {
            if(b[i] == mode){
                if(output1.equals(""))
                    output1 += i;
                else {
                    output1 += " and " + i;
                    status = true;
                }
            }
            if(b[i] > 1){
                if(output2.equals("")) //
                    output2 += i + " was repeated " + b[i]  + " times";
                else
                    output2 += ",\n" + i + " was repeated " + b[i]  + " times";
            }
        }
        if(!output2.equals(""))
            output2 +=  ".";
        if(status)
            output1 += " are the mode.";
        else
            output1 += " is the mode.";
        System.out.println(output1);
        System.out.println(output2);

    }

    private static int[] generateRandomArray(Scanner userInput) {
        Random random = new Random();

        System.out.println("Please enter the size of the array: ");
        int itr = userInput.nextInt();
        if (itr == 0) {
            return new int[0];
        }
        // generate a random array that is not bigger than user choose as a size.
        int i = 0, value;
        int[] a = new int[itr];
        while (i < itr) {
            value = random.nextInt(itr);
            a[i] = value;
            i++;
        }
        return a;
    }
    /** PROBLEM 2 --- FIND THE REPEATED NUMBERS (SORTED ARRAY) --- **/
    private static void questionTwo(Scanner userInput) {
        int[] a =  generateSortedArray(userInput);
//        int[] a = {-5, -2, -1, 0, 0, 1, 5, 7, 7};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        System.out.println("Please enter a key to find any repeated numbers: ");
        int key = grabUserInput(userInput);
        int start = 0, end = a.length-1, mid = (start+end)/2, counter = 0;
        if((a[mid] == key && a[mid+1] == key) || a[mid] == key && a[mid-1] == key) {
//            long begin = System.nanoTime(), elapsed = 0; //(to calculate the runtime) 2 Part D
            // check the lower element
            counter = binary_search(a, key, counter, start, mid - 1);
            // check the upper element
            counter = binary_search(a, key, counter, mid + 1, end);
            // calculates the middle element as it contains the key
            counter++;
            System.out.printf("%d was repeated %d times.\n", key, counter);
        }
        else{
            counter = binary_search(a, key, counter, start, end);
            System.out.printf("%d was repeated %d times.\n", key, counter);
        }
    }

    private static int binary_search(int[] a, int key, int counter, int start, int end) {
        int mid = (start + end) / 2;
        // base case
        if(start > end) {
            return counter;
        }
        System.out.println(a[mid] + ", key : " + key);
        if(key == a[mid]){
            counter++;
        }
        if(a[mid] > key){
            return binary_search(a,key,counter,start,mid-1);
        }
        else{
            return binary_search(a,key,counter,mid + 1,end);
        }
    }

    private static int[] generateSortedArray(Scanner userInput) {
        Random random = new Random();

        System.out.println("Please enter the size of the array: ");
        int itr = userInput.nextInt();
        if (itr == 0) {
            return new int[0];
        }
        int i = 0, value, status = random.nextInt(itr);
        int[] a = new int[itr];
        while (i < itr) {
            value = random.nextInt(itr) * (random.nextBoolean() ? -1:1);
            a[i] = value;
            i++;
        }
        return a;
    }

    private static int grabUserInput(Scanner userInput) {
        int key = -1;
        while (true) {
            try {
                key = userInput.nextInt();
                return key;
            } catch (InputMismatchException e) {
                System.out.println("Invalid: Please enter a number");
            }
        }
    }

    public static int displayMenu(Scanner userInput) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("              -Menu-            ");
        System.out.println(" 1: Find the amount of times the number repeated");
        System.out.println(" 2: Find the mean element(s) and all the number that have repeated");
        System.out.println("-1: Exit program");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Please enter a option: ");
        return userInput.nextInt();
    }
}
