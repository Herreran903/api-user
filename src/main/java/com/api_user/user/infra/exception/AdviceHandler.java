package com.api_user.user.infra.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class AdviceHandler {

    //General
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<ExceptionDetails> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
//        List<FieldErrorsDetails> errorsDetails = new ArrayList<>();
//
//        if (ex.getCause() instanceof MismatchedInputException mie) {
//            String fieldName = !mie.getPath().isEmpty() ? mie.getPath().get(0).getFieldName() : "Unknown";
//            String requiredType = mie.getTargetType() != null ? mie.getTargetType().getSimpleName() : "Unknown";
//            String message = String.format(GlobalExceptionMessage.INVALID_TYPE_PARAM,
//                    Objects.requireNonNull(fieldName),
//                    Objects.requireNonNull(requiredType));
//
//            errorsDetails.add(new FieldErrorsDetails(fieldName, message));
//        }
//
//        ExceptionDetails details = new ExceptionDetails(
//                HttpStatus.BAD_REQUEST.value(),
//                HttpStatus.BAD_REQUEST.getReasonPhrase(),
//                GlobalExceptionMessage.INVALID_JSON,
//                "",
//                LocalDateTime.now(),
//                errorsDetails
//        );
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ExceptionDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//
//        List<FieldErrorsDetails> errorsDetails = ex.getBindingResult().getFieldErrors().stream()
//                .map(fieldError -> new FieldErrorsDetails(fieldError.getField(), fieldError.getDefaultMessage()))
//                .distinct()
//                .toList();
//
//        ExceptionDetails details = new ExceptionDetails(
//                HttpStatus.BAD_REQUEST.value(),
//                HttpStatus.BAD_REQUEST.getReasonPhrase(),
//                GlobalExceptionMessage.INVALID_OBJECT,
//                "",
//                LocalDateTime.now(),
//                errorsDetails
//        );
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ExceptionDetails> handleConstraintViolationException(ConstraintViolationException ex) {
//
//        List<FieldErrorsDetails> errorsDetails = ex.getConstraintViolations().stream()
//                .map(constraintViolation -> {
//                    String fieldName = constraintViolation.getPropertyPath().toString();
//                    fieldName = fieldName.contains(".")
//                            ? fieldName.substring(fieldName.lastIndexOf('.') + 1)
//                            : fieldName;
//                    return new FieldErrorsDetails(fieldName, constraintViolation.getMessage());
//                })
//                .toList();
//
//        ExceptionDetails details = new ExceptionDetails(
//                HttpStatus.BAD_REQUEST.value(),
//                HttpStatus.BAD_REQUEST.getReasonPhrase(),
//                GlobalExceptionMessage.INVALID_PARAMETERS,
//                "",
//                LocalDateTime.now(),
//                errorsDetails
//        );
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
//    }
}
