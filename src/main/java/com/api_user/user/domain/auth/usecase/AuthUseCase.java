package com.api_user.user.domain.auth.usecase;

import com.api_user.user.domain.auth.api.IAuthServicePort;
import com.api_user.user.domain.auth.exception.AuthExceptionMessage;
import com.api_user.user.domain.auth.exception.ex.AuthInvalidCredentialsException;
import com.api_user.user.domain.auth.exception.ex.AuthNotValidFieldException;
import com.api_user.user.domain.auth.model.Auth;
import com.api_user.user.domain.auth.spi.IAuthAdapterPort;
import com.api_user.user.domain.auth.util.AuthConstants;
import com.api_user.user.domain.error.ErrorDetail;
import com.api_user.user.domain.user.model.User;
import com.api_user.user.domain.user.spi.IUserPersistencePort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthUseCase implements IAuthServicePort {

    private final IAuthAdapterPort authAdapterPort;
    private final IUserPersistencePort userPersistencePort;

    public AuthUseCase(IAuthAdapterPort authAdapterPort, IUserPersistencePort userPersistencePort) {
        this.authAdapterPort = authAdapterPort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public String authenticate(Auth auth) {
        List<ErrorDetail> errors = new ArrayList<>();

        if (auth.getEmail() == null || auth.getEmail().trim().isEmpty())
            errors.add(new ErrorDetail(AuthConstants.EMAIL, AuthExceptionMessage.EMPTY_EMAIL));

        if (auth.getPassword() == null || auth.getPassword().trim().isEmpty())
            errors.add(new ErrorDetail(AuthConstants.PASSWORD, AuthExceptionMessage.EMPTY_PASSWORD));

        if (!errors.isEmpty())
            throw new AuthNotValidFieldException(AuthExceptionMessage.BAD_CREDENTIALS, errors);

        authAdapterPort.authenticate(auth);

        User user = userPersistencePort.getUserByEmail(auth.getEmail())
                .orElseThrow(() -> new AuthInvalidCredentialsException(AuthExceptionMessage.BAD_CREDENTIALS));

        Map<String, Object> claims = new HashMap<>();
        claims.put(AuthConstants.ID, user.getId());
        claims.put(AuthConstants.ROLES, user.getRole().getName());

        return authAdapterPort.generateToken(claims, user.getEmail());
    }
}
