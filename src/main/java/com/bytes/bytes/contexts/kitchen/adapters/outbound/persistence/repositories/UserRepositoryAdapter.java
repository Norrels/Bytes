package com.bytes.bytes.contexts.kitchen.adapters.outbound.persistence.repositories;

import com.bytes.bytes.contexts.kitchen.adapters.outbound.persistence.entities.UserEntity;
import com.bytes.bytes.contexts.kitchen.core.application.port.outbound.UserRepositoryPort;
import com.bytes.bytes.contexts.kitchen.core.domain.models.User;
import com.bytes.bytes.contexts.kitchen.utils.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {
    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserRepositoryAdapter(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        UserEntity UserEntity = userMapper.userToUserEntityMapper(user);
        return userMapper.userEntityToUserMapper(repository.save(UserEntity));
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(userMapper::userEntityToUserMapper);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(userMapper::userEntityToUserMapper);
    }

}
