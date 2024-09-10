package com.api_user.user.infra.role.out;

import com.api_user.user.domain.role.model.Role;
import com.api_user.user.domain.role.spi.IRolePersistencePort;
import com.api_user.user.domain.role.util.RoleEnum;

import java.util.Optional;

public class RoleAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;

    public RoleAdapter(IRoleRepository roleRepository, IRoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Optional<Role> getRoleByName(RoleEnum name) {

        return roleRepository.findByName(name).map(roleMapper::toRole);
    }
}
