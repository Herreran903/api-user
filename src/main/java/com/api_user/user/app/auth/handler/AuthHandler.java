package com.api_user.user.app.auth.handler;

import com.api_user.user.app.auth.dto.AuthRequest;
import com.api_user.user.app.auth.dto.AuthResponse;
import com.api_user.user.app.auth.mapper.IAuthRequestMapper;
import com.api_user.user.domain.auth.api.IAuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthHandler implements IAuthHandler {

    private final IAuthServicePort authServicePort;
    private final IAuthRequestMapper authRequestMapper;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        return new AuthResponse(authServicePort.authenticate(authRequestMapper.toAuth(authRequest)));
    }
}
