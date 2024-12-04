package com.bytes.bytes.contexts.kitchen.domain.execeptions.user;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Email ou senha incorretos");
    }
}
