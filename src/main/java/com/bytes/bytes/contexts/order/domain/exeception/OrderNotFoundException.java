package com.bytes.bytes.contexts.order.domain.exeception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super("Pedido não encontrado");
    }
}
