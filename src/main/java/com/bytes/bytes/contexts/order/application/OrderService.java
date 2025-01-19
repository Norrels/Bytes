package com.bytes.bytes.contexts.order.application;

import com.bytes.bytes.contexts.order.application.useCases.*;
import com.bytes.bytes.contexts.order.domain.models.dtos.CreateOrderDTO;
import com.bytes.bytes.contexts.order.domain.models.Order;
import com.bytes.bytes.contexts.order.domain.models.OrderStatus;
import com.bytes.bytes.contexts.order.domain.ports.inbound.OrderServicePort;

public class OrderService implements OrderServicePort {
    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    public OrderService(CreateOrderUseCase createOrderUseCase, GetOrderByIdUseCase getOrderByIdUseCase, CancelOrderUseCase cancelOrderUseCase, UpdateOrderStatusUseCase updateOrderStatusUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
    }
    @Override
    public Order createOrder(CreateOrderDTO order) {
        return createOrderUseCase.execute(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return getOrderByIdUseCase.execute(id);
    }

    @Override
    public void cancelOrder(Long id, Long modifyById) {
        cancelOrderUseCase.execute(id);
    }

    @Override
    public void updateStatus(Long id, OrderStatus status, Long modifyById) {
        updateOrderStatusUseCase.execute(id, status, modifyById);
    }
}
