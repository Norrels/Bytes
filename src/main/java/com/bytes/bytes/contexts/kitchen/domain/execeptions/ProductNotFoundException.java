package com.bytes.bytes.contexts.kitchen.domain.execeptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Produto não encontrado");
    }
}
