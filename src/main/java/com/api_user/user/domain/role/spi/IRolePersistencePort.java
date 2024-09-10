package com.api_user.user.domain.role.spi;

import com.api_user.user.domain.role.model.Role;
import com.api_user.user.domain.role.util.RoleEnum;

import java.util.Optional;

public interface IRolePersistencePort {
    Optional<Role> getRoleByName(RoleEnum name);
}
