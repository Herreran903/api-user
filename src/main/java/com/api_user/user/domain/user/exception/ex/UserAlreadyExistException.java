package com.api_user.user.domain.user.exception.ex;

import com.api_user.user.domain.error.ErrorDetail;

import java.util.List;

public class UserAlreadyExistException extends RuntimeException {

    private final transient List<ErrorDetail> errors;

    public UserAlreadyExistException(String message, List<ErrorDetail> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }
}
