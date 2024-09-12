package com.api_user.user.infra.user.out;

import com.api_user.user.domain.role.model.Role;
import com.api_user.user.domain.role.util.RoleEnum;
import com.api_user.user.domain.user.model.User;
import com.api_user.user.infra.role.out.RoleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.api_user.user.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserMapper userMapper;

    @InjectMocks
    private UserAdapter userAdapter;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Role role = new Role(VALID_ROLE_ID, VALID_ROLE_NAME, VALID_ROLE_DESCRIPTION);
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

        userEntity = new UserEntity(
                VALID_USER_ID,
                VALID_USER_NAME,
                VALID_USER_LAST_NAME,
                VALID_USER_DNI,
                VALID_USER_PHONE,
                VALID_USER_BIRTHDATE,
                VALID_USER_EMAIL,
                VALID_USER_PASSWORD,
                new RoleEntity(VALID_ROLE_ID, RoleEnum.ROLE_WAREHOUSE_ASSISTANT, VALID_ROLE_DESCRIPTION)
        );
    }

    @Test
    void testIsUserPresentByDniUserExists() {
        when(userRepository.findByDni(VALID_USER_DNI)).thenReturn(Optional.of(new UserEntity()));

        Boolean result = userAdapter.isUserPresentByDni(VALID_USER_DNI);

        assertTrue(result);
        verify(userRepository, times(1)).findByDni(VALID_USER_DNI);
    }

    @Test
    void testIsUserPresentByDniUserDoesNotExist() {
        String dni = "";
        when(userRepository.findByDni(dni)).thenReturn(Optional.empty());

        Boolean result = userAdapter.isUserPresentByDni(dni);

        assertFalse(result);
        verify(userRepository, times(1)).findByDni(dni);
    }

    @Test
    void testIsUserPresentByEmailUserExists() {
        when(userRepository.findByEmail(VALID_USER_EMAIL)).thenReturn(Optional.of(new UserEntity()));

        Boolean result = userAdapter.isUserPresentByEmail(VALID_USER_EMAIL);

        assertTrue(result);
        verify(userRepository, times(1)).findByEmail(VALID_USER_EMAIL);
    }

    @Test
    void testIsUserPresentByEmailUserDoesNotExist() {
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        Boolean result = userAdapter.isUserPresentByEmail(email);

        assertFalse(result);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void shouldCreateUserSuccessfully() {
        when(userMapper.toEntity(user)).thenReturn(userEntity);

        userAdapter.createUser(user);

        verify(userMapper, times(1)).toEntity(user);
        verify(userRepository, times(1)).save(userEntity);
    }

}