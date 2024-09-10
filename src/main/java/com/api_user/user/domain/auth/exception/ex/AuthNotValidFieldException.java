package com.api_user.user.domain.auth.exception.ex;

import com.api_user.user.domain.error.ErrorDetail;

import java.io.Serializable;
import java.util.List;

public class AuthNotValidFieldException extends RuntimeException implements Serializable {

    private final transient List<ErrorDetail> errors;

    public AuthNotValidFieldException(String message, List<ErrorDetail> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }
}
