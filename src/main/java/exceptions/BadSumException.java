package exceptions;

public class BadSumException extends BadFormatException {
    public BadSumException() {
    }

    public BadSumException(String message) {
        super(message);
    }

    public BadSumException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadSumException(Throwable cause) {
        super(cause);
    }
}
