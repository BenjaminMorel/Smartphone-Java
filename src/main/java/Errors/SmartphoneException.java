package Errors;

public class SmartphoneException extends Exception {

    private ErrorCode errorCode;
    private String errorMessage ;

    /**
     * Class qui permet de redefinir nos erreurs
     * @param errorMessage message de l'erreur
     * @param errorCode code de l'erreur
     */
    public SmartphoneException(String errorMessage, ErrorCode errorCode) {
        super(errorMessage) ;
        this.errorMessage=errorMessage ;
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode.getCode();
    }

    public String getErrorMessage(){
        return errorMessage ;
    }
}
