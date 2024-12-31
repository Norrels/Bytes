package com.bytes.bytes.contexts.payment.application;

import com.bytes.bytes.contexts.payment.application.useCases.CreatePaymentUseCase;
import com.bytes.bytes.contexts.payment.application.useCases.FindPaymentByOrderIdUseCase;
import com.bytes.bytes.contexts.payment.domain.models.Payment;
import com.bytes.bytes.contexts.payment.domain.port.inbound.PaymentServicePort;
import com.bytes.bytes.contexts.shared.useCases.*;

public class PaymentService implements PaymentServicePort {
    private final CreatePaymentUseCase createPaymentUseCase;
    private final FindPaymentByOrderIdUseCase findPaymentByOrderIdUseCase;
    private final GetOrderDTOByIdUseCasePort getOrderDTOByIdUseCase;
    private final PayOrderUseCasePort payOrderUseCase;

    public PaymentService(CreatePaymentUseCase createPaymentUseCase, FindPaymentByOrderIdUseCase findPaymentByOrderIdUseCase, GetOrderDTOByIdUseCasePort getOrderDTOByIdUseCase, PayOrderUseCasePort payOrderUseCase) {
        this.createPaymentUseCase = createPaymentUseCase;
        this.findPaymentByOrderIdUseCase = findPaymentByOrderIdUseCase;
        this.getOrderDTOByIdUseCase = getOrderDTOByIdUseCase;
        this.payOrderUseCase = payOrderUseCase;
    }

    @Override
    public Payment create(Payment payment) {
        payOrderUseCase.execute(payment.getOrderId());
        return createPaymentUseCase.execute(payment);
    }

    @Override
    public Payment findByOrderId(Long orderId) {
        return findPaymentByOrderIdUseCase.execute(orderId);
    }
}
