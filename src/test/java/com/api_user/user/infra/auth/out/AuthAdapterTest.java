package com.api_user.user.infra.auth.out;

import com.api_user.user.domain.auth.model.Auth;
import com.api_user.user.domain.auth.util.AuthConstants;
import com.api_user.user.domain.role.util.RoleEnum;
import com.api_user.user.infra.security.jwt.JwtService;
import com.api_user.user.infra.security.userdetail.UserDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.api_user.user.utils.TestConstants.VALID_USER_EMAIL;
import static com.api_user.user.utils.TestConstants.VALID_USER_PASSWORD;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class AuthAdapterTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailServiceImpl userDetailService;

    @InjectMocks
    private AuthAdapter authAdapter;

    private Auth auth;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        auth = new Auth(VALID_USER_EMAIL, VALID_USER_PASSWORD);
    }

    @Test
    void shouldAuthenticateSuccessfully() {
        authAdapter.authenticate(auth);

        verify(authenticationManager).authenticate(
                argThat(authenticationToken ->
                        authenticationToken instanceof UsernamePasswordAuthenticationToken &&
                                authenticationToken.getPrincipal().equals(auth.getEmail()) &&
                                authenticationToken.getCredentials().equals(auth.getPassword())
                )
        );
    }

    @Test
    void shouldGenerateTokenSuccessfully() {
        UserDetails userDetails = mock(UserDetails.class);
        Map<String, Object> claims = new HashMap<>();
        String expectedToken = "generatedToken";
        claims.put(AuthConstants.ROLES, RoleEnum.ROLE_ADMIN.toString());

        when(userDetailService.loadUserByUsername(VALID_USER_EMAIL)).thenReturn(userDetails);
        when(userDetails.getAuthorities()).thenReturn(Collections.emptyList());
        when(jwtService.generateToken(anyMap(), eq(userDetails))).thenReturn(expectedToken);

        String actualToken = authAdapter.generateToken(claims, VALID_USER_EMAIL);

        assertEquals(expectedToken, actualToken);
        assertTrue(claims.containsKey(AuthConstants.ROLES));
        assertEquals(RoleEnum.ROLE_ADMIN.toString(), claims.get(AuthConstants.ROLES).toString());
        verify(userDetailService, times(1)).loadUserByUsername(VALID_USER_EMAIL);
        verify(jwtService, times(1)).generateToken(claims, userDetails);
    }

}