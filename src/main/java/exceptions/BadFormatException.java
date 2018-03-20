package exceptions;

public class BadFormatException extends Exception {
    public BadFormatException() {
    }

    public BadFormatException(String message) {
        super(message);
    }

    public BadFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadFormatException(Throwable cause) {
        super(cause);
    }
}
