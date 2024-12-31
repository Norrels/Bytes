package com.bytes.bytes.contexts.kitchen.application.useCases.user;

import com.bytes.bytes.contexts.kitchen.domain.models.User;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.TokenProviderPort;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.UserRepositoryPort;

public class AuthenticateUserUseCase {
    private final UserRepositoryPort userRepository;
    private final TokenProviderPort tokenProviderPort;

    public AuthenticateUserUseCase(UserRepositoryPort userRepository, TokenProviderPort tokenProviderPort) {
        this.userRepository = userRepository;
        this.tokenProviderPort = tokenProviderPort;
    }

    public String execute(String email, String password) {
        User user = userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Email ou senha incorretos"));

        if (!user.isActive()) {
            throw new RuntimeException("Usuário inativo");
        }

        return tokenProviderPort.generate(user.getId().toString());
    }
}
