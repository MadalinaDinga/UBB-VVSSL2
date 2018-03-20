package exceptions;

public class BadMonthException extends BadFormatException {
    public BadMonthException() {
    }

    public BadMonthException(String message) {
        super(message);
    }

    public BadMonthException(String message, Throwable cause) {
        super(message, cause);
    }
}
