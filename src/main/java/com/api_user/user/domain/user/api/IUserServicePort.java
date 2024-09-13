package com.api_user.user.domain.user.api;

import com.api_user.user.domain.user.model.User;

public interface IUserServicePort {
    void createUserWarehouseAssistant(User user);
    String createUserClient(User user);
}
