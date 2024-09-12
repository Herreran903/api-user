package com.api_user.user.app.auth.handler;

import com.api_user.user.app.auth.dto.AuthRequest;
import com.api_user.user.app.auth.dto.AuthResponse;

public interface IAuthHandler {
    AuthResponse authenticate(AuthRequest authRequest);
}
