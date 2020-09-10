package duke.gui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.Utility;
import duke.core.DukeData;
import duke.core.DukeLogic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Duke extends Application {

    private static final String WELCOME_MESSAGE = "Welcome to Duke!";

    private ByteArrayOutputStream outputStream;
    private ByteArrayOutputStream errorStream;

    private DukeData dukeData = new DukeData();

    private Image userImage = new Image(Utility.getResource("images/user.jpg"));
    private Image dukeImage = new Image(Utility.getResource("images/duke.jpg"));
    private Image errorImage = new Image(Utility.getResource("images/error.jpg"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button clearButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {

        // Redirect System.out to outputStream
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Redirect System.err to errorStream
        errorStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorStream));

        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        clearButton = new Button("Clear");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(clearButton, scrollPane, userInput);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        clearDialogContainer();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 510);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setSpacing(15);

        userInput.setPrefWidth(385);
        clearButton.setPrefWidth(385);

        AnchorPane.setTopAnchor(clearButton, 1.0);
        AnchorPane.setLeftAnchor(clearButton, 0.0);

        AnchorPane.setTopAnchor(scrollPane, 26.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);

        AnchorPane.setBottomAnchor(userInput, 0.0);
        AnchorPane.setLeftAnchor(userInput , 0.0);

        // Clear chat box
        clearButton.setOnMouseClicked((event) -> clearDialogContainer());

        // Execute main logic when user press enter
        userInput.setOnAction((event) -> handleUserInput());

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void clearDialogContainer() {
        dialogContainer.getChildren().clear();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(new Label(WELCOME_MESSAGE), new ImageView(dukeImage)));
    }

    /**
     * Main program logic.
     * Executes the user input, and displays user input, duke output, and error message
     * to the chatbox
     */
    private void handleUserInput() {
        DukeLogic.execute(dukeData, userInput.getText());

        String input = userInput.getText();
        String output = outputStream.toString();
        String error = errorStream.toString();

        if (!input.isBlank()) {
            dialogContainer.getChildren().add(
                    DialogBox.getUserDialog(new Label(input), new ImageView(userImage)));
        }

        if (!output.isBlank()) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(new Label(output), new ImageView(dukeImage)));
        }

        if (!error.isBlank()) {
            dialogContainer.getChildren().add(
                    DialogBox.getErrorDialog(new Label(error), new ImageView(errorImage)));
        }

        userInput.clear();
        outputStream.reset();
        errorStream.reset();
    }

}
