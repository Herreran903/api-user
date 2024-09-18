package com.api_user.user.app.user.handler;

import com.api_user.user.app.auth.dto.AuthResponse;
import com.api_user.user.app.user.dto.UserRequest;
import com.api_user.user.app.user.mapper.IUserRequestMapper;
import com.api_user.user.domain.role.api.IRoleServicePort;
import com.api_user.user.domain.user.api.IUserServicePort;
import com.api_user.user.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {

    private final IUserServicePort userService;
    private final IRoleServicePort roleService;
    private final IUserRequestMapper userRequestMapper;

    @Override
    public void createUserWarehouseAssistant(UserRequest userRequest) {
        User user = userRequestMapper.toUser(userRequest);

        userService.createUserWarehouseAssistant(user);
    }

    @Override
    public AuthResponse createUserClient(UserRequest userRequest){
        User user = userRequestMapper.toUser(userRequest);

        return new AuthResponse(userService.createUserClient(user));
    }
}
