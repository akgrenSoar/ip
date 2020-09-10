package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

/**
 * A speech bubble that can be displayed in the chatbox
 */
public class DialogBox extends HBox {

    private final Label text;
    private final ImageView displayPicture;

    /**
     * Generate a DialogBox
     * @param label A label containing message text
     * @param imageView The user's picture
     */
    public DialogBox(Label label, ImageView imageView) {
        this.text = label;
        this.displayPicture = imageView;

        this.text.setWrapText(true);
        this.displayPicture.setFitWidth(100.0);
        this.displayPicture.setFitHeight(100.0);

        // Source: https://stackoverflow.com/a/20490028/6943913
        Rectangle clip = new Rectangle(
                imageView.boundsInParentProperty().get().getWidth(),
                imageView.boundsInParentProperty().get().getHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        this.displayPicture.setClip(clip);

        this.setSpacing(15);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.text, this.displayPicture);
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
     * @param label A label containing user's input
     * @param imageView The user's profile picture
     * @return The user's speech bubble
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    /**
     * Generate a duke speech bubble
     * @param label A label containing duke's output
     * @param imageView The duke's profile picture
     * @return The duke's speech bubble
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        DialogBox dialogBox = new DialogBox(label, imageView);
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Generate a error message speech bubble
     * @param label A label containing error message
     * @param imageView The error message's profile picture
     * @return The error message's speech bubble
     */
    public static DialogBox getErrorDialog(Label label, ImageView imageView) {
        DialogBox dialogBox = new DialogBox(label, imageView);
        dialogBox.flip();
        dialogBox.setStyle("-fx-background-color: #ffcccc");
        return dialogBox;
    }
}
