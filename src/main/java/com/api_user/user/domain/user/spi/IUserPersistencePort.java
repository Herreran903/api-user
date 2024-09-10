package com.api_user.user.domain.user.spi;

import com.api_user.user.domain.user.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    Boolean isUserPresentByDni(String dni);
    Boolean isUserPresentByEmail(String email);
    void createUser(User user);
    Optional<User> getUserByEmail(String email);
}
