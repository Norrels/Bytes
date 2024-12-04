package com.bytes.bytes.contexts.kitchen.domain.execeptions.user;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Usuário já existente");
    }
}
