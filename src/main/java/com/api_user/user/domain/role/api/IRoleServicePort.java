package com.api_user.user.domain.role.api;

import com.api_user.user.domain.role.model.Role;
import com.api_user.user.domain.role.util.RoleEnum;

public interface IRoleServicePort {
    Role getRoleByName(RoleEnum name);
}
