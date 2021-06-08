package Errors;

public enum ErrorCode {

    // Weather App
    BAD_PARAMETER(100),
    CITY_NOT_FOUND(200),
    CONNEXION_ERROR(201),

    // Contact App
    SAVE_ERROR(200),
    FIRSTNAME_EMPTY(201),
    LASTNAME_EMPTY(202),
    PHONENUMBER_EMPTY(203),
    PHONENUMBER_INVALID(204),
    CONTACT_ALREADY_EXISTS(205),
    BIRTHDATE_INVALID(206);

    // Gallery App


    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
