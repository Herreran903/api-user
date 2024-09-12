package com.api_user.user.domain.auth.exception;

public class AuthExceptionMessage {

    private AuthExceptionMessage(){
        throw new AssertionError();
    }

    public static final String BAD_CREDENTIALS = "The provided credentials are incorrect";
    public static final String BAD_ROLE = "The user's role is invalid or not properly assigned";
    public static final String EMPTY_EMAIL = "The email field cannot be empty";
    public static final String EMPTY_PASSWORD = "The password field cannot be empty";
}
