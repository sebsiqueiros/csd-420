/*
 * Name: Sebastian Siqueiros
 * Course: CSD-420
 * Assignment: Module 6.2
 * Date: September 12, 2026
 * Description:
 * This program demonstrates two generic bubble sort methods.
 * The first method uses the Comparable interface and the second
 * method uses the Comparator interface. The program tests both
 * methods with numbers and strings to make sure they work correctly.
 */

import java.util.Comparator;

public class SiqueirosBubbleSort {

    /*
     * This method sorts an array using the Comparable interface.
     * The objects in the array are compared to each other.
     */
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {

        boolean changed = true;

        for (int i = 1; i < list.length && changed; i++) {

            changed = false;

            for (int j = 0; j < list.length - i; j++) {

                if (list[j].compareTo(list[j + 1]) > 0) {

                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;

                    changed = true;
                }
            }
        }
    }

    /*
     * This method sorts an array using the Comparator interface.
     * The Comparator decides how the objects should be compared.
     */
    public static <E> void bubbleSort(E[] list, Comparator<E> comparator) {

        boolean changed = true;

        for (int i = 1; i < list.length && changed; i++) {

            changed = false;

            for (int j = 0; j < list.length - i; j++) {

                if (comparator.compare(list[j], list[j + 1]) > 0) {

                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;

                    changed = true;
                }
            }
        }
    }

    /*
     * This method prints the values in an array.
     */
    public static <E> void printArray(E[] list) {

        for (E element : list) {
            System.out.print(element + " ");
        }

        System.out.println();
    }

    /*
     * This method checks if an integer array is sorted from
     * smallest to largest.
     */
    public static boolean isSorted(Integer[] list) {

        for (int i = 0; i < list.length - 1; i++) {

            if (list[i] > list[i + 1]) {
                return false;
            }
        }

        return true;
    }

    /*
     * This is the main method used to test both bubble sort methods.
     */
    public static void main(String[] args) {

        // Test the Comparable version with integers.
        Integer[] numbers = { 5, 3, 8, 1, 9, 2, 4 };

        System.out.println("Comparable Bubble Sort");
        System.out.print("Before sorting: ");
        printArray(numbers);

        bubbleSort(numbers);

        System.out.print("After sorting:  ");
        printArray(numbers);

        if (isSorted(numbers)) {
            System.out.println("Comparable test passed.");
        } else {
            System.out.println("Comparable test failed.");
        }

        System.out.println();

        // Test the Comparator version with integers.
        Integer[] moreNumbers = { 7, 2, 9, 4, 1, 6, 3 };

        System.out.println("Comparator Bubble Sort");
        System.out.print("Before sorting: ");
        printArray(moreNumbers);

        bubbleSort(moreNumbers, Comparator.naturalOrder());

        System.out.print("After sorting:  ");
        printArray(moreNumbers);

        if (isSorted(moreNumbers)) {
            System.out.println("Comparator test passed.");
        } else {
            System.out.println("Comparator test failed.");
        }

        System.out.println();

        // Test the Comparable version with strings.
        String[] words = {
            "Orange",
            "Apple",
            "Banana",
            "Grape",
            "Pear"
        };

        System.out.println("Comparable Bubble Sort with Strings");
        System.out.print("Before sorting: ");
        printArray(words);

        bubbleSort(words);

        System.out.print("After sorting:  ");
        printArray(words);

        System.out.println("String Comparable test completed.");

        System.out.println();

        // Test the Comparator version with strings in reverse order.
        String[] reverseWords = {
            "Orange",
            "Apple",
            "Banana",
            "Grape",
            "Pear"
        };

        System.out.println("Comparator Bubble Sort with Strings in Reverse Order");
        System.out.print("Before sorting: ");
        printArray(reverseWords);

        bubbleSort(reverseWords, Comparator.reverseOrder());

        System.out.print("After sorting:  ");
        printArray(reverseWords);

        System.out.println("String Comparator test completed.");
    }
}