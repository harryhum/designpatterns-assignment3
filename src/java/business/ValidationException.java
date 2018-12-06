package business;

/**
 * exception used for when validation of data has failed.
 * 
 * @author Shawn Emami
 * @author Stanley Pieda
 */
public class ValidationException extends Exception {

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ValidationException(Throwable throwable) {
        super(throwable);
    }
}
