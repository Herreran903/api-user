package com.api_user.user.infra.user.config;

import com.api_user.user.domain.role.api.IRoleServicePort;
import com.api_user.user.domain.user.api.IUserServicePort;
import com.api_user.user.domain.user.spi.IPasswordEncoderPort;
import com.api_user.user.domain.user.spi.IUserPersistencePort;
import com.api_user.user.domain.user.usecase.UserUseCase;
import com.api_user.user.infra.user.out.IUserMapper;
import com.api_user.user.infra.user.out.IUserRepository;
import com.api_user.user.infra.user.out.UserAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanUserConfig {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final IRoleServicePort roleService;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdapter(userRepository, userMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), passwordEncoderPort, roleService);
    }
}
