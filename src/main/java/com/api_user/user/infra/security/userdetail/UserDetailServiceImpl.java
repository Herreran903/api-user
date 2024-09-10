package com.api_user.user.infra.security.userdetail;

import com.api_user.user.domain.auth.exception.AuthExceptionMessage;
import com.api_user.user.domain.auth.exception.ex.AuthInvalidCredentialsException;
import com.api_user.user.infra.user.out.IUserRepository;
import com.api_user.user.infra.user.out.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new AuthInvalidCredentialsException(AuthExceptionMessage.BAD_CREDENTIALS));

        List<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(userEntity.getRole().getNameString()));

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}
