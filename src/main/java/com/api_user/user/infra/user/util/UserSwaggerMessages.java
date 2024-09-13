package com.api_user.user.infra.user.util;

public class UserSwaggerMessages {

    private UserSwaggerMessages (){
        throw new AssertionError();
    }

    public static final String CREATE_WAREHOUSE_ASSISTANT_SUMMARY = "Register a new warehouse assistant";
    public static final String CREATE_WAREHOUSE_ASSISTANT_DESCRIPTION = "This endpoint allows registering a new warehouse assistant in the system.";
    public static final String CREATE_WAREHOUSE_ASSISTANT_REQUEST_BODY_DESCRIPTION = "Request body to create a new warehouse assistant";

    public static final String CREATE_CLIENT_SUMMARY = "Register a new client";
    public static final String CREATE_CLIENT_DESCRIPTION = "This endpoint allows registering a new client in the system.";
    public static final String CREATE_CLIENT_REQUEST_BODY_DESCRIPTION = "Request body to create a new client";


    public static final String USER_REQUEST_EXAMPLE = "{ \"name\": \"John\", " +
            "\"lastname\": \"Doe\", " +
            "\"dni\": \"12345678A\", " +
            "\"phone\": \"123-456-7890\", " +
            "\"birthdate\": \"1980-01-01\", " +
            "\"email\": \"john.doe@example.com\", " +
            "\"password\": \"password123\", " +
            "\"role\": \"assistant\" }";

    public static final String RESPONSE_201_DESCRIPTION = "User successfully created";
}
