package com.api_user.user.domain.role.exception;

public class RoleExceptionMessage {

    private RoleExceptionMessage(){
        throw new AssertionError();
    }

    public static final String NOT_FOUND_ROLE = "No such role";
}
