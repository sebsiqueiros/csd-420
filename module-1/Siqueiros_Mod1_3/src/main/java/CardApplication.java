import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Sebastian Siqueiros
 *
 * CSD-420 Module 1.3
 * August 15, 2026 
 * This JavaFX application randomly selects four cards from a
 * deck of 52 playing cards and displays them in a window.
 *
 * The Refresh button uses a lambda expression to generate
 * four new cards when clicked.
 */
public class CardApplication extends Application {

    // Random number generator used to select cards.
    private final Random random = new Random();

    /**
     * Starts the JavaFX application.
     *
     * @param stage the primary JavaFX window
     */
    @Override
    public void start(Stage stage) {

        // Create a horizontal box to hold the four cards.
        HBox cardBox = new HBox(15);

        // Center the cards in the window.
        cardBox.setAlignment(Pos.CENTER);

        // Display four randomly selected cards when the
        // application first starts.
        displayRandomCards(cardBox);

        // Create the Refresh button.
        Button refreshButton = new Button("Refresh");

        // Use a lambda expression for the button event.
        refreshButton.setOnAction(event -> displayRandomCards(cardBox));

        // Create a vertical layout containing the cards
        // and the Refresh button.
        VBox root = new VBox(20);

        // Center everything in the window.
        root.setAlignment(Pos.CENTER);

        // Add the cards and button to the layout.
        root.getChildren().addAll(cardBox, refreshButton);

        // Create the JavaFX scene.
        Scene scene = new Scene(root, 1100, 500);

        // Set the window title.
        stage.setTitle("Four Random Playing Cards");

        // Add the scene to the stage.
        stage.setScene(scene);

        // Display the window.
        stage.show();
    }

    /**
     * Selects four unique cards and displays them.
     *
     * @param cardBox the HBox where the cards are displayed
     */
    private void displayRandomCards(HBox cardBox) {

        // Remove the cards currently being displayed.
        cardBox.getChildren().clear();

        // HashSet prevents duplicate card numbers.
        Set<Integer> selectedCards = new HashSet<>();

        // Continue selecting cards until four unique cards
        // have been selected.
        while (selectedCards.size() < 4) {
            int cardNumber = random.nextInt(52) + 1;
            selectedCards.add(cardNumber);
        }

        // Create an ImageView for each selected card.
        for (int cardNumber : selectedCards) {

            // Build the path to the card image.
            String imagePath =
                    "/cards/" + cardNumber + ".png";

            // Load the card image.
            Image image = new Image(
                    getClass().getResourceAsStream(imagePath)
            );

            // Create an ImageView to display the image.
            ImageView imageView = new ImageView(image);

            // Resize the card so four cards fit in the window.
            imageView.setFitWidth(200);
            imageView.setFitHeight(283);

            // Preserve the card's original proportions.
            imageView.setPreserveRatio(true);

            // Add the card to the display.
            cardBox.getChildren().add(imageView);
        }
    }

    /**
     * Main method used to launch the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}