package com.bytes.bytes.contexts.kitchen.domain.port.inbound;

import com.bytes.bytes.contexts.shared.dtos.ProductDTO;

import java.util.List;

public interface ProductServicePort {
    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);

    List<ProductDTO> findProductByCategory(String category);
}
