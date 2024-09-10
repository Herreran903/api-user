package com.api_user.user.domain.auth.api;

import com.api_user.user.domain.auth.model.Auth;

public interface IAuthServicePort {
    String authenticate(Auth auth);
}
