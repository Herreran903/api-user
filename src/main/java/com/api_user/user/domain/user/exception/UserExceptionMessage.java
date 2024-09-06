package com.api_user.user.domain.user.exception;

import com.api_user.user.domain.user.util.UserConstants;

public class UserExceptionMessage {

    private UserExceptionMessage() {
        throw new AssertionError();
    }

    public static final String EMPTY_NAME =
            "The 'name' field is empty";

    public static final String EMPTY_LASTNAME =
            "The 'lastname' field is empty";

    public static final String EMPTY_EMAIL =
            "The 'name' field is empty";

    public static final String EMPTY_DNI =
            "The 'dni' field is empty";

    public static final String INVALID_DNI =
            "The 'dni' field is invalid";

    public static final String INVALID_DNI_LENGTH =
            "The 'dni' must be between "+ UserConstants.MIN_DIN +" and "+ UserConstants.MAX_DNI +" characters long";

    public static final String EMPTY_PHONE =
            "The 'phone' field is empty";

    public static final String INVALID_PHONE =
            "The 'phone' field is invalid";

    public static final String INVALID_PHONE_LENGTH =
            "The 'phone' must be between "+ UserConstants.MIN_PHONE_NUMBER +" and "+ UserConstants.MAX_PHONE_NUMBER +" characters long";

    public static final String EMPTY_BIRTHDATE =
            "The 'birthdate' field is empty";

    public static final String FUTURE_BIRTHDATE =
            "The 'birthdate' must be a past date";

    public static final String INVALID_EMAIL =
            "The 'email' field is invalid";

    public static final String EMPTY_PASSWORD =
            "The 'password' field is empty";

    public static final String NOT_OLD_ENOUGH =
            "The user must be at least 18 years old";

    public static final String USER_ALREADY_EXISTS_DNI =
            "A user with DNI '%s' already exists";

    public static final String USER_ALREADY_EXISTS_EMAIL =
            "A user with email '%s' already exists";
}
