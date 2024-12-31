package com.bytes.bytes.contexts.order.application.useCases;

import com.bytes.bytes.contexts.order.domain.exeception.OrderNotFoundException;
import com.bytes.bytes.contexts.order.domain.ports.outbound.OrderRepositoryPort;

public class PayOrderUseCase {
    private final OrderRepositoryPort orderRepositoryPort;

    public PayOrderUseCase(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    public void execute(Long id){
        orderRepositoryPort.findById(id).orElseThrow(OrderNotFoundException::new).pay();
    }
}
