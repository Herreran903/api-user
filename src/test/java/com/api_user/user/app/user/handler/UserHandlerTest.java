package com.api_user.user.app.user.handler;

import com.api_user.user.app.user.dto.UserRequest;
import com.api_user.user.app.user.mapper.IUserRequestMapper;
import com.api_user.user.domain.user.api.IUserServicePort;
import com.api_user.user.domain.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.api_user.user.utils.TestConstants.*;
import static org.mockito.Mockito.*;

class UserHandlerTest {

    @Mock
    private IUserServicePort userService;

    @Mock
    private IUserRequestMapper userRequestMapper;

    @InjectMocks
    private UserHandler userHandler;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User.Builder()
                .id(VALID_USER_ID)
                .name(VALID_USER_NAME)
                .lastname(VALID_USER_LAST_NAME)
                .dni(VALID_USER_DNI)
                .phone(VALID_USER_PHONE)
                .birthdate(VALID_USER_BIRTHDATE)
                .email(VALID_USER_EMAIL)
                .password(VALID_USER_PASSWORD)
                .build();
    }

    @Test
    void shouldCallCreateUserOnUserService() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(VALID_USER_NAME);
        userRequest.setLastname(VALID_USER_LAST_NAME);
        userRequest.setDni(VALID_USER_DNI);
        userRequest.setPhone(VALID_USER_PHONE);
        userRequest.setBirthdate(VALID_USER_BIRTHDATE);
        userRequest.setEmail(VALID_USER_EMAIL);
        userRequest.setPassword(VALID_USER_PASSWORD);


        when(userRequestMapper.toUser(userRequest)).thenReturn(user);

        userHandler.createUserWarehouseAssistant(userRequest);

        verify(userRequestMapper, times(1)).toUser(userRequest);
        verify(userService, times(1)).createUserWarehouseAssistant(user);
    }
}