package com.bytes.bytes.contexts.kitchen.application.useCases.user;

import com.bytes.bytes.contexts.kitchen.domain.models.User;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.UserRepositoryPort;

public class CreateUserUseCase {
    private final UserRepositoryPort userRepository;

    public CreateUserUseCase(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent((u) -> {
            throw new RuntimeException("Usuário já existente");
        });

        user.setActive(true);
        return userRepository.save(user);
    }
}
