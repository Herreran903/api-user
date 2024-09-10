package com.api_user.user.domain.auth.usecase;

import com.api_user.user.domain.auth.exception.AuthExceptionMessage;
import com.api_user.user.domain.auth.exception.ex.AuthInvalidCredentialsException;
import com.api_user.user.domain.auth.exception.ex.AuthNotValidFieldException;
import com.api_user.user.domain.auth.model.Auth;
import com.api_user.user.domain.auth.spi.IAuthAdapterPort;
import com.api_user.user.domain.auth.util.AuthConstants;
import com.api_user.user.domain.user.model.User;
import com.api_user.user.domain.user.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Map;
import java.util.Optional;

import static com.api_user.user.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthUseCaseTest {

    @Mock
    private IAuthAdapterPort adapterPort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private AuthUseCase authUseCase;

    private Auth auth;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        auth = new Auth(VALID_USER_EMAIL, VALID_USER_PASSWORD);
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty(){
        auth.setEmail("");

        AuthNotValidFieldException exception = assertThrows(AuthNotValidFieldException.class,
                ()-> authUseCase.authenticate(auth));

        assertEquals(AuthExceptionMessage.BAD_CREDENTIALS, exception.getMessage());
        assertFalse(exception.getErrors().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull(){
        auth.setEmail(null);

        AuthNotValidFieldException exception = assertThrows(AuthNotValidFieldException.class,
                ()-> authUseCase.authenticate(auth));

        assertEquals(AuthExceptionMessage.BAD_CREDENTIALS, exception.getMessage());
        assertFalse(exception.getErrors().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsEmpty(){
        auth.setPassword("");

        AuthNotValidFieldException exception = assertThrows(AuthNotValidFieldException.class,
                ()-> authUseCase.authenticate(auth));

        assertEquals(AuthExceptionMessage.BAD_CREDENTIALS, exception.getMessage());
        assertFalse(exception.getErrors().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNull(){
        auth.setPassword(null);

        AuthNotValidFieldException exception = assertThrows(AuthNotValidFieldException.class,
                ()-> authUseCase.authenticate(auth));

        assertEquals(AuthExceptionMessage.BAD_CREDENTIALS, exception.getMessage());
        assertFalse(exception.getErrors().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {
        when(userPersistencePort.getUserByEmail(VALID_USER_EMAIL)).thenReturn(Optional.empty());

        AuthInvalidCredentialsException exception = assertThrows(AuthInvalidCredentialsException.class,
                () -> authUseCase.authenticate(auth));

        assertEquals(AuthExceptionMessage.BAD_CREDENTIALS, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAuthAdapterPortThrowsException() {
        doThrow(new BadCredentialsException(AuthExceptionMessage.BAD_CREDENTIALS))
                .when(adapterPort).authenticate(auth);

        BadCredentialsException exception = assertThrows(BadCredentialsException.class,
                () -> authUseCase.authenticate(auth));

        assertEquals(AuthExceptionMessage.BAD_CREDENTIALS, exception.getMessage());
    }

    @Test
    void shouldCallAuthAdapterPortAuthenticate() {
        when(userPersistencePort.getUserByEmail(VALID_USER_EMAIL)).thenReturn(Optional.of(mock(User.class)));

        authUseCase.authenticate(auth);

        verify(adapterPort, times(1)).authenticate(auth);
    }

    @Test
    void shouldGenerateTokenSuccessfully() {
        User user = new User();
        user.setId(VALID_USER_ID);
        user.setEmail(VALID_USER_EMAIL);

        when(userPersistencePort.getUserByEmail(auth.getEmail())).thenReturn(Optional.of(user));

        String expectedToken = "generatedToken";
        when(adapterPort.generateToken(anyMap(), eq(user.getEmail()))).thenReturn(expectedToken);

        String actualToken = authUseCase.authenticate(auth);

        assertEquals(expectedToken, actualToken);

        ArgumentCaptor<Map<String, Object>> claimsCaptor = ArgumentCaptor.forClass(Map.class);
        verify(adapterPort).generateToken(claimsCaptor.capture(), eq(user.getEmail()));

        Map<String, Object> capturedClaims = claimsCaptor.getValue();
        assertEquals(user.getId(), capturedClaims.get(AuthConstants.ID));
    }

}