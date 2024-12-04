package com.bytes.bytes.contexts.kitchen.utils;

import com.bytes.bytes.contexts.kitchen.domain.models.Product;
import com.bytes.bytes.contexts.kitchen.domain.models.ProductCategory;
import com.bytes.bytes.contexts.shared.dtos.ProductDTO;

import java.math.BigDecimal;

public class ProductDummyFactory {
    public static Product createDummyProduct(Long id){
        return new Product(
                id,
                "Dummy Product",
                "https://bytes.com/dummy.png",
                BigDecimal.TEN,
                ProductCategory.SOBREMESA,
                "Delicioso Milkshake Dummy",
                19L
        );
    }

    public static Product createDummyProductBy(ProductDTO productDTO){
        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getImgUrl(),
                productDTO.getPrice(),
                productDTO.getCategory(),
                productDTO.getDescription(),
                productDTO.getCreatedById()
        );
    }
}
