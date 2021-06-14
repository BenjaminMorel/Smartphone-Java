package Errors;

/**
 * Ennumeration des codes d'erreurs
 */
public enum ErrorCode {

    IO_EXCEPTION(50),
    BAD_PARAMETER(100),
    CONNEXION_ERROR(404),
    SAVE_ERROR(200),
    JSON_FILE(300) ;

    private final int code;


    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
