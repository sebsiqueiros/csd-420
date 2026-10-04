/*
 * Name: Sebastian Siqueiros
 * Course: CSD-420 Advanced Java Programming
 * Module: 10.2 Assignment
 *
 * Purpose:
 * This program connects to the databasedb database and allows a user
 * to display and update fan information stored in the fans table.
 * The user enters a fan ID and selects Display to retrieve the record.
 * The user can then change the displayed information and select Update
 * to save the changes to the database.
 *
 * The program does not create or delete the fans table.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SebastianFanApp extends Application {

    // Database connection information
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/databasedb";
    private static final String DB_USER = "student1";
    private static final String DB_PASSWORD = "pass";

    // Text fields used by the interface
    private final TextField idField = new TextField();
    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final TextField favoriteTeamField = new TextField();

    @Override
    public void start(Stage primaryStage) {

        // Create labels
        Label titleLabel = new Label("Fan Information");
        Label idLabel = new Label("Fan ID:");
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label favoriteTeamLabel = new Label("Favorite Team:");

        // Create buttons
        Button displayButton = new Button("Display");
        Button updateButton = new Button("Update");

        // Set preferred widths for the text fields
        idField.setPrefWidth(200);
        firstNameField.setPrefWidth(200);
        lastNameField.setPrefWidth(200);
        favoriteTeamField.setPrefWidth(200);

        // Display the database record matching the entered ID
        displayButton.setOnAction(event -> displayFan());

        // Update the displayed database record
        updateButton.setOnAction(event -> updateFan());

        // Place the two buttons next to each other
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(displayButton, updateButton);

        // Create the main layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(12);

        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(idLabel, 0, 1);
        grid.add(idField, 1, 1);
        grid.add(firstNameLabel, 0, 2);
        grid.add(firstNameField, 1, 2);
        grid.add(lastNameLabel, 0, 3);
        grid.add(lastNameField, 1, 3);
        grid.add(favoriteTeamLabel, 0, 4);
        grid.add(favoriteTeamField, 1, 4);
        grid.add(buttonBox, 0, 5, 2, 1);

        // Create and display the window
        Scene scene = new Scene(grid, 430, 300);

        primaryStage.setTitle("CSD-420 Module 10 - Fan Database");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*
     * Displays a fan record using the ID entered by the user.
     */
    private void displayFan() {

        String idText = idField.getText().trim();

        if (idText.isEmpty()) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Missing ID",
                    "Please enter a fan ID."
            );
            return;
        }

        int fanID;

        try {
            fanID = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid ID",
                    "Fan ID must be a number."
            );
            return;
        }

        String sql =
                "SELECT firstname, lastname, favoriteteam FROM fans WHERE ID = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, fanID);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    firstNameField.setText(
                            resultSet.getString("firstname")
                    );

                    lastNameField.setText(
                            resultSet.getString("lastname")
                    );

                    favoriteTeamField.setText(
                            resultSet.getString("favoriteteam")
                    );

                } else {

                    clearFanFields();

                    showAlert(
                            Alert.AlertType.INFORMATION,
                            "Record Not Found",
                            "No fan was found with ID " + fanID + "."
                    );
                }
            }

        } catch (SQLException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Database Error",
                    "Unable to display the fan record.\n\n"
                            + e.getMessage()
            );
        }
    }

    /*
     * Updates the fan record using the values currently displayed
     * in the interface.
     */
    private void updateFan() {

        String idText = idField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String favoriteTeam = favoriteTeamField.getText().trim();

        if (idText.isEmpty()) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Missing ID",
                    "Please enter a fan ID."
            );
            return;
        }

        int fanID;

        try {
            fanID = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid ID",
                    "Fan ID must be a number."
            );
            return;
        }

        if (firstName.isEmpty()
                || lastName.isEmpty()
                || favoriteTeam.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Missing Information",
                    "First name, last name, and favorite team "
                            + "cannot be blank."
            );
            return;
        }

        String sql =
                "UPDATE fans "
                        + "SET firstname = ?, lastname = ?, favoriteteam = ? "
                        + "WHERE ID = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, favoriteTeam);
            statement.setInt(4, fanID);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Update Successful",
                        "Fan ID " + fanID
                                + " was updated successfully."
                );

            } else {

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Record Not Found",
                        "No fan was found with ID " + fanID + "."
                );
            }

        } catch (SQLException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Database Error",
                    "Unable to update the fan record.\n\n"
                            + e.getMessage()
            );
        }
    }

    /*
     * Opens a connection to the databasedb database.
     */
    private Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                DB_URL,
                DB_USER,
                DB_PASSWORD
        );
    }

    /*
     * Clears the fan information fields.
     */
    private void clearFanFields() {

        firstNameField.clear();
        lastNameField.clear();
        favoriteTeamField.clear();
    }

    /*
     * Displays messages to the user.
     */
    private void showAlert(
            Alert.AlertType alertType,
            String title,
            String message) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /*
     * Main method used to launch the JavaFX application.
     */
    public static void main(String[] args) {

        launch(args);
    }
}