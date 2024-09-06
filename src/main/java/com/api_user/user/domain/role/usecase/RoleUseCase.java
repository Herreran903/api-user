package com.api_user.user.domain.role.usecase;

import com.api_user.user.domain.role.api.IRoleServicePort;
import com.api_user.user.domain.role.exception.RoleExceptionMessage;
import com.api_user.user.domain.role.exception.ex.RoleNotFoundByNameException;
import com.api_user.user.domain.role.model.Role;
import com.api_user.user.domain.role.spi.IRolePersistencePort;

public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Role getRoleByName(String code) {
        return rolePersistencePort.getRoleByName(code).
                orElseThrow(() -> new RoleNotFoundByNameException(RoleExceptionMessage.NOT_FOUND_ROLE));
    }
}
