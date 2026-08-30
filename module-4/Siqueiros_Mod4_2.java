/*
 * Name: Sebastian Siqueiros
 * Course: CSD-420
 * Assignment: Module 4 Assignment 4.2
 * Date: August 31, 2026
 * Description: This program compares the time required to traverse a
 *              LinkedList using an Iterator and the get(index) method.
 *              The program tests the LinkedList with 50,000 and 500,000
 *              integers and displays the time taken by each approach.
 */

import java.util.Iterator;
import java.util.LinkedList;

public class Siqueiros_Mod4_2 {

    /*
     * Tests the traversal speed of a LinkedList using an Iterator
     * and the get(index) method.
     *
     * @param size the number of integers to store in the LinkedList
     */
    public static void testList(int size) {

        // Create the LinkedList.
        LinkedList<Integer> list = new LinkedList<>();

        // Add the requested number of integers to the LinkedList.
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        System.out.println("\nTesting LinkedList with " + size + " integers");

        /*
         * Test traversal using an Iterator.
         */
        long iteratorStart = System.nanoTime();

        Iterator<Integer> iterator = list.iterator();
        long iteratorSum = 0;

        while (iterator.hasNext()) {
            iteratorSum += iterator.next();
        }

        long iteratorEnd = System.nanoTime();

        long iteratorTime = iteratorEnd - iteratorStart;

        /*
         * Test traversal using the get(index) method.
         */
        long getStart = System.nanoTime();

        long getSum = 0;

        for (int i = 0; i < list.size(); i++) {
            getSum += list.get(i);
        }

        long getEnd = System.nanoTime();

        long getTime = getEnd - getStart;

        /*
         * Test that both methods actually visited all of the elements.
         * Since both methods add every value together, their sums
         * should be the same.
         */
        if (iteratorSum == getSum && iteratorSum == ((long) size * (size - 1)) / 2) {
            System.out.println("Test passed: Both methods visited all elements.");
        } else {
            System.out.println("Test failed: The traversal results do not match.");
        }

        
        System.out.println("Iterator time: " + iteratorTime + " nanoseconds");
        System.out.println("get(index) time: " + getTime + " nanoseconds");

        
        if (iteratorTime > 0) {
            double difference = (double) getTime / iteratorTime;

            System.out.printf(
                "get(index) was approximately %.2f times slower than the Iterator.%n",
                difference
            );
        }
    }

    public static void main(String[] args) {

        System.out.println("CSD-420 Module 4 Assignment 4.2");
        System.out.println("LinkedList Traversal Performance Test");

        
        testList(50000);

        
        testList(500000);

        
    }
}