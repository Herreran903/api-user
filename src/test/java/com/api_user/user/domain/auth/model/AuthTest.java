package com.api_user.user.domain.auth.model;

import org.junit.jupiter.api.Test;

import static com.api_user.user.utils.TestConstants.VALID_USER_EMAIL;
import static com.api_user.user.utils.TestConstants.VALID_USER_PASSWORD;
import static org.junit.jupiter.api.Assertions.*;

class AuthTest {

    @Test
    void shouldUpdateAuthSuccessfully(){
        Auth auth = new Auth(null, null);

        auth.setEmail(VALID_USER_EMAIL);
        auth.setPassword(VALID_USER_PASSWORD);

        assertEquals(VALID_USER_EMAIL, auth.getEmail());
        assertEquals(VALID_USER_PASSWORD, auth.getPassword());
    }

}