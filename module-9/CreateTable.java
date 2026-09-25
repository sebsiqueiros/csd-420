/*
 * Name: Sebastian Siqueiros
 * Assignment: Module 9.2
 * Course: CSD-420
 *
 * Purpose:
 * Tests the Java connection to the databasedb database
 * and creates a table named address33.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/databasedb";
        String user = "student1";
        String password = "pass";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS address33 ("
                    + "ID INT NOT NULL AUTO_INCREMENT, "
                    + "FIRSTNAME VARCHAR(40), "
                    + "LASTNAME VARCHAR(40), "
                    + "STREET VARCHAR(40), "
                    + "CITY VARCHAR(40), "
                    + "STATE VARCHAR(40), "
                    + "ZIP VARCHAR(40), "
                    + "PRIMARY KEY (ID))";

            statement.executeUpdate(sql);

            System.out.println("Connection successful!");
            System.out.println("Table address33 created successfully.");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Database error:");
            e.printStackTrace();
        }
    }
}