package com.bytes.bytes.contexts.kitchen.application.services;

import com.bytes.bytes.contexts.kitchen.domain.execeptions.user.UserNotFoundException;
import com.bytes.bytes.contexts.kitchen.domain.port.inbound.ProductServicePort;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.ProductRepositoryPort;
import com.bytes.bytes.contexts.kitchen.domain.execeptions.product.ProductNotFoundException;
import com.bytes.bytes.contexts.kitchen.domain.models.Product;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.UserRepositoryPort;
import com.bytes.bytes.contexts.kitchen.utils.ProductMapper;
import com.bytes.bytes.contexts.shared.dtos.ProductDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService implements ProductServicePort {
    private final ProductRepositoryPort productRepository;

    private final UserRepositoryPort userReposity;

    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    public ProductService(ProductRepositoryPort productRepository, UserRepositoryPort userReposity) {
        this.productRepository = productRepository;
        this.userReposity = userReposity;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        userReposity.findById(productDTO.getCreatedById()).orElseThrow(UserNotFoundException::new);
        Product product = productRepository.save(productMapper.toProduct(productDTO));
        product.validate();
        return productMapper.toProductDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.update(productMapper.toProduct(productDTO));
        return productMapper.toProductDTO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(id);
    }

    @Override
    public List<ProductDTO> findProductByCategory(String category) {
        return productRepository.findByCategory(category).stream()
                .map(productMapper::toProductDTO)
                .collect(Collectors.toList());
    }
}
