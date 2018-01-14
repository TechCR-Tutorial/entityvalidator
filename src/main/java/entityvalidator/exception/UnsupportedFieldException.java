package entityvalidator.exception;

public class UnsupportedFieldException extends Exception {
    /**
     * Throw when value not present
     *
     * @param message
     */
    public UnsupportedFieldException(String message) {
        super(message);
    }

}
