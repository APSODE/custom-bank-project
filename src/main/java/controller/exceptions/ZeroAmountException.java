package controller.exceptions;

public class ZeroAmountException extends Exception {
    public ZeroAmountException(String errorMessage) {
        super(errorMessage);
    }
}
