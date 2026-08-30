/*
 * Name: Sebastian Siqueiros
 * Course: CSD-420
 * Assignment: Module 3 Assignment 3.2
 * Date: August 31, 2026
 * Description: This program creates an ArrayList containing 50 random
 *              numbers from 1 through 20. It then uses a static generic
 *              method to create a new ArrayList without duplicate values.
 */

import java.util.ArrayList;
import java.util.Random;

public class Siqueiros_Mod3_2 {

    /*
     * This method creates a new ArrayList containing the values
     * from the original list without any duplicates.
     *
     * @param list the original ArrayList
     * @return a new ArrayList containing no duplicate values
     */
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {

        ArrayList<E> newList = new ArrayList<>();

        for (E value : list) {
            if (!newList.contains(value)) {
                newList.add(value);
            }
        }

        return newList;
    }

    public static void main(String[] args) {

        ArrayList<Integer> originalList = new ArrayList<>();

        
        Random random = new Random();

        
        for (int i = 0; i < 50; i++) {
            int number = random.nextInt(20) + 1;
            originalList.add(number);
        }

        
        System.out.println("Original ArrayList:");
        System.out.println(originalList);

        
        ArrayList<Integer> newList = removeDuplicates(originalList);

        
        System.out.println("\nArrayList After Removing Duplicates:");
        System.out.println(newList);

        
        System.out.println("\nOriginal ArrayList Size: " + originalList.size());
        System.out.println("New ArrayList Size: " + newList.size());
    }
}