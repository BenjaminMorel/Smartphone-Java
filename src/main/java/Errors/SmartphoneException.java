package Errors;

public class SmartphoneException extends Exception {

    private ErrorCode errorCode;
    private String errorMessage ;

    public SmartphoneException(String errorMessage, ErrorCode errorCode) {
        super(errorMessage) ;
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
