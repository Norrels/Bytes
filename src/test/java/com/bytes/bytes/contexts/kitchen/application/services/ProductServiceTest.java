package com.bytes.bytes.contexts.kitchen.application.services;

import com.bytes.bytes.contexts.kitchen.domain.execeptions.product.ProductNotFoundException;
import com.bytes.bytes.contexts.kitchen.domain.execeptions.user.UserNotFoundException;
import com.bytes.bytes.contexts.kitchen.domain.models.Product;
import com.bytes.bytes.contexts.kitchen.domain.models.ProductCategory;
import com.bytes.bytes.contexts.kitchen.domain.models.User;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.ProductRepositoryPort;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.UserRepositoryPort;
import com.bytes.bytes.contexts.kitchen.utils.ProductDummyFactory;
import com.bytes.bytes.contexts.kitchen.utils.ProductMapper;
import com.bytes.bytes.contexts.shared.dtos.ProductDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    private final Long productId = 12L;

    private final Long userId = 2L;
    @Mock
    private ProductRepositoryPort productRepository;

    @Mock
    private UserRepositoryPort userRepository;

    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("Should be able to create a product ")
    void createProduct() {
        ProductDTO productDto = ProductDTO.builder()
                .name("Bytes Fries")
                .createdById(userId)
                .price(BigDecimal.TWO)
                .createdById(userId)
                .category(ProductCategory.ACOMPANHAMENTO)
                .build();

        Product saveProduct = productMapper.toProduct(productDto);
        saveProduct.setId(2L);
        when(productRepository.save(any(Product.class))).thenReturn(saveProduct);

        User user = new User();
        user.setId(2L);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        ProductDTO newProduct = productService.createProduct(productDto);

        assertNotNull(newProduct.getId());
    }

    @Test
    @DisplayName("Should not create a product if user does not exists")
    void shouldNotCreateProductIfUserDoesNotExists() {
        ProductDTO productDto = ProductDTO.builder()
                .name("Bytes Fries")
                .createdById(userId)
                .price(BigDecimal.TWO)
                .category(ProductCategory.ACOMPANHAMENTO)
                .build();

        assertThrows(UserNotFoundException.class, () -> {
            productService.createProduct(productDto);
        });
    }

    @Test
    @DisplayName("Delete product if product exists")
    void deleteProduct() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));
        productService.deleteProduct(productId);
        verify(productRepository).delete(productId);
    }

    @Test
    @DisplayName("Do not delete product if does not exists")
    void doNotDeleteProduct() {
        assertThrows(ProductNotFoundException.class, () -> {
            productService.deleteProduct(productId);
        });
    }

    @Test
    @DisplayName("Should update product")
    void updateProduct() {
        ProductDTO productDTO = ProductDTO.builder()
                .name("Bytes fries")
                .category(ProductCategory.ACOMPANHAMENTO)
                .createdById(userId)
                .price(BigDecimal.TWO)
                .build();

        Product dummyProduct = ProductDummyFactory.createDummyProduct(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(dummyProduct));

        ProductDTO product = productService.updateProduct(productId, productDTO);

        assertEquals(productId, product.getId());
    }

    @Test
    @DisplayName("Should return products by category")
    void findProductByCategory() {
        List<Product> productsDummy = List.of(
                createProduct(13L),
                createProduct(12L)
        );

        when(productRepository.findByCategory(ProductCategory.BEBIDA.toString())).thenReturn(productsDummy);

        List<ProductDTO> products = productService.findProductByCategory(ProductCategory.BEBIDA.toString());

        assertEquals(2, products.size());
        assertEquals(ProductCategory.BEBIDA, products.get(0).getCategory());
        assertEquals(ProductCategory.BEBIDA, products.get(1).getCategory());
    }


    private Product createProduct(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setCategory(ProductCategory.BEBIDA);
        return product;
    }
}