package com.api_user.user.domain.role.usecase;

import com.api_user.user.domain.role.exception.RoleExceptionMessage;
import com.api_user.user.domain.role.exception.ex.RoleNotFoundByNameException;
import com.api_user.user.domain.role.model.Role;
import com.api_user.user.domain.role.spi.IRolePersistencePort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.api_user.user.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RoleUseCaseTest {

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private RoleUseCase roleUseCase;

    public RoleUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnRoleWhenRoleExists() {
        Role expectedRole = new Role(VALID_ROLE_ID, VALID_ROLE_NAME, VALID_ROLE_DESCRIPTION);
        when(rolePersistencePort.getRoleByName(VALID_ROLE_NAME)).thenReturn(Optional.of(expectedRole));

        Role actualRole = roleUseCase.getRoleByName(VALID_ROLE_NAME);

        assertNotNull(actualRole);
        assertEquals(expectedRole, actualRole);
    }

    @Test
    void shouldThrowRoleNotFoundExceptionWhenRoleDoesNotExist() {
        String roleName = "ROLE_NON_EXISTENT";
        when(rolePersistencePort.getRoleByName(roleName)).thenReturn(Optional.empty());

        RoleNotFoundByNameException exception = assertThrows(
                RoleNotFoundByNameException.class,
                () -> roleUseCase.getRoleByName(roleName)
        );

        assertEquals(RoleExceptionMessage.NOT_FOUND_ROLE, exception.getMessage());
    }

}