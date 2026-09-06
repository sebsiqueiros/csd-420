/*
 * Name: Sebastian Siqueiros
 * Course: CSD-420
 * Module: 5
 * Assignment: 5.2
 * Date: September 2026
 *
 * Description:
 * This program reads words from a text file named
 * collection_of_words.txt. It stores the words in a TreeSet
 * so duplicate words are removed automatically. The program
 * displays all non-duplicate words in ascending order and
 * descending order. Test code is included to make sure the
 * program is functioning correctly.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Siqueiros_Mod5_2 {

    public static void main(String[] args) {

        // Create a TreeSet to store the words.
        // TreeSet automatically removes duplicate words
        // and keeps the words in ascending order.
        Set<String> words = new TreeSet<>();

        // Read the words from the text file.
        try {
            File file = new File("collection_of_words.txt");
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                words.add(input.next());
            }

            input.close();

        } catch (FileNotFoundException e) {
            System.out.println(
                "The file collection_of_words.txt was not found."
            );
            return;
        }

        // Display words in ascending order.
        System.out.println("Words in Ascending Order:");

        for (String word : words) {
            System.out.println(word);
        }

        // Display words in descending order.
        System.out.println();
        System.out.println("Words in Descending Order:");

        for (String word : ((TreeSet<String>) words).descendingSet()) {
            System.out.println(word);
        }

        // Test code
        System.out.println();
        System.out.println("Testing:");

        // Test 1: Make sure the correct number of unique words
        // was found after removing duplicates.
        int expectedUniqueWords = 8;

        if (words.size() == expectedUniqueWords) {
            System.out.println(
                "Test 1 passed: Duplicate words were removed."
            );
        } else {
            System.out.println(
                "Test 1 failed: The number of unique words is incorrect."
            );
        }

        // Test 2: Make sure words were actually read from the file.
        if (!words.isEmpty()) {
            System.out.println(
                "Test 2 passed: Words were successfully read from the file."
            );
        } else {
            System.out.println(
                "Test 2 failed: No words were found."
            );
        }

        // Test 3: Make sure the first word is correct
        // when the words are in ascending order.
        if (!words.isEmpty()
                && ((TreeSet<String>) words).first().equals("computer")) {
            System.out.println(
                "Test 3 passed: Ascending order is correct."
            );
        } else {
            System.out.println(
                "Test 3 failed: Ascending order is incorrect."
            );
        }

        // Test 4: Make sure the first word in descending order
        // is the word that should come last alphabetically.
        if (!words.isEmpty()
                && ((TreeSet<String>) words).last().equals("testing")) {
            System.out.println(
                "Test 4 passed: Descending order is correct."
            );
        } else {
            System.out.println(
                "Test 4 failed: Descending order is incorrect."
            );
        }
    }
}