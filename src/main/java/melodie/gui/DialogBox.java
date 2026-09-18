package melodie.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Represents one chat row containing a message and its speaker's icon.
 */
public class DialogBox extends HBox {
    private static final double MAX_DIALOG_WIDTH_RATIO = 0.72;

    @FXML
    private Label dialog;

    @FXML
    private Label speakerIcon;

    private DialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load a dialog box", e);
        }

        this.dialog.setText(text);
        this.dialog.maxWidthProperty().bind(
                this.widthProperty().multiply(MAX_DIALOG_WIDTH_RATIO));
    }

    /**
     * Creates a right-aligned user message.
     *
     * @param text Message text.
     * @return User dialog box.
     */
    public static DialogBox getUserDialog(String text) {
        DialogBox dialogBox = new DialogBox(text);
        dialogBox.getStyleClass().add("user-dialog");
        dialogBox.dialog.getStyleClass().add("user-bubble");
        dialogBox.speakerIcon.setManaged(false);
        dialogBox.speakerIcon.setVisible(false);
        return dialogBox;
    }

    /**
     * Creates a left-aligned Melodie response.
     *
     * @param text Response text.
     * @return Melodie dialog box.
     */
    public static DialogBox getMelodieDialog(String text) {
        DialogBox dialogBox = new DialogBox(text);
        dialogBox.flip();
        dialogBox.getStyleClass().add("melodie-dialog");
        dialogBox.dialog.getStyleClass().add("melodie-bubble");
        return dialogBox;
    }

    /**
     * Creates a visually prominent Melodie error response.
     *
     * @param text Error message.
     * @return Error dialog box.
     */
    public static DialogBox getErrorDialog(String text) {
        DialogBox dialogBox = new DialogBox(text);
        dialogBox.flip();
        dialogBox.getStyleClass().add("melodie-dialog");
        dialogBox.dialog.getStyleClass().add("error-bubble");
        return dialogBox;
    }

    private void flip() {
        ObservableList<Node> children = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(children);
        this.getChildren().setAll(children);
        this.setAlignment(Pos.TOP_LEFT);
    }
}
