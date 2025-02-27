package ghost.exception;

/**
 * Represents an exception specific to the Ghost application.
 */
public class GhostException extends Exception {

    /**
     * Constructs a GhostException with a specified error message.
     *
     * @param message The error message.
     */
    public GhostException(String message) {
        super(message);
    }
}
