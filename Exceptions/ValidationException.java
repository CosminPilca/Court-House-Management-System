package Exceptions;

/**
 * Exception for validation errors (e.g., empty fields, negative IDs).
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
