package com.bytes.bytes.contexts.kitchen.application.useCases.user;

import com.bytes.bytes.contexts.kitchen.domain.execeptions.UserNotFoundException;
import com.bytes.bytes.contexts.kitchen.domain.models.User;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UpdateUserUseCase {
    private final UserRepositoryPort userRepository;

    private final PasswordEncoder passwordEncoder;

    public UpdateUserUseCase(UserRepositoryPort userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(Long id, User userToUpdate) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (!user.getEmail().equals(userToUpdate.getEmail())) {
            userRepository.findByEmail(userToUpdate.getEmail()).ifPresent((u) -> {
                throw new RuntimeException("O email já foi cadastrado");
            });
        }


        user.update(userToUpdate);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
