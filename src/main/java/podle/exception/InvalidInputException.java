package podle.exception;

/**
 * Represents an exception that is thrown when the user provides invalid,
 * incomplete, or unparseable input to the application.
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Constructs a new InvalidInputException with the specified detail message.
     *
     * @param message The detailed error message explaining why the input was invalid.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
