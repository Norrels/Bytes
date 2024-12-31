package com.bytes.bytes.contexts.kitchen.domain.execeptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Usuário não encontrado");
    }
}
