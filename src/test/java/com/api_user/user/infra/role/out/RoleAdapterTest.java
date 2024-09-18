package com.api_user.user.infra.role.out;

import com.api_user.user.domain.role.model.Role;
import com.api_user.user.domain.role.util.RoleEnum;
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

        role = new Role(VALID_ROLE_ID, ROLE_WAREHOUSE_ASSISTANT_NAME, ROLE_WAREHOUSE_ASSISTANT_DESC);
        roleEntity = new RoleEntity(VALID_ROLE_ID, RoleEnum.ROLE_WAREHOUSE_ASSISTANT, ROLE_WAREHOUSE_ASSISTANT_DESC);
    }

    @Test
    void getRoleByNameShouldReturnRoleWhenRoleExist() {
        when(roleRepository.findByName(RoleEnum.ROLE_WAREHOUSE_ASSISTANT)).thenReturn(Optional.of(roleEntity));
        when(roleMapper.toRole(roleEntity)).thenReturn(role);

        Optional<Role> result = roleAdapter.getRoleByName(RoleEnum.ROLE_WAREHOUSE_ASSISTANT);

        assertTrue(result.isPresent());
        assertEquals(role, result.get());
        verify(roleRepository, times(1)).findByName(RoleEnum.ROLE_WAREHOUSE_ASSISTANT);
        verify(roleMapper, times(1)).toRole(roleEntity);
    }

    @Test
    void testGetRoleByName_RoleDoesNotExist() {
        when(roleRepository.findByName(RoleEnum.ROLE_WAREHOUSE_ASSISTANT)).thenReturn(Optional.empty());

        Optional<Role> result = roleAdapter.getRoleByName(RoleEnum.ROLE_WAREHOUSE_ASSISTANT);

        assertFalse(result.isPresent());
    }
}