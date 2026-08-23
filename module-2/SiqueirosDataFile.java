/*
 * Name: Sebastian Siqueiros
 * Course: CSD-420
 * Assignment: Module 2.2
 * Date: August 24, 2026
 *
 * Description:
 * This program generates five random integers and five random double values
 * and writes them to a data file. If the file does not exist, it is created.
 * If the file already exists, the new data is appended to the file.
 */

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class SiqueirosDataFile {

    public static void main(String[] args) {

        String fileName = "SiqueirosDatafile.dat";
        Random random = new Random();

        try (DataOutputStream output = new DataOutputStream(
                new FileOutputStream(fileName, true))) {

            System.out.println("Writing data to " + fileName);

            // Generate and write five random integers
            System.out.println("Random Integers:");

            for (int i = 0; i < 5; i++) {
                int number = random.nextInt(100);
                output.writeInt(number);
                System.out.println(number);
            }

            // Generate and write five random double values
            System.out.println("Random Doubles:");

            for (int i = 0; i < 5; i++) {
                double number = random.nextDouble();
                output.writeDouble(number);
                System.out.println(number);
            }

            System.out.println("Data was successfully written to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
            e.printStackTrace();
        }
    }
}