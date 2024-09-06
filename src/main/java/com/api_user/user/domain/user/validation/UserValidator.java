package com.api_user.user.domain.user.validation;

import com.api_user.user.domain.error.ErrorDetail;
import com.api_user.user.domain.user.exception.UserExceptionMessage;
import com.api_user.user.domain.user.model.User;
import com.api_user.user.domain.user.util.UserConstants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    private UserValidator() {
        throw new AssertionError();
    }

    public static List<ErrorDetail> validateUser(User user) {
        List<ErrorDetail> errors = new ArrayList<>();

        validateName(user.getName(), errors);
        validateLastname(user.getLastname(), errors);
        validateEmail(user.getEmail(), errors);
        validateDni(user.getDni(), errors);
        validatePhone(user.getPhone(), errors);
        validateBirthdate(user.getBirthdate(), errors);
        validatePassword(user.getPassword(), errors);

        return errors;
    }

    private static void validateName(String name, List<ErrorDetail> errors) {
        if (name == null || name.trim().isEmpty()) {
            errors.add(
                    new ErrorDetail(UserConstants.NAME, UserExceptionMessage.EMPTY_NAME)
            );
        }
    }

    private static void validateLastname(String lastname, List<ErrorDetail> errors) {
        if (lastname == null || lastname.trim().isEmpty()) {
            errors.add(
                    new ErrorDetail(UserConstants.LASTNAME, UserExceptionMessage.EMPTY_LASTNAME)
            );
        }
    }

    private static void validateEmail(String email, List<ErrorDetail> errors) {
        if (email == null || email.trim().isEmpty()) {
            errors.add(
                    new ErrorDetail(UserConstants.EMAIL, UserExceptionMessage.EMPTY_EMAIL)
            );
        } else if (!email.matches(UserConstants.REGEX_EMAIL)){
            errors.add(
                    new ErrorDetail(UserConstants.EMAIL, UserExceptionMessage.INVALID_EMAIL)
            );
        }
    }

    private static void validateDni(String dni, List<ErrorDetail> errors) {
        if (dni == null || !dni.matches(UserConstants.REGEX_DNI)) {
            errors.add(
                    new ErrorDetail(UserConstants.DNI, UserExceptionMessage.INVALID_DNI)
            );
        }
    }

    private static void validatePhone(String phone, List<ErrorDetail> errors) {
        if (phone == null || !phone.matches(UserConstants.REGEX_PHONE_NUMBER)) {
            errors.add(
                    new ErrorDetail(UserConstants.PHONE, UserExceptionMessage.INVALID_PHONE)
            );
        }
    }

    private static void validateBirthdate(LocalDate birthdate, List<ErrorDetail> errors) {
        if (birthdate == null) {
            errors.add(
                    new ErrorDetail(UserConstants.BIRTHDATE, UserExceptionMessage.EMPTY_BIRTHDATE)
            );
        } else if (birthdate.isAfter(LocalDate.now().minusYears(18))) {
            errors.add(
                    new ErrorDetail(UserConstants.BIRTHDATE, UserExceptionMessage.NOT_OLD_ENOUGH)
            );
        }
    }

    private static void validatePassword(String password, List<ErrorDetail> errors) {
        if (password == null || password.trim().isEmpty()) {
            errors.add(
                    new ErrorDetail(UserConstants.PASSWORD, UserExceptionMessage.EMPTY_PASSWORD)
            );
        }
    }
}
