package exceptions;

public class NotExistLoanAmount extends Exception {
    public NotExistLoanAmount(String errorMessage) {
        super(errorMessage);
    }
}
