package com.api_user.user.app.user.handler;

import com.api_user.user.app.auth.dto.AuthResponse;
import com.api_user.user.app.user.dto.UserRequest;

public interface IUserHandler {
    void createUserWarehouseAssistant(UserRequest userRequest);
    AuthResponse createUserClient(UserRequest userRequest);
}
