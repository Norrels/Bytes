package com.bytes.bytes.contexts.kitchen.core.application.port.outbound;

import com.bytes.bytes.contexts.kitchen.core.domain.models.User;

import java.util.Optional;

public interface UserServicePort {
    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
