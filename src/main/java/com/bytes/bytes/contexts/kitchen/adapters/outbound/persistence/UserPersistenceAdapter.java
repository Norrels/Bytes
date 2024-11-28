package com.bytes.bytes.contexts.kitchen.adapters.outbound.persistence;

import com.bytes.bytes.contexts.kitchen.utils.UserMapper;
import com.bytes.bytes.contexts.kitchen.adapters.outbound.persistence.entities.UserEntity;
import com.bytes.bytes.contexts.kitchen.adapters.outbound.persistence.repositories.UserRepositoryAdapter;
import com.bytes.bytes.contexts.kitchen.core.application.port.outbound.UserServicePort;
import com.bytes.bytes.contexts.kitchen.core.domain.models.User;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserPersistenceAdapter implements UserServicePort {
    private final UserRepositoryAdapter repository;

    private final UserMapper userMapper;

    public UserPersistenceAdapter(UserRepositoryAdapter repository, UserMapper userMapper) {
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
