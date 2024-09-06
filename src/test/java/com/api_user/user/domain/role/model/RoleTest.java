package com.api_user.user.domain.role.model;

import org.junit.jupiter.api.Test;

import static com.api_user.user.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleTest {

    @Test
    void shouldUpdateRoleSuccessfully() {
        Role role = new Role(2L, null, null);

        role.setId(VALID_ROLE_ID);
        role.setName(VALID_ROLE_NAME);
        role.setDescription(VALID_ROLE_DESCRIPTION);

        assertEquals(VALID_ROLE_ID, role.getId());
        assertEquals(VALID_ROLE_NAME, role.getName());
        assertEquals(VALID_ROLE_DESCRIPTION, role.getDescription());
    }
}