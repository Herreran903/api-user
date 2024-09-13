package com.api_user.user.infra.auth.out;

import com.api_user.user.domain.auth.model.Auth;
import com.api_user.user.domain.auth.spi.IAuthAdapterPort;
import com.api_user.user.infra.security.jwt.JwtService;
import com.api_user.user.infra.security.userdetail.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthAdapter implements IAuthAdapterPort {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailServiceImpl userDetailService;

    @Override
    public void authenticate(Auth auth) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        auth.getEmail(),
                        auth.getPassword()
                )
        );
    }

    @Override
    public String generateToken(Map<String, Object> claims, String username) {
        UserDetails user = userDetailService.loadUserByUsername(username);
        return jwtService.generateToken(claims, user);
    }
}
