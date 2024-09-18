package com.api_user.user.infra.auth.util;

public class AuthSwaggerMessages {

    private AuthSwaggerMessages() {
        throw new AssertionError();
    }

    public static final String LOGIN_SUMMARY = "Login";
    public static final String LOGIN_DESCRIPTION = "Authenticate a user and return an authentication token";
    public static final String LOGIN_REQUEST_BODY_DESCRIPTION = "Request body to login:";

    public static final String LOGIN_REQUEST_EXAMPLE =
            "{ \"email\": \"john.doe@example.com\", " +
            "\"password\": \"password123\"}";

    public static final String RESPONSE_200_DESCRIPTION = "Successfully login";
}
