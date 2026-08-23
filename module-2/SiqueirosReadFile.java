import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class SiqueirosReadFile {

    public static void main(String[] args) {

        String fileName = "SiqueirosDatafile.dat";

        try (DataInputStream input = new DataInputStream(
                new FileInputStream(fileName))) {

            System.out.println("Reading data from " + fileName);
            System.out.println();

            int recordNumber = 1;

            try {
                while (true) {

                    System.out.println("Record " + recordNumber);

                    System.out.println("Integers:");

                    for (int i = 0; i < 5; i++) {
                        System.out.println(input.readInt());
                    }

                    System.out.println("Doubles:");

                    for (int i = 0; i < 5; i++) {
                        System.out.println(input.readDouble());
                    }

                    System.out.println();

                    recordNumber++;
                }

            } catch (EOFException e) {
                System.out.println("Finished reading the file.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}