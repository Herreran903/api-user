package com.api_user.user.app.user.dto;

import com.api_user.user.domain.user.exception.UserExceptionMessage;
import com.api_user.user.domain.user.util.UserConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = UserExceptionMessage.EMPTY_NAME)
    private String name;

    @NotBlank(message = UserExceptionMessage.EMPTY_LASTNAME)
    private String lastname;

    @NotBlank(message = UserExceptionMessage.EMPTY_DNI)
    @Size(max = UserConstants.MAX_DNI, min = UserConstants.MIN_DIN, message = UserExceptionMessage.INVALID_DNI_LENGTH)
    @Pattern(regexp = UserConstants.REGEX_DNI, message = UserExceptionMessage.INVALID_DNI)
    private String dni;

    @NotBlank(message = UserExceptionMessage.EMPTY_PHONE)
    @Size(max = UserConstants.MAX_PHONE_NUMBER, min = UserConstants.MIN_PHONE_NUMBER, message = UserExceptionMessage.INVALID_PHONE_LENGTH)
    @Pattern(regexp = UserConstants.REGEX_PHONE_NUMBER, message = UserExceptionMessage.INVALID_PHONE)
    private String phone;

    @NotNull(message = UserExceptionMessage.EMPTY_BIRTHDATE)
    @Past(message = UserExceptionMessage.FUTURE_BIRTHDATE)
    private LocalDate birthdate;

    @NotBlank(message = UserExceptionMessage.EMPTY_EMAIL)
    @Email(message = UserExceptionMessage.INVALID_EMAIL)
    private String email;

    @NotBlank(message = UserExceptionMessage.EMPTY_PASSWORD)
    private String password;
}
