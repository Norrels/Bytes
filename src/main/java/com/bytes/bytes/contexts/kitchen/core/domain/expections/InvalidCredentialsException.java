package com.bytes.bytes.contexts.kitchen.core.domain.expections;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Email ou senha incorretos");
    }
}
