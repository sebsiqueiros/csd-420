/*
 * Name: Sebastian Siqueiros
 * Course: CSD-420 Java Programming
 * Module: 7.2
 * Date: September 18, 2026
 *
 * Description:
 * This program demonstrates the use of an external JavaFX CSS
 * stylesheet. The stylesheet defines a style class for white
 * circles with black borders and IDs for red and green circles.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Siqueiros_Mod7_2 extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create the main horizontal box.
        HBox hBox = new HBox(5);

        // Center all objects vertically in the HBox.
        hBox.setAlignment(Pos.CENTER);

        // Create the scene.
        Scene scene = new Scene(hBox, 300, 250);

        // Load the external CSS stylesheet.
        scene.getStylesheets().add("mystyle.css");

        // ---------------------------------------------------------
        // First pane
        // ---------------------------------------------------------

        Pane pane = new Pane();

        // Set the size of the pane.
        pane.setPrefSize(70, 250);

        // Create the first circle.
        Circle circle1 = new Circle(35, 125, 30);

        // Apply the plaincircle style class.
        circle1.getStyleClass().add("plaincircle");

        // Add the first circle to the pane.
        pane.getChildren().add(circle1);

        // Apply the border style to the pane.
        pane.getStyleClass().add("border");

        // ---------------------------------------------------------
        // Second circle
        // ---------------------------------------------------------

        Circle circle2 = new Circle(30, 125, 30);

        // Apply the plaincircle style class.
        circle2.getStyleClass().add("plaincircle");

        // ---------------------------------------------------------
        // Third circle
        // ---------------------------------------------------------

        Circle circle3 = new Circle(30, 125, 30);

        // Apply the redcircle ID.
        circle3.setId("redcircle");

        // ---------------------------------------------------------
        // Fourth circle
        // ---------------------------------------------------------

        Circle circle4 = new Circle(30, 125, 30);

        // Apply the plaincircle style class.
        circle4.getStyleClass().add("plaincircle");

        // Apply the greencircle ID.
        circle4.setId("greencircle");

        // Add the pane and remaining three circles to the HBox.
        hBox.getChildren().addAll(
                pane,
                circle2,
                circle3,
                circle4
        );

        // Test the program.
        testProgram(
                scene,
                circle1,
                circle2,
                circle3,
                circle4,
                pane
        );

        // Set the title of the window.
        primaryStage.setTitle("Exercise31_01");

        // Set the scene.
        primaryStage.setScene(scene);

        // Display the window.
        primaryStage.show();
    }

    /**
     * Tests the CSS stylesheet, style classes, IDs, and pane.
     *
     * @param scene the JavaFX scene
     * @param circle1 first circle
     * @param circle2 second circle
     * @param circle3 third circle
     * @param circle4 fourth circle
     * @param pane bordered pane
     */
    private void testProgram(
            Scene scene,
            Circle circle1,
            Circle circle2,
            Circle circle3,
            Circle circle4,
            Pane pane) {

        // Test that the stylesheet was loaded.
        if (scene.getStylesheets().contains("mystyle.css")) {
            System.out.println("PASS: CSS stylesheet loaded.");
        } else {
            throw new AssertionError(
                    "FAIL: CSS stylesheet was not loaded.");
        }

        // Test the first circle.
        if (circle1.getStyleClass().contains("plaincircle")) {
            System.out.println(
                    "PASS: Circle 1 has plaincircle class.");
        } else {
            throw new AssertionError(
                    "FAIL: Circle 1 does not have plaincircle class.");
        }

        // Test the second circle.
        if (circle2.getStyleClass().contains("plaincircle")) {
            System.out.println(
                    "PASS: Circle 2 has plaincircle class.");
        } else {
            throw new AssertionError(
                    "FAIL: Circle 2 does not have plaincircle class.");
        }

        // Test the third circle.
        if ("redcircle".equals(circle3.getId())) {
            System.out.println(
                    "PASS: Circle 3 has redcircle ID.");
        } else {
            throw new AssertionError(
                    "FAIL: Circle 3 does not have redcircle ID.");
        }

        // Test the fourth circle.
        if (circle4.getStyleClass().contains("plaincircle")) {
            System.out.println(
                    "PASS: Circle 4 has plaincircle class.");
        } else {
            throw new AssertionError(
                    "FAIL: Circle 4 does not have plaincircle class.");
        }

        // Test the fourth circle's ID.
        if ("greencircle".equals(circle4.getId())) {
            System.out.println(
                    "PASS: Circle 4 has greencircle ID.");
        } else {
            throw new AssertionError(
                    "FAIL: Circle 4 does not have greencircle ID.");
        }

        // Test the pane border.
        if (pane.getStyleClass().contains("border")) {
            System.out.println(
                    "PASS: Pane has border class.");
        } else {
            throw new AssertionError(
                    "FAIL: Pane is missing border class.");
        }

        // Display the final test result.
        System.out.println("All tests passed.");
    }

    /**
     * Main method that launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}