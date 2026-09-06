package melodie.gui;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import melodie.Melodie;

/**
 * Controls the main Melodie chat window.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private final Image userImage = this.loadImage("/images/DaUser.jpeg");
    private final Image melodieImage = this.loadImage("/images/DaMelodie.jpeg");

    private Melodie melodie;

    /**
     * Configures behaviour that depends on controls injected from FXML.
     */
    @FXML
    public void initialize() {
        this.dialogContainer.heightProperty().addListener((observable, oldHeight, newHeight) ->
                this.scrollPane.setVvalue(1.0));
    }

    /**
     * Supplies the chatbot used to process input and displays its greeting.
     *
     * @param melodie Melodie chatbot instance.
     */
    public void setMelodie(Melodie melodie) {
        this.melodie = melodie;
        this.dialogContainer.getChildren().add(
                DialogBox.getMelodieDialog(melodie.getGreeting(), this.melodieImage));
    }

    /**
     * Sends the entered command to Melodie and adds both sides of the exchange to the chat.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        String response = this.melodie.getResponse(input);
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getMelodieDialog(response, this.melodieImage));
        this.userInput.clear();
        this.userInput.requestFocus();

        if (this.melodie.isExitRequested()) {
            this.userInput.setDisable(true);
            this.sendButton.setDisable(true);
            PauseTransition exitDelay = new PauseTransition(Duration.seconds(1));
            exitDelay.setOnFinished(event -> Platform.exit());
            exitDelay.play();
        }
    }

    private Image loadImage(String path) {
        return new Image(Objects.requireNonNull(
                MainWindow.class.getResourceAsStream(path), "Missing image resource: " + path));
    }
}
