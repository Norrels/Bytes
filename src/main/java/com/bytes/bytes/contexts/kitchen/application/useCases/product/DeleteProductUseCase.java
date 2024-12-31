package com.bytes.bytes.contexts.kitchen.application.useCases.product;

import com.bytes.bytes.contexts.kitchen.domain.execeptions.ProductNotFoundException;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.ProductRepositoryPort;

public class DeleteProductUseCase {
    private final ProductRepositoryPort productRepository;

    public DeleteProductUseCase(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(Long id) {
        productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(id);
    }
}
