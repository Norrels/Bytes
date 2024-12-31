package com.bytes.bytes.contexts.order.application;

import com.bytes.bytes.contexts.order.application.useCases.*;
import com.bytes.bytes.contexts.order.domain.models.dtos.CreateOrderDTO;
import com.bytes.bytes.contexts.order.domain.models.Order;
import com.bytes.bytes.contexts.order.domain.models.OrderStatus;
import com.bytes.bytes.contexts.order.domain.ports.inbound.OrderServicePort;
import com.bytes.bytes.contexts.order.mappers.OrderMappper;
import com.bytes.bytes.contexts.shared.dtos.OrderDTO;
import com.bytes.bytes.contexts.shared.PayOrderService;
import com.bytes.bytes.contexts.shared.useCases.UserExistsUseCasePort;

public class OrderService implements OrderServicePort, PayOrderService {
    private final UserExistsUseCasePort userExistsUseCasePort;
    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;
    private final PayOrderUseCase payOrderUseCase;
    private final OrderMappper orderMappper;

    public OrderService(CreateOrderUseCase createOrderUseCase, GetOrderByIdUseCase getOrderByIdUseCase, CancelOrderUseCase cancelOrderUseCase, UpdateOrderStatusUseCase updateOrderStatusUseCase, UserExistsUseCasePort userExistsUseCasePort, PayOrderUseCase payOrderUseCase, OrderMappper orderMappper) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
        this.userExistsUseCasePort = userExistsUseCasePort;
        this.payOrderUseCase = payOrderUseCase;
        this.orderMappper = orderMappper;
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
    @Override
    public void payOrder(Long orderId) {
        payOrderUseCase.execute(orderId);
    }
    @Override
    public OrderDTO getOrder(Long orderId) {
        return orderMappper.toOrderDTO(getOrderById(orderId));
    }

    public void checkIfUserExists(Long userId) {
        if(!userExistsUseCasePort.execute(userId)) {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}
