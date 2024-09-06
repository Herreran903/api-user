package com.api_user.user.infra.exception;

import com.api_user.user.domain.error.ErrorDetail;
import com.api_user.user.domain.role.exception.ex.RoleNotFoundByNameException;
import com.api_user.user.domain.user.exception.ex.UserAlreadyExistException;
import com.api_user.user.domain.user.exception.ex.UserNotValidFieldException;
import com.api_user.user.domain.util.GlobalExceptionMessage;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class AdviceHandler {

    //User
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionDetails> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        ExceptionDetails details = new ExceptionDetails(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                "",
                LocalDateTime.now(),
                ex.getErrors()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(details);
    }

    @ExceptionHandler(UserNotValidFieldException.class)
    public ResponseEntity<ExceptionDetails> handleUserNotValidFieldException(UserNotValidFieldException ex) {
        ExceptionDetails details = new ExceptionDetails(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                "",
                LocalDateTime.now(),
                ex.getErrors()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(details);
    }

    //Role
    @ExceptionHandler(RoleNotFoundByNameException.class)
    public ResponseEntity<ExceptionDetails> handleRoleNotFoundByCodeException(RoleNotFoundByNameException ex) {
        ExceptionDetails details = new ExceptionDetails(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                "",
                LocalDateTime.now(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
    }

    //General
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDetails> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        List<ErrorDetail> errorsDetails = new ArrayList<>();

        if (ex.getCause() instanceof MismatchedInputException mie) {
            String fieldName = !mie.getPath().isEmpty() ? mie.getPath().get(0).getFieldName() : "Unknown";
            String requiredType = mie.getTargetType() != null ? mie.getTargetType().getSimpleName() : "Unknown";
            String message = String.format(GlobalExceptionMessage.INVALID_TYPE_PARAM,
                    Objects.requireNonNull(fieldName),
                    Objects.requireNonNull(requiredType));

            errorsDetails.add(new ErrorDetail(fieldName, message));
        }

        ExceptionDetails details = new ExceptionDetails(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                GlobalExceptionMessage.INVALID_JSON,
                "",
                LocalDateTime.now(),
                errorsDetails
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<ErrorDetail> errorsDetails = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorDetail(fieldError.getField(), fieldError.getDefaultMessage()))
                .distinct()
                .toList();

        ExceptionDetails details = new ExceptionDetails(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                GlobalExceptionMessage.INVALID_OBJECT,
                "",
                LocalDateTime.now(),
                errorsDetails
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionDetails> handleConstraintViolationException(ConstraintViolationException ex) {

        List<ErrorDetail> errorsDetails = ex.getConstraintViolations().stream()
                .map(constraintViolation -> {
                    String fieldName = constraintViolation.getPropertyPath().toString();
                    fieldName = fieldName.contains(".")
                            ? fieldName.substring(fieldName.lastIndexOf('.') + 1)
                            : fieldName;
                    return new ErrorDetail(fieldName, constraintViolation.getMessage());
                })
                .toList();

        ExceptionDetails details = new ExceptionDetails(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                GlobalExceptionMessage.INVALID_PARAMETERS,
                "",
                LocalDateTime.now(),
                errorsDetails
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
    }
}
