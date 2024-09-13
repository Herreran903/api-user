package com.api_user.user.domain.role.model;

import org.junit.jupiter.api.Test;

import static com.api_user.user.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleTest {

    @Test
    void shouldUpdateRoleSuccessfully() {
        Role role = new Role(2L, null, null);

        role.setId(VALID_ROLE_ID);
        role.setName(ROLE_WAREHOUSE_ASSISTANT_NAME);
        role.setDescription(ROLE_WAREHOUSE_ASSISTANT_DESC);

        assertEquals(VALID_ROLE_ID, role.getId());
        assertEquals(ROLE_WAREHOUSE_ASSISTANT_NAME, role.getName());
        assertEquals(ROLE_WAREHOUSE_ASSISTANT_DESC, role.getDescription());
    }
}