package com.api_user.user.domain.user.spi;

import com.api_user.user.domain.user.model.User;

public interface IUserPersistencePort {
    Boolean isUserPresentByDni(String dni);
    Boolean isUserPresentByEmail(String email);
    void createUser(User user);
}
