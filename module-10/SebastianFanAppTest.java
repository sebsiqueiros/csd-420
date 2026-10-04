/*
 * Name: Sebastian Siqueiros
 * Course: CSD-420 Advanced Java Programming
 * Module: 10.2
 *
 * Purpose:
 * This program tests the database functions used by the
 * SebastianFanApp program. The tests verify that the program
 * can connect to the databasedb database, retrieve a fan record,
 * update a fan record, and confirm that the update was successful.
 *
 * The test restores the original fan information after the
 * update test is completed.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SebastianFanAppTest {

    // Database connection information
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/databasedb";

    private static final String DB_USER = "student1";
    private static final String DB_PASSWORD = "pass";

    public static void main(String[] args) {

        System.out.println("CSD-420 Module 10 Test");
        System.out.println("----------------------");

        testDatabaseConnection();
        testDisplayRecord();
        testUpdateRecord();

        System.out.println("----------------------");
        System.out.println("Testing complete.");
    }

    /*
     * Tests whether a connection can be made to databasedb.
     */
    private static void testDatabaseConnection() {

        System.out.println();
        System.out.println("TEST 1: Database Connection");

        try (Connection connection = getConnection()) {

            if (connection != null && !connection.isClosed()) {
                System.out.println(
                        "PASS: Successfully connected to databasedb."
                );
            } else {
                System.out.println(
                        "FAIL: Database connection was not created."
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "FAIL: Could not connect to the database."
            );

            System.out.println(e.getMessage());
        }
    }

    /*
     * Tests the same type of SELECT operation used by the
     * Display button in SebastianFanApp.
     */
    private static void testDisplayRecord() {

        System.out.println();
        System.out.println("TEST 2: Display Fan Record");

        int testID = 1;

        String sql =
                "SELECT ID, firstname, lastname, favoriteteam "
                        + "FROM fans WHERE ID = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, testID);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    System.out.println(
                            "PASS: Fan record was found."
                    );

                    System.out.println(
                            "ID: "
                                    + resultSet.getInt("ID")
                    );

                    System.out.println(
                            "First Name: "
                                    + resultSet.getString("firstname")
                    );

                    System.out.println(
                            "Last Name: "
                                    + resultSet.getString("lastname")
                    );

                    System.out.println(
                            "Favorite Team: "
                                    + resultSet.getString("favoriteteam")
                    );

                } else {

                    System.out.println(
                            "FAIL: Fan ID "
                                    + testID
                                    + " was not found."
                    );
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "FAIL: Error while retrieving fan record."
            );

            System.out.println(e.getMessage());
        }
    }

    /*
     * Tests the same type of UPDATE operation used by the
     * Update button in SebastianFanApp.
     *
     * The original favorite team is saved first. The test
     * changes the favorite team, verifies the change, and then
     * restores the original value.
     */
    private static void testUpdateRecord() {

        System.out.println();
        System.out.println("TEST 3: Update Fan Record");

        int testID = 1;
        String originalTeam = null;
        String testTeam = "Test Team";

        String selectSQL =
                "SELECT favoriteteam FROM fans WHERE ID = ?";

        String updateSQL =
                "UPDATE fans SET favoriteteam = ? WHERE ID = ?";

        try (Connection connection = getConnection()) {

            // Get the original favorite team
            try (
                    PreparedStatement selectStatement =
                            connection.prepareStatement(selectSQL)
            ) {

                selectStatement.setInt(1, testID);

                try (
                        ResultSet resultSet =
                                selectStatement.executeQuery()
                ) {

                    if (resultSet.next()) {

                        originalTeam =
                                resultSet.getString("favoriteteam");

                    } else {

                        System.out.println(
                                "FAIL: Fan ID "
                                        + testID
                                        + " was not found."
                        );

                        return;
                    }
                }
            }

            // Update the favorite team with a temporary test value
            try (
                    PreparedStatement updateStatement =
                            connection.prepareStatement(updateSQL)
            ) {

                updateStatement.setString(1, testTeam);
                updateStatement.setInt(2, testID);

                int rowsUpdated =
                        updateStatement.executeUpdate();

                if (rowsUpdated == 1) {

                    System.out.println(
                            "PASS: Update command changed one record."
                    );

                } else {

                    System.out.println(
                            "FAIL: Update command did not "
                                    + "change one record."
                    );

                    return;
                }
            }

            // Verify that the update was saved
            try (
                    PreparedStatement verifyStatement =
                            connection.prepareStatement(selectSQL)
            ) {

                verifyStatement.setInt(1, testID);

                try (
                        ResultSet resultSet =
                                verifyStatement.executeQuery()
                ) {

                    if (resultSet.next()) {

                        String updatedTeam =
                                resultSet.getString("favoriteteam");

                        if (testTeam.equals(updatedTeam)) {

                            System.out.println(
                                    "PASS: Updated value was "
                                            + "saved correctly."
                            );

                        } else {

                            System.out.println(
                                    "FAIL: Updated value was "
                                            + "not saved correctly."
                            );
                        }
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "FAIL: Error while testing update."
            );

            System.out.println(e.getMessage());

        } finally {

            if (originalTeam != null) {

                restoreOriginalTeam(
                        testID,
                        originalTeam
                );
            }
        }
    }

    /*
     * Restores the fan's original favorite team after testing.
     */
    private static void restoreOriginalTeam(
            int fanID,
            String originalTeam) {

        String sql =
                "UPDATE fans SET favoriteteam = ? WHERE ID = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, originalTeam);
            statement.setInt(2, fanID);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated == 1) {

                System.out.println(
                        "PASS: Original fan information was restored."
                );

            } else {

                System.out.println(
                        "FAIL: Original fan information "
                                + "was not restored."
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "FAIL: Could not restore original fan information."
            );

            System.out.println(e.getMessage());
        }
    }

    /*
     * Returns a connection to the databasedb database.
     */
    private static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                DB_URL,
                DB_USER,
                DB_PASSWORD
        );
    }
}