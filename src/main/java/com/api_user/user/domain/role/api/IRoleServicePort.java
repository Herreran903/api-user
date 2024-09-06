package com.api_user.user.domain.role.api;

import com.api_user.user.domain.role.model.Role;

public interface IRoleServicePort {
    Role getRoleByName(String code);
}
