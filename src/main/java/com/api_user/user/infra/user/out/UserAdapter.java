package com.api_user.user.infra.user.out;

import com.api_user.user.domain.user.model.User;
import com.api_user.user.domain.user.spi.IUserPersistencePort;

import java.util.Optional;

public class UserAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    public UserAdapter(IUserRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Boolean isUserPresentByDni(String dni) {
        return userRepository.findByDni(dni).isPresent();
    }

    @Override
    public Boolean isUserPresentByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void createUser(User user) {
        userRepository.save(userMapper.toEntity(user));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toUser);
    }
}
