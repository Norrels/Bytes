package com.bytes.bytes.contexts.kitchen.core.application.services;

import com.bytes.bytes.contexts.kitchen.core.application.port.outbound.TokenProviderPort;
import com.bytes.bytes.contexts.kitchen.core.application.port.outbound.UserServicePort;
import com.bytes.bytes.contexts.kitchen.core.domain.expections.InvalidCredentialsException;
import com.bytes.bytes.contexts.kitchen.core.domain.expections.UserAlreadyExistsException;
import com.bytes.bytes.contexts.kitchen.core.domain.expections.UserIsNotActiveException;
import com.bytes.bytes.contexts.kitchen.core.domain.expections.UserNotFoundException;
import com.bytes.bytes.contexts.kitchen.core.domain.models.User;

public class UserService {
    private final UserServicePort userServicePort;

    private final TokenProviderPort tokenProviderPort;

    public UserService(UserServicePort saveUserPort, TokenProviderPort tokenProviderPort) {
        this.userServicePort = saveUserPort;
        this.tokenProviderPort = tokenProviderPort;
    }

    public User createUser(User user) {
        userServicePort.findByEmail(user.getEmail()).ifPresent((u) -> {
            throw new UserAlreadyExistsException();
        });

        user.setActive(true);
        return userServicePort.save(user);
    }

    public User update(Long id, User user) {
        User user_db = userServicePort.findById(id).orElseThrow(UserNotFoundException::new);
        user_db.setPassword(user.getPassword());
        user_db.setRole(user.getRole());
        user_db.setEmail(user.getEmail());
        user_db.setName(user.getName());
        return userServicePort.save(user_db);
    }

    public User delete(Long id) {
        User user = userServicePort.findById(id).orElseThrow(UserNotFoundException::new);
        user.setActive(false);
        return userServicePort.save(user);
    }

    public String autenticate(String email, String password) {
       User user = userServicePort.findByEmail(email)
                .filter(usuario -> usuario.getPassword().equals(password))
                .orElseThrow(InvalidCredentialsException::new);

       if(!user.isActive()){
            throw new UserIsNotActiveException();
       }

        return tokenProviderPort.generate(user.getId().toString());
    }
}
