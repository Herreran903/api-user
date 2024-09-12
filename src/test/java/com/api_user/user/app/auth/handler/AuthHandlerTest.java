package com.api_user.user.app.auth.handler;

import com.api_user.user.app.auth.dto.AuthRequest;
import com.api_user.user.app.auth.mapper.IAuthRequestMapper;
import com.api_user.user.domain.auth.api.IAuthServicePort;
import com.api_user.user.domain.auth.model.Auth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.api_user.user.utils.TestConstants.VALID_USER_EMAIL;
import static com.api_user.user.utils.TestConstants.VALID_USER_PASSWORD;
import static org.mockito.Mockito.*;

class AuthHandlerTest {

    @Mock
    private IAuthServicePort authServicePort;

    @Mock
    private IAuthRequestMapper authRequestMapper;

    @InjectMocks
    private AuthHandler authHandler;

    private Auth auth;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallAuthenticateOnAuthService(){
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(VALID_USER_EMAIL);
        authRequest.setPassword(VALID_USER_PASSWORD);

        when(authRequestMapper.toAuth(authRequest)).thenReturn(auth);

        authHandler.authenticate(authRequest);

        verify(authRequestMapper, times(1)).toAuth(authRequest);
        verify(authServicePort, times(1)).authenticate(auth);
    }

}