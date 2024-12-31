package com.bytes.bytes.contexts.order.adapters.outbound.persistence.repository;

import com.bytes.bytes.contexts.order.adapters.outbound.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
