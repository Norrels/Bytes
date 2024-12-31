package com.bytes.bytes.contexts.kitchen.application.useCases.product;

import com.bytes.bytes.contexts.kitchen.domain.execeptions.UserNotFoundException;
import com.bytes.bytes.contexts.kitchen.domain.models.Product;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.ProductRepositoryPort;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.UserRepositoryPort;

public class CreateProductUseCase {
    private final ProductRepositoryPort productRepository;
    private final UserRepositoryPort userRepository;

    public CreateProductUseCase(ProductRepositoryPort productRepository, UserRepositoryPort userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Product execute(Product product) {
        userRepository.findById(product.getCreatedById()).orElseThrow(UserNotFoundException::new);
        return productRepository.save(product);
    }
}
