package com.api_user.user.domain.role.exception.ex;

public class RoleNotFoundByNameException extends RuntimeException {
    public RoleNotFoundByNameException(String message) {
        super(message);
    }
}
