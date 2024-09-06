package com.api_user.user.domain.role.spi;

import com.api_user.user.domain.role.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {
    Optional<Role> getRoleByName(String code);
}
