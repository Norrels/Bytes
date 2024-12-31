package com.bytes.bytes.contexts.kitchen.application.useCases.user;

import com.bytes.bytes.contexts.kitchen.domain.port.outbound.UserRepositoryPort;
import com.bytes.bytes.contexts.shared.useCases.UserExistsUseCasePort;
import org.springframework.stereotype.Service;

@Service
public class UserExistsUseCase implements UserExistsUseCasePort {

    private final UserRepositoryPort userRepositoryPort;

    public UserExistsUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }


    @Override
    public boolean execute(Long id) {
        return userRepositoryPort.findById(id).isPresent();
    }
}
