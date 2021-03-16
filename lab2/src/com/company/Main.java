package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int selection = 0;
        while (selection != -1) {
            selection = displayMenu(userInput);
            switch (selection) {
                case 1:
                    questionOne(userInput);
                    System.out.println();
                    break;
                case 2:
                    questionTwo(userInput);
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

    private static void questionOne(Scanner userInput) {
        System.out.println("Please enter a square root number: ");
        int value = userInput.nextInt();
        double mid = (double) value / 2;
        System.out.println("Squareroot of " + value + " is: " + squareRoot(value, mid, value, 0));
    }

    private static void questionTwo(Scanner userInput) {
        int[] a = generateArray(userInput);
        System.out.println(Arrays.toString(a));
        System.out.println("The smallest missing number is: " + findSmallestNumber(a, 0, a.length - 1));
    }

    private static int findSmallestNumber(int[] a, int start, int end) {
        int mid = (start + end) / 2;
        // base case
        if (start > end) {
            return start;
        }
        // if the array is one element
        else if (a.length == 1) {
            if(a[start] > 0){
                return 0;
            }
            else {
                return a[start] + 1;
            }
        }
        if (a[mid] == mid) {
            // since the list is sorted to (0-m), if element is same as the value we assume that LHS is correct
            return findSmallestNumber(a, mid + 1, end);
        } else {
            return findSmallestNumber(a, start, mid - 1);
        }
    }


    private static int squareRoot(int value, double mid, double high, double low) {
        // the newmid is for case when (assuming) squareroot of 10 if mid = 5 was 'low 25 > 10' then range should be (6, 10) and new mid = 8 as ex lecture
        double newmid = ((mid + 1) + high) / 2;
        double square = mid * mid;
        if (square > value) {

            if ((square > value) & (value > (mid - 1) * (mid - 1))) {
                return (int) Math.ceil(mid);
            } else {
                return squareRoot(value, mid / 2, mid, low); // Decrease
            }
        } else if (square < value) {

            if ((square < value) & (value < (mid + 1) * (mid + 1))) {
                return (int) Math.ceil(mid);
            } else {
                return squareRoot(value, newmid, high, mid + 1); // Increase
            }
        } else {

            return (int) mid;
        }
    }

    private static int[] generateArray(Scanner userInput) {
        Random random = new Random();

        HashSet<Integer> duplicates = new HashSet<>();
        System.out.println("Please enter the range of the array: ");
        int itr = userInput.nextInt();
        if (itr == 0) {
            return new int[0];
        }
        int i = 0, value, status = random.nextInt(itr);
        int[] a = new int[itr - status];
        while (i < itr - status) {
            value = random.nextInt(itr);
            if (!duplicates.contains(value)) {
                a[i] = value;
                duplicates.add(value);
                i++;
            }
        }
        Arrays.sort(a);
        return a;
    }

    public static int displayMenu(Scanner userInput) {
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