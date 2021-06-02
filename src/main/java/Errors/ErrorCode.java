package Errors;

public enum ErrorCode {

    BAD_PARAMETER(100),

    // 200 - 300 = weather application
    CITY_NOT_FOUND(200),
    CONNEXION_ERROR(201);



    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
