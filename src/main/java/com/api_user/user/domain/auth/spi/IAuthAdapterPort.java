package com.api_user.user.domain.auth.spi;

import com.api_user.user.domain.auth.model.Auth;

import java.util.Map;

public interface IAuthAdapterPort {
    void authenticate(Auth auth);
    String generateToken(Map<String, Object> claims, String username);
}
