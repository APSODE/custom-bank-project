package exceptions;

public class OverWithrawLimitException extends Exception {
    public OverWithrawLimitException(String errorMessage) {
        super(errorMessage);
    }
}
