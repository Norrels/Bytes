package com.bytes.bytes.contexts.kitchen.application.useCases.product;

import com.bytes.bytes.contexts.kitchen.domain.execeptions.ProductNotFoundException;
import com.bytes.bytes.contexts.kitchen.domain.models.Product;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.ProductRepositoryPort;
import com.bytes.bytes.contexts.kitchen.utils.ProductMapper;


public class UpdateProductUseCase {
    private final ProductRepositoryPort productRepository;

    public UpdateProductUseCase(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(Long id, Product productToUpdate) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.update(productToUpdate);
        productRepository.save(product);
        return product;
    }
}
