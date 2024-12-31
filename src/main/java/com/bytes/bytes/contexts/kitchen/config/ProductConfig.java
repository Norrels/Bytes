package com.bytes.bytes.contexts.kitchen.config;

import com.bytes.bytes.contexts.kitchen.application.ProductService;
import com.bytes.bytes.contexts.kitchen.application.useCases.product.*;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.ProductRepositoryPort;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.UserRepositoryPort;
import com.bytes.bytes.contexts.kitchen.utils.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public CreateProductUseCase createProductUseCase(ProductRepositoryPort productRepositoryPort, UserRepositoryPort userRepositoryPort) {
        return new CreateProductUseCase(productRepositoryPort, userRepositoryPort);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(ProductRepositoryPort productRepositoryPort) {
        return new UpdateProductUseCase(productRepositoryPort);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(ProductRepositoryPort productRepositoryPort) {
        return new DeleteProductUseCase(productRepositoryPort);
    }

    @Bean
    public FindProductByCategoryUseCase findProductByCategoryUseCase(ProductRepositoryPort productRepositoryPort) {
        return new FindProductByCategoryUseCase(productRepositoryPort);
    }

    @Bean
    public FindProductByIdUseCase findProductByIdUseCase(ProductRepositoryPort productRepositoryPort, ProductMapper productMapper) {
        return new FindProductByIdUseCase(productRepositoryPort, productMapper);
    }

    @Bean
    public ProductService productService(CreateProductUseCase createProductUseCase, UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase, FindProductByCategoryUseCase findProductByCategoryUseCase) {
        return new ProductService(createProductUseCase, updateProductUseCase, deleteProductUseCase, findProductByCategoryUseCase);
    }
}