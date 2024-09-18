package com.api_user.user.domain.user.util;

public class UserConstants {

    private UserConstants() {
        throw new AssertionError();
    }

    public static final int MAX_PHONE_NUMBER = 13;
    public static final int MIN_PHONE_NUMBER = 9;
    public static final int MAX_DNI = 13;
    public static final int MIN_DIN = 9;
    public static final String REGEX_PHONE_NUMBER = "^\\+?[0-9]{9,13}$";
    public static final String REGEX_DNI = "^[0-9]{9,13}$";
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static final String NAME = "name";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String DNI = "dni";
    public static final String PHONE = "phone";
    public static final String BIRTHDATE = "birthdate";
    public static final String PASSWORD = "password";

    public static final String USER_TABLE = "users";
    public static final String USER_ROLE_COLUMN = "role_id";

}
