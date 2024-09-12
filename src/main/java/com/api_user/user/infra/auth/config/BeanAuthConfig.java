package com.api_user.user.infra.auth.config;

import com.api_user.user.domain.auth.api.IAuthServicePort;
import com.api_user.user.domain.auth.spi.IAuthAdapterPort;
import com.api_user.user.domain.auth.usecase.AuthUseCase;
import com.api_user.user.domain.user.spi.IUserPersistencePort;
import com.api_user.user.infra.auth.out.AuthAdapter;
import com.api_user.user.infra.security.jwt.JwtService;
import com.api_user.user.infra.security.userdetail.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@RequiredArgsConstructor
public class BeanAuthConfig {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailServiceImpl userDetailServiceImpl;
    private final IUserPersistencePort userPersistencePort;

    @Bean
    public IAuthAdapterPort authAdapterPort() {
        return new AuthAdapter(authenticationManager, jwtService, userDetailServiceImpl);
    }

    @Bean
    public IAuthServicePort authServicePort() {
        return new AuthUseCase(authAdapterPort(), userPersistencePort);
    }
}
