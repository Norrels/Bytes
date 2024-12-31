package com.bytes.bytes.contexts.kitchen.config;

import com.bytes.bytes.contexts.kitchen.application.UserService;
import com.bytes.bytes.contexts.kitchen.application.useCases.user.*;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.TokenProviderPort;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryPort userRepositoryPort) {
        return new CreateUserUseCase(userRepositoryPort);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(UserRepositoryPort userRepositoryPort) {
        return new UpdateUserUseCase(userRepositoryPort);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(UserRepositoryPort userRepositoryPort) {
        return new DeleteUserUseCase(userRepositoryPort);
    }

    @Bean
    public AuthenticateUserUseCase authenticateUserUseCase(UserRepositoryPort userRepositoryPort, TokenProviderPort tokenProviderPort) {
        return new AuthenticateUserUseCase(userRepositoryPort, tokenProviderPort);
    }

    @Bean
    public UserService userService(CreateUserUseCase createUserUseCase, UpdateUserUseCase updateUserUseCase, DeleteUserUseCase deleteUserUseCase, AuthenticateUserUseCase authenticateUserUseCase) {
        return new UserService(createUserUseCase, updateUserUseCase, deleteUserUseCase, authenticateUserUseCase);
    }
}