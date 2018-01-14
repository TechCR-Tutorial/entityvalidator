package entityvalidator.exception;

public class ValueNotPresentException extends Exception {

    /**
     * Throw when value not present
     *
     * @param message
     */
    public ValueNotPresentException(String message) {
        super(message);
    }

}
