package com.bytes.bytes.contexts.payment.adapters.outbound.persistence.entities;

import com.bytes.bytes.contexts.payment.domain.models.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long orderId;

    @Enumerated
    private PaymentType paymentType;

    @Positive
    private BigDecimal total;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @NotNull
    private Long externalId;
}
