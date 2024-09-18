package com.api_user.user.app.auth.dto;

import com.api_user.user.domain.auth.exception.AuthExceptionMessage;
import com.api_user.user.domain.user.exception.UserExceptionMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotBlank(message = AuthExceptionMessage.EMPTY_EMAIL)
    @Email(message = UserExceptionMessage.INVALID_EMAIL)
    private String email;

    @NotBlank(message = AuthExceptionMessage.EMPTY_PASSWORD)
    private String password;
}
