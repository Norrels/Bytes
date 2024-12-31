package com.bytes.bytes.contexts.order.application;

import com.bytes.bytes.contexts.order.application.useCases.*;
import com.bytes.bytes.contexts.order.domain.models.dtos.CreateOrderDTO;
import com.bytes.bytes.contexts.order.domain.models.Order;
import com.bytes.bytes.contexts.order.domain.models.OrderStatus;
import com.bytes.bytes.contexts.order.domain.ports.inbound.OrderServicePort;
import com.bytes.bytes.contexts.order.mappers.OrderMappper;
import com.bytes.bytes.contexts.shared.useCases.UserExistsUseCasePort;

public class OrderService implements OrderServicePort {
    private final UserExistsUseCasePort userExistsUseCasePort;
    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    public OrderService(CreateOrderUseCase createOrderUseCase, GetOrderByIdUseCase getOrderByIdUseCase, CancelOrderUseCase cancelOrderUseCase, UpdateOrderStatusUseCase updateOrderStatusUseCase, UserExistsUseCasePort userExistsUseCasePort) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
        this.userExistsUseCasePort = userExistsUseCasePort;
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
        checkIfUserExists(modifyById);
        cancelOrderUseCase.execute(id);
    }

    @Override
    public void updateStatus(Long id, OrderStatus status, Long modifyById) {
        checkIfUserExists(modifyById);
        updateOrderStatusUseCase.execute(id, status, modifyById);
    }

    public void checkIfUserExists(Long userId) {
        if(!userExistsUseCasePort.execute(userId)) {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}
