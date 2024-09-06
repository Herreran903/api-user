package com.api_user.user.infra.role.out;

import com.api_user.user.domain.role.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.api_user.user.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleAdapterTest {

    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private IRoleMapper roleMapper;

    @InjectMocks
    private RoleAdapter roleAdapter;

    private Role role;
    private RoleEntity roleEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        role = new Role(VALID_ROLE_ID, VALID_ROLE_NAME, VALID_ROLE_DESCRIPTION);
        roleEntity = new RoleEntity(VALID_ROLE_ID, VALID_ROLE_NAME, VALID_ROLE_DESCRIPTION);
    }

    @Test
    void getRoleByNameShouldReturnRoleWhenRoleExist() {
        when(roleRepository.findByName(VALID_ROLE_NAME)).thenReturn(Optional.of(roleEntity));
        when(roleMapper.toRole(roleEntity)).thenReturn(role);

        Optional<Role> result = roleAdapter.getRoleByName(VALID_ROLE_NAME);

        assertTrue(result.isPresent());
        assertEquals(role, result.get());
        verify(roleRepository, times(1)).findByName(VALID_ROLE_NAME);
        verify(roleMapper, times(1)).toRole(roleEntity);
    }

    @Test
    void testGetRoleByName_RoleDoesNotExist() {
        when(roleRepository.findByName(VALID_ROLE_NAME)).thenReturn(Optional.empty());

        Optional<Role> result = roleAdapter.getRoleByName(VALID_ROLE_NAME);

        assertFalse(result.isPresent());
    }
}