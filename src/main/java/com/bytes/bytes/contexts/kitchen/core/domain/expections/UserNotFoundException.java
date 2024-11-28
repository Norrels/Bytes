package com.bytes.bytes.contexts.kitchen.core.domain.expections;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
}
