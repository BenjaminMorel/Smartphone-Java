package Errors;

public class BusinessException extends Throwable{

    private ErrorCode errorCode;
    private String messageErreur ;

    public BusinessException(String messageErreur, ErrorCode errorCode) {
        super(messageErreur) ;
        this.errorCode = errorCode;
    }
}
