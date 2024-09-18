package com.api_user.user.domain.user.usecase;

import com.api_user.user.domain.auth.api.IAuthServicePort;
import com.api_user.user.domain.auth.model.Auth;
import com.api_user.user.domain.role.api.IRoleServicePort;
import com.api_user.user.domain.role.model.Role;
import com.api_user.user.domain.role.util.RoleEnum;
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

    @Mock
    private IAuthServicePort authServicePort;

    @InjectMocks
    private UserUseCase userUseCase;

    private User user;
    private Role roleWarehouse;
    private Role roleClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleWarehouse = new Role(VALID_ROLE_ID, ROLE_WAREHOUSE_ASSISTANT_NAME, ROLE_WAREHOUSE_ASSISTANT_DESC);
        roleClient = new Role(VALID_ROLE_ID, ROLE_CLIENT_NAME, ROLE_CLIENT_DESC);
        user = new User.Builder()
                .id(VALID_USER_ID)
                .name(VALID_USER_NAME)
                .lastname(VALID_USER_LAST_NAME)
                .dni(VALID_USER_DNI)
                .phone(VALID_USER_PHONE)
                .birthdate(VALID_USER_BIRTHDATE)
                .email(VALID_USER_EMAIL)
                .password(VALID_USER_PASSWORD)
                .role(roleWarehouse)
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
    void shouldCreateUserWareHouseSuccessfully() {
        when(userPersistencePort.isUserPresentByDni(user.getDni())).thenReturn(false);
        when(userPersistencePort.isUserPresentByEmail(user.getEmail())).thenReturn(false);
        when(passwordEncoderPort.encode(user.getPassword())).thenReturn("encodedPassword");
        when(roleServicePort.getRoleByName(RoleEnum.ROLE_WAREHOUSE_ASSISTANT)).thenReturn(roleWarehouse);

        userUseCase.createUserWarehouseAssistant(user);

        verify(userPersistencePort, times(1)).createUser(user);
        assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    void shouldCreateUserClientSuccessfully() {
        when(userPersistencePort.isUserPresentByDni(user.getDni())).thenReturn(false);
        when(userPersistencePort.isUserPresentByEmail(user.getEmail())).thenReturn(false);
        when(passwordEncoderPort.encode(user.getPassword())).thenReturn("encodedPassword");
        when(roleServicePort.getRoleByName(RoleEnum.ROLE_CLIENT)).thenReturn(roleClient);
        when(authServicePort.authenticate(any(Auth.class))).thenReturn("authToken");

        String authToken = userUseCase.createUserClient(user);

        verify(userPersistencePort, times(1)).createUser(user);
        verify(authServicePort).authenticate(any(Auth.class));
        assertEquals("encodedPassword", user.getPassword());
        assertEquals("authToken", authToken);
    }




}