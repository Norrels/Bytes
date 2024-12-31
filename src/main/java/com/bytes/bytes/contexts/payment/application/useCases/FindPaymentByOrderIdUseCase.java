package com.bytes.bytes.contexts.payment.application.useCases;

import com.bytes.bytes.contexts.payment.domain.models.Payment;
import com.bytes.bytes.contexts.payment.domain.port.oubound.PaymentRepositoryPort;

public class FindPaymentByOrderIdUseCase {
    private final PaymentRepositoryPort paymentRepository;

    public FindPaymentByOrderIdUseCase(PaymentRepositoryPort paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment execute(Long orderId) {
       return paymentRepository.findByOrderId(orderId).orElseThrow(() -> new RuntimeException("Pagamento não encotrado para o pedido " + orderId));
    }
}
