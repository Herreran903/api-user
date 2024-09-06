package com.api_user.user.infra.role.config;

import com.api_user.user.domain.role.api.IRoleServicePort;
import com.api_user.user.domain.role.spi.IRolePersistencePort;
import com.api_user.user.domain.role.usecase.RoleUseCase;
import com.api_user.user.infra.role.out.IRoleMapper;
import com.api_user.user.infra.role.out.IRoleRepository;
import com.api_user.user.infra.role.out.RoleAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanRoleConfig {

    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleAdapter(roleRepository, roleMapper);
    }

    @Bean
    IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }
}
