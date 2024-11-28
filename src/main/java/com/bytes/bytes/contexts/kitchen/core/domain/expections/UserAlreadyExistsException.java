package com.bytes.bytes.contexts.kitchen.core.domain.expections;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Usuário já existente");
    }
}
