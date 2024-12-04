package com.bytes.bytes.contexts.kitchen.domain.execeptions.user;

public class UserIsNotActiveException extends RuntimeException {
    public UserIsNotActiveException() {
        super("Usuário não está ativo");
    }
}
