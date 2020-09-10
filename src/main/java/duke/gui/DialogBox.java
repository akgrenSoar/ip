package duke.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A speech bubble that can be displayed in the chatbox
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Generate a DialogBox
     * @param text Message text of dialog box
     * @param img The user's picture
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

//        this.text.setWrapText(true);
//        this.displayPicture.setFitWidth(100.0);
//        this.displayPicture.setFitHeight(100.0);

        // Source: https://stackoverflow.com/a/20490028/6943913
//        Rectangle clip = new Rectangle(
//                imageView.boundsInParentProperty().get().getWidth(),
//                imageView.boundsInParentProperty().get().getHeight());
//        clip.setArcWidth(20);
//        clip.setArcHeight(20);
//        this.displayPicture.setClip(clip);

//        this.setSpacing(15);
//        this.setAlignment(Pos.TOP_RIGHT);
//        this.getChildren().addAll(this.text, this.displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Generate a user speech bubble
     * @param message Message text of user's input
     * @param image The user's profile picture
     * @return The user's speech bubble
     */
    public static DialogBox getUserDialog(String message, Image image) {
        return new DialogBox(message, image);
    }

    /**
     * Generate a duke speech bubble
     * @param message Message text of duke's output
     * @param image The duke's profile picture
     * @return The duke's speech bubble
     */
    public static DialogBox getDukeDialog(String message, Image image) {
        DialogBox dialogBox = new DialogBox(message, image);
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Generate a error message speech bubble
     * @param message Message text of error message
     * @param image The error message's profile picture
     * @return The error message's speech bubble
     */
    public static DialogBox getErrorDialog(String message, Image image) {
        DialogBox dialogBox = new DialogBox(message, image);
        dialogBox.flip();
        dialogBox.setStyle("-fx-background-color: #ffcccc");
        return dialogBox;
    }
}
