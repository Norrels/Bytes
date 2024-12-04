package com.bytes.bytes.contexts.kitchen.domain.port.outbound;

import com.bytes.bytes.contexts.kitchen.domain.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findByCategory(String category);
    void delete(Long id);
}
