package com.api_user.user.domain.user.usecase;

import com.api_user.user.domain.role.api.IRoleServicePort;
import com.api_user.user.domain.role.model.Role;
import com.api_user.user.domain.user.exception.UserExceptionMessage;
import com.api_user.user.domain.user.exception.ex.UserAlreadyExistException;
import com.api_user.user.domain.user.exception.ex.UserNotValidFieldException;
import com.api_user.user.domain.user.model.User;
import com.api_user.user.domain.user.spi.IPasswordEncoderPort;
import com.api_user.user.domain.user.spi.IUserPersistencePort;
import com.api_user.user.domain.util.GlobalExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.api_user.user.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserUseCaseTest{

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IPasswordEncoderPort passwordEncoderPort;

    @Mock
    private IRoleServicePort roleServicePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        role = new Role(VALID_ROLE_ID, VALID_ROLE_NAME, VALID_ROLE_DESCRIPTION);
        user = new User.Builder()
                .id(VALID_USER_ID)
                .name(VALID_USER_NAME)
                .lastname(VALID_USER_LAST_NAME)
                .dni(VALID_USER_DNI)
                .phone(VALID_USER_PHONE)
                .birthdate(VALID_USER_BIRTHDATE)
                .email(VALID_USER_EMAIL)
                .password(VALID_USER_PASSWORD)
                .role(role)
                .build();
    }

    @Test
    void shouldThrowExceptionWhenUserFieldsAreInvalid() {
        User invalidUser = new User.Builder().email("").build();

        UserNotValidFieldException exception = assertThrows(UserNotValidFieldException.class,
                () -> userUseCase.createUserWarehouseAssistant(invalidUser));

        assertEquals(GlobalExceptionMessage.INVALID_OBJECT, exception.getMessage());
        assertFalse(exception.getErrors().isEmpty());
    }


    @Test
    void shouldThrowExceptionWhenUserAlreadyExistsByDni(){
        when(userPersistencePort.isUserPresentByDni(user.getDni())).thenReturn(true);

        UserAlreadyExistException exception = assertThrows(UserAlreadyExistException.class,
                () -> userUseCase.createUserWarehouseAssistant(user));

        assertEquals(String.format(UserExceptionMessage.USER_ALREADY_EXISTS_DNI, user.getDni()), exception.getErrors().get(0).getMessage());
        assertEquals(GlobalExceptionMessage.ALREADY_EXIST_OBJECT, exception.getMessage());
        verify(userPersistencePort, times(1)).isUserPresentByDni(user.getDni());
        verify(userPersistencePort, never()).createUser(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExistsByEmail() {
        when(userPersistencePort.isUserPresentByEmail(user.getEmail())).thenReturn(true);

        UserAlreadyExistException exception = assertThrows(UserAlreadyExistException.class,
                () -> userUseCase.createUserWarehouseAssistant(user));

        assertEquals(String.format(UserExceptionMessage.USER_ALREADY_EXISTS_EMAIL, user.getEmail()), exception.getErrors().get(0).getMessage());
        assertEquals(GlobalExceptionMessage.ALREADY_EXIST_OBJECT, exception.getMessage());
        verify(userPersistencePort, times(1)).isUserPresentByEmail(user.getEmail());
        verify(userPersistencePort, never()).createUser(any(User.class));
    }

    @Test
    void shouldCreateUserSuccessfully() {
        when(userPersistencePort.isUserPresentByDni(user.getDni())).thenReturn(false);
        when(userPersistencePort.isUserPresentByEmail(user.getEmail())).thenReturn(false);
        when(passwordEncoderPort.encode(user.getPassword())).thenReturn("encodedPassword");
        when(roleServicePort.getRoleByName(VALID_ROLE_NAME)).thenReturn(role);

        userUseCase.createUserWarehouseAssistant(user);

        verify(userPersistencePort, times(1)).createUser(user);
        assertEquals("encodedPassword", user.getPassword());
    }


}