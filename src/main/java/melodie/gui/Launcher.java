package melodie.gui;

import javafx.application.Application;

/**
 * Provides a non-JavaFX entry point that launches Melodie's graphical interface.
 */
public final class Launcher {

    private Launcher() {
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args Command-line arguments passed to JavaFX.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
