package com.bytes.bytes.contexts.kitchen.core.domain.expections;

public class UserIsNotActiveException extends RuntimeException {
    public UserIsNotActiveException() {
        super("Usuário não está ativo");
    }
}
