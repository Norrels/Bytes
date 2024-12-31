package com.bytes.bytes.contexts.order.application.useCases;

import com.bytes.bytes.contexts.order.domain.exeception.OrderNotFoundException;
import com.bytes.bytes.contexts.order.domain.models.Order;
import com.bytes.bytes.contexts.order.domain.ports.outbound.OrderRepositoryPort;

public class CancelOrderUseCase {
    private final OrderRepositoryPort orderRepositoryPort;

    public CancelOrderUseCase(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    public void execute(Long id) {
        Order order = orderRepositoryPort.findById(id).orElseThrow(OrderNotFoundException::new);
        order.cancel();
        orderRepositoryPort.save(order);
    }
}
