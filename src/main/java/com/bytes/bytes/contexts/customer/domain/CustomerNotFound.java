package com.bytes.bytes.contexts.customer.domain;

public class CustomerNotFound extends RuntimeException{
    public CustomerNotFound() {
        super("Cliente não encontrado");
    }
}
