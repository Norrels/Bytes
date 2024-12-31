package com.bytes.bytes.contexts.payment.adapters.inbound.rest;

import com.bytes.bytes.contexts.payment.adapters.inbound.dtos.PaymentDTO;
import com.bytes.bytes.contexts.payment.application.PaymentService;
import com.bytes.bytes.contexts.payment.domain.models.Payment;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Tag(name = "Payment", description = "Endpoints de pagamento")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{orderId}/pay")
    public ResponseEntity<Object> payOrder(@PathVariable Long orderId, @RequestBody PaymentDTO paymentOrder){
        try {
            Payment payment = paymentService.create(new Payment(null, orderId, paymentOrder.getPaymentType(), paymentOrder.getTotal(), paymentOrder.getExternal_id()));
            return ResponseEntity.ok().body(payment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{orderId}/status")
    public ResponseEntity<Object> statusOrder(@PathVariable Long orderId){
        try {
            Payment payment = paymentService.findByOrderId(orderId);
            return ResponseEntity.ok(payment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
