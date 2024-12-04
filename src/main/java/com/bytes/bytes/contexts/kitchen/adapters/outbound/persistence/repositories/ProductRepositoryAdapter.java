package com.bytes.bytes.contexts.kitchen.adapters.outbound.persistence.repositories;

import com.bytes.bytes.contexts.kitchen.adapters.outbound.persistence.entities.ProductEntity;
import com.bytes.bytes.contexts.kitchen.domain.models.Product;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.ProductRepositoryPort;
import com.bytes.bytes.contexts.kitchen.utils.ProductMapper;
import com.bytes.bytes.contexts.kitchen.utils.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductRepository productRepository;


    private final ProductMapper productMapper;

    public ProductRepositoryAdapter(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productRepository.save(productMapper.toProductEntity(product));
        return productMapper.toProduct(productEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findByCategory(String category) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
