package com.api_user.user.infra.role.out;

import com.api_user.user.domain.role.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleMapper {
    Role toRole(RoleEntity roleEntity);
    RoleEntity toRoleEntity(Role role);
}
