package duke.gui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.Utility;
import duke.core.DukeData;
import duke.core.DukeLogic;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private static final String WELCOME_MESSAGE = "Welcome to Duke!";

    private final DukeData dukeData = new DukeData();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorStream = new ByteArrayOutputStream();

    private final Image userImage = new Image(Utility.getResourceAsStream("images/user.jpg"));
    private final Image dukeImage = new Image(Utility.getResourceAsStream("images/duke.jpg"));
    private final Image errorImage = new Image(Utility.getResourceAsStream("images/error.jpg"));

    @FXML
    public void initialize() {
        // Automatically scroll down
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Redirect System.out to outputStream
        System.setOut(new PrintStream(outputStream));

        // Redirect System.err to errorStream
        System.setErr(new PrintStream(errorStream));
    }

    @FXML
    private void clearDialogContainer() {
        dialogContainer.getChildren().clear();
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(WELCOME_MESSAGE, dukeImage));
    }

    /**
     * Main program logic.
     * Executes the user input, and displays user input, duke output, and error message
     * to the chatbox
     */
    @FXML
    private void handleUserInput() {
        DukeLogic.execute(dukeData, userInput.getText());

        String input = userInput.getText();
        String output = outputStream.toString();
        String error = errorStream.toString();

        if (!input.isBlank()) {
            DialogBox dialogBox = DialogBox.getUserDialog(input, userImage);
            dialogContainer.getChildren().add(dialogBox);
        }

        if (!output.isBlank()) {
            DialogBox dialogBox = DialogBox.getDukeDialog(output, dukeImage);
            dialogContainer.getChildren().add(dialogBox);
        }

        if (!error.isBlank()) {
            DialogBox dialogBox = DialogBox.getErrorDialog(error, errorImage);
            dialogContainer.getChildren().add(dialogBox);
        }

        userInput.clear();
        outputStream.reset();
        errorStream.reset();
    }

}
