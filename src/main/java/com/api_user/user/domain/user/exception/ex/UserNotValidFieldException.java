package com.api_user.user.domain.user.exception.ex;

import com.api_user.user.domain.error.ErrorDetail;

import java.io.Serializable;
import java.util.List;

public class UserNotValidFieldException extends RuntimeException implements Serializable {

    private final transient List<ErrorDetail> errors;

    public UserNotValidFieldException(String message, List<ErrorDetail> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }
}
