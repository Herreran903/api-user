package com.api_user.user.domain.user.model;

import com.api_user.user.domain.role.model.Role;
import org.junit.jupiter.api.Test;

import static com.api_user.user.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldUpdateUserSuccessfully() {
        User user = new User.Builder().build();
        Role role = new Role(VALID_ROLE_ID, VALID_ROLE_NAME, VALID_ROLE_DESCRIPTION);

        user.setId(VALID_USER_ID);
        user.setName(VALID_USER_NAME);
        user.setLastname(VALID_USER_LAST_NAME);
        user.setDni(VALID_USER_DNI);
        user.setPhone(VALID_USER_PHONE);
        user.setBirthdate(VALID_USER_BIRTHDATE);
        user.setEmail(VALID_USER_EMAIL);
        user.setPassword(VALID_USER_PASSWORD);
        user.setRole(role);

        assertEquals(VALID_USER_ID, user.getId());
        assertEquals(VALID_USER_NAME, user.getName());
        assertEquals(VALID_USER_LAST_NAME, user.getLastname());
        assertEquals(VALID_USER_DNI, user.getDni());
        assertEquals(VALID_USER_PHONE, user.getPhone());
        assertEquals(VALID_USER_BIRTHDATE, user.getBirthdate());
        assertEquals(VALID_USER_EMAIL, user.getEmail());
        assertEquals(VALID_USER_PASSWORD, user.getPassword());
        assertEquals(VALID_USER_ROLE, user.getRole().getName());
    }
}