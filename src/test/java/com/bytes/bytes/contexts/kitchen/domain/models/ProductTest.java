package com.bytes.bytes.contexts.kitchen.domain.models;

import com.bytes.bytes.contexts.kitchen.domain.execeptions.product.ProductInvalidDataException;
import com.bytes.bytes.contexts.kitchen.utils.ProductDummyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


class ProductTest {

    private Product productDummy;

    private final Long productId = 12L;

    @BeforeEach
    void setUp() {
        productDummy = ProductDummyFactory.createDummyProduct(productId);
    }

    @Test
    @DisplayName("Should be able to update a product")
    void update() {
        productDummy.update(new Product(
                null,
                "Milkshake Morango",
                "https://bytes.com/milkshake.png",
                BigDecimal.TEN,
                ProductCategory.SOBREMESA,
                "Delicioso milkshake de morango",
                19L
        ));

        assertEquals("Milkshake Morango", productDummy.getName());
        assertEquals("Delicioso milkshake de morango", productDummy.getDescription());
        assertEquals(BigDecimal.TEN, productDummy.getPrice());
        assertEquals(ProductCategory.SOBREMESA, productDummy.getCategory());
        assertEquals(productId, productDummy.getId());
        assertEquals("https://bytes.com/milkshake.png", productDummy.getImgUrl());
    }

    @Test
    @DisplayName("Should not be able to update a product with invalid data")
    void doNotUpdateIfProductHasInvalidData() {
        assertThrows(ProductInvalidDataException.class, () ->{
            productDummy.update(new Product(
                   null,
                   "",
                   "https://bytes.com/milkshake.png",
                   BigDecimal.TEN,
                   ProductCategory.SOBREMESA,
                   "Delicioso milkshake de morango",
                    19L
           ));
        });
    }

    @Test
    @DisplayName("Price should be greater than 0")
    void priceShouldBeGreaterThanZero(){
        assertThrows(ProductInvalidDataException.class, () ->{
           new Product(
                    null,
                    "Milkshake Morango",
                    "https://bytes.com/milkshake.png",
                    BigDecimal.ZERO,
                    ProductCategory.SOBREMESA,
                    "Delicioso milkshake de morango",
                   19L
           ).validate();
        });
    }

    @Test
    @DisplayName("Name can´t be empty")
    void productNameCantBeEmpty(){
        assertThrows(ProductInvalidDataException.class, () -> {
            new Product(
                    null,
                    "",
                    "https://bytes.com/milkshake.png",
                    BigDecimal.TWO,
                    ProductCategory.SOBREMESA,
                    "Delicioso milkshake de morango",
                    19L
            ).validate();
        });
    }
}