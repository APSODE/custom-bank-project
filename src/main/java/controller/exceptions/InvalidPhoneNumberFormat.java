package controller.exceptions;

public class InvalidPhoneNumberFormat extends Exception {
    public InvalidPhoneNumberFormat(String errorMessage) {
        super(errorMessage);
    }
}
