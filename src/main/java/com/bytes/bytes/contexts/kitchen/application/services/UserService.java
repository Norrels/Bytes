package com.bytes.bytes.contexts.kitchen.application.services;

import com.bytes.bytes.contexts.kitchen.domain.port.outbound.TokenProviderPort;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.UserRepositoryPort;
import com.bytes.bytes.contexts.kitchen.domain.execeptions.user.InvalidCredentialsException;
import com.bytes.bytes.contexts.kitchen.domain.execeptions.user.UserAlreadyExistsException;
import com.bytes.bytes.contexts.kitchen.domain.execeptions.user.UserIsNotActiveException;
import com.bytes.bytes.contexts.kitchen.domain.execeptions.user.UserNotFoundException;
import com.bytes.bytes.contexts.kitchen.domain.models.User;
import com.bytes.bytes.contexts.kitchen.utils.UserMapper;
import com.bytes.bytes.contexts.shared.dtos.UserDTO;
import com.bytes.bytes.contexts.shared.services.UserServicePort;

public class UserService implements UserServicePort {
    private final UserRepositoryPort repository;

    private final TokenProviderPort tokenProviderPort;

    private final UserMapper mapper;

    public UserService(UserRepositoryPort saveUserPort, TokenProviderPort tokenProviderPort, UserMapper mapper) {
        this.repository = saveUserPort;
        this.tokenProviderPort = tokenProviderPort;
        this.mapper = mapper;
    }
    @Override
    public UserDTO createUser(UserDTO user) {
        repository.findByEmail(user.getEmail()).ifPresent((u) -> {
            throw new UserAlreadyExistsException();
        });

        user.setActive(true);
        return mapper.UserToUserDTOMapper(repository.save(mapper.userDTOToUserMapper(user)));
    }

    @Override
    public UserDTO update(Long id, UserDTO user) {
        User user_db = repository.findById(id).orElseThrow(UserNotFoundException::new);
        user_db.setPassword(user.getPassword());
        user_db.setRole(user.getRole());
        user_db.setEmail(user.getEmail());
        user_db.setName(user.getName());
        return mapper.UserToUserDTOMapper(repository.save(mapper.userDTOToUserMapper(user)));
    }

    @Override
    public UserDTO delete(Long id) {
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setActive(false);
        return mapper.UserToUserDTOMapper(repository.save(user));
    }

    @Override
    public String autenticate(String email, String password) {
       User user = repository.findByEmail(email)
                .filter(usuario -> usuario.getPassword().equals(password))
                .orElseThrow(InvalidCredentialsException::new);

       if(!user.isActive()){
            throw new UserIsNotActiveException();
       }

        return tokenProviderPort.generate(user.getId().toString());
    }
}
