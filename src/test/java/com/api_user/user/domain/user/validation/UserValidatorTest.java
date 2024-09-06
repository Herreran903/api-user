package com.api_user.user.domain.user.validation;

import com.api_user.user.domain.error.ErrorDetail;
import com.api_user.user.domain.user.exception.UserExceptionMessage;
import com.api_user.user.domain.user.model.User;
import com.api_user.user.domain.user.util.UserConstants;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    @Test
    void shouldReturnErrorWhenNameIsNullOrEmpty() {
        User user = new User.Builder()
                .name("")
                .build();

        List<ErrorDetail> errors = UserValidator.validateUser(user);

        assertTrue(errors.stream().anyMatch(error -> error.getField().equals(UserConstants.NAME) &&
                error.getMessage().equals(UserExceptionMessage.EMPTY_NAME)));
    }

    @Test
    void shouldReturnErrorWhenLastnameIsNullOrEmpty() {
        User user = new User.Builder()
                .lastname("")
                .build();

        List<ErrorDetail> errors = UserValidator.validateUser(user);

        assertTrue(errors.stream().anyMatch(error -> error.getField().equals(UserConstants.LASTNAME) &&
                error.getMessage().equals(UserExceptionMessage.EMPTY_LASTNAME)));
    }

    @Test
    void shouldReturnErrorWhenEmailIsInvalid() {
        User user = new User.Builder()
                .email("invalid_email")
                .build();

        List<ErrorDetail> errors = UserValidator.validateUser(user);

        assertTrue(errors.stream().anyMatch(error -> error.getField().equals(UserConstants.EMAIL) &&
                error.getMessage().equals(UserExceptionMessage.INVALID_EMAIL)));
    }

    @Test
    void shouldReturnErrorWhenDniIsInvalid() {
        User user = new User.Builder()
                .dni("123")
                .build();

        List<ErrorDetail> errors = UserValidator.validateUser(user);

        assertTrue(errors.stream().anyMatch(error -> error.getField().equals(UserConstants.DNI) &&
                error.getMessage().equals(UserExceptionMessage.INVALID_DNI)));
    }

    @Test
    void shouldReturnErrorWhenPhoneIsInvalid() {
        User user = new User.Builder()
                .phone("123")
                .build();

        List<ErrorDetail> errors = UserValidator.validateUser(user);

        assertTrue(errors.stream().anyMatch(error -> error.getField().equals(UserConstants.PHONE) &&
                error.getMessage().equals(UserExceptionMessage.INVALID_PHONE)));
    }

    @Test
    void shouldReturnErrorWhenBirthdateIsInvalid() {
        User user = new User.Builder()
                .birthdate(LocalDate.now())
                .build();

        List<ErrorDetail> errors = UserValidator.validateUser(user);

        assertTrue(errors.stream().anyMatch(error -> error.getField().equals(UserConstants.BIRTHDATE) &&
                error.getMessage().equals(UserExceptionMessage.NOT_OLD_ENOUGH)));
    }

    @Test
    void shouldReturnErrorWhenPasswordIsEmpty() {
        User user = new User.Builder()
                .password("")
                .build();

        List<ErrorDetail> errors = UserValidator.validateUser(user);

        assertTrue(errors.stream().anyMatch(error -> error.getField().equals(UserConstants.PASSWORD) &&
                error.getMessage().equals(UserExceptionMessage.EMPTY_PASSWORD)));
    }
}