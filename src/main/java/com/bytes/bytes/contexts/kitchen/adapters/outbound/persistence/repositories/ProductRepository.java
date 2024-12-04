package com.bytes.bytes.contexts.kitchen.adapters.outbound.persistence.repositories;

import com.bytes.bytes.contexts.kitchen.adapters.outbound.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
