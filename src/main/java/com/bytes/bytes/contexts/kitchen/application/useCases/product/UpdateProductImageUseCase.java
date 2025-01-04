package com.bytes.bytes.contexts.kitchen.application.useCases.product;

import com.bytes.bytes.contexts.kitchen.domain.execeptions.ProductNotFoundException;
import com.bytes.bytes.contexts.kitchen.domain.models.Product;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.ProductRepositoryPort;

public class UpdateProductImageUseCase {
    private final ProductRepositoryPort productRepository;

    public UpdateProductImageUseCase(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(Long id, String imageUrl) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setImgUrl(imageUrl);
        productRepository.save(product);
        return product;
    }
}
