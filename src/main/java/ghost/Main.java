package ghost;

import ghost.command.ExitCommand;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

/**
 * The JavaFX GUI for the Ghost chatbot.
 * This class handles the initialization of the application window,
 * user input handling, and displaying responses in the UI.
 */
public class Main extends Application {
    private Label responseLabel;  // Declare the responseLabel field
    private Ghost ghost;  // Declare Ghost field

    /**
     * Starts the JavaFX application window.
     * Sets up the layout, input fields, and button actions.
     *
     * @param stage The primary stage for this JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);  // Center align the content

        // Create a label to display responses
        responseLabel = new Label("Welcome to Ghost! 👻");
        responseLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #000000; -fx-alignment: center;");

        // Initialize the Ghost object, passing the responseLabel to the constructor
        ghost = new Ghost("data/tasks.txt", responseLabel);

        // Show welcome message when the application starts
        ghost.getUi().showWelcomeMessage(responseLabel);

        // Create a ScrollPane to allow the responseLabel to scroll
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(responseLabel);
        scrollPane.setFitToWidth(true);  // Ensure label fits the scroll pane width

        // TextField for user input
        TextField inputField = new TextField();
        inputField.setPromptText("Enter your task here...");

        // Extracted method for creating the send button
        Button sendButton = createSendButton(inputField);

        // Create the ExitCommand and handle window close event
        ExitCommand exitCommand = new ExitCommand(responseLabel);
        stage.setOnCloseRequest(event -> exitCommand.execute(
                null, ghost.getUi(), null, responseLabel)); // Execute exit command on window close

        // Add components to layout
        layout.getChildren().addAll(scrollPane, inputField, sendButton);

        // Set up the scene and stage
        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("Ghost Task Manager");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates the send button to handle user input and updates the response label.
     *
     * @param inputField The input field where users enter their commands.
     * @return The send button.
     */
    private Button createSendButton(TextField inputField) {
        Button sendButton = new Button("Send");
        sendButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");  // Green background with white text
        sendButton.setOnAction(e -> {
            String userInput = inputField.getText(); // Get user input
            String response = ghost.getResponse(userInput, responseLabel); // Get response from Ghost
            responseLabel.setText(response); // Update the response in the label
            inputField.clear(); // Clear input field for next command
        });
        return sendButton;
    }

    /**
     * Launches the GhostTask application.
     * <p>
     * This is the entry point for running the application from the command line.
     * </p>
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
