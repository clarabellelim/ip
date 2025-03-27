package ghost;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * This class is necessary for launching the JavaFX application,
 * as it resolves potential issues with classpath configurations.
 */
public class Launcher {

    /**
     * Main method to launch the JavaFX application.
     *
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
