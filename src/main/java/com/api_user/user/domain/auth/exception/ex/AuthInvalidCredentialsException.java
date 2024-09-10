package com.api_user.user.domain.auth.exception.ex;

public class AuthInvalidCredentialsException extends RuntimeException {
    public AuthInvalidCredentialsException(String message) {
        super(message);
    }
}
