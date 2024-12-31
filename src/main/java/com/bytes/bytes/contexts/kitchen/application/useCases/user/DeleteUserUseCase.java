package com.bytes.bytes.contexts.kitchen.application.useCases.user;

import com.bytes.bytes.contexts.kitchen.domain.execeptions.UserNotFoundException;
import com.bytes.bytes.contexts.kitchen.domain.models.User;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.UserRepositoryPort;

public class DeleteUserUseCase {
    private final UserRepositoryPort userRepository;

    public DeleteUserUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setActive(false);
        return userRepository.save(user);
    }
}
