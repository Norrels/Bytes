package com.bytes.bytes.contexts.kitchen.domain.execeptions.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
}
