package exceptions;

public class BadYearException extends BadFormatException {
    public BadYearException() {
    }

    public BadYearException(String message) {
        super(message);
    }

    public BadYearException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadYearException(Throwable cause) {
        super(cause);
    }
}
