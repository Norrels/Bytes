package com.bytes.bytes.contexts.kitchen.adapters.inbound.rest;

import com.bytes.bytes.contexts.kitchen.adapters.inbound.dtos.ProductRequest;
import com.bytes.bytes.contexts.kitchen.application.ProductService;
import com.bytes.bytes.contexts.kitchen.application.UserService;
import com.bytes.bytes.contexts.kitchen.domain.models.Product;
import com.bytes.bytes.contexts.kitchen.domain.models.ProductCategory;
import com.bytes.bytes.contexts.kitchen.utils.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Product", description = "Operações realizadas pelo estabelicimento em relação aos produtos")
@RequestMapping("/kitchen/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Cria produto")
    @PostMapping()
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        try {
            var productToSave = new Product(
                    null,
                    productRequest.getName(),
                    productRequest.getImgUrl(),
                    productRequest.getPrice(),
                    productRequest.getCategory(),
                    productRequest.getDescription(),
                    productRequest.getObservation(),
                    1L);

            Product product = productService.createProduct(productToSave);
            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @Operation(summary = "Atualiza produto")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {
        try {
            var productToUpdate = new Product(
                    id,
                    productRequest.getName(),
                    productRequest.getImgUrl(),
                    productRequest.getPrice(),
                    productRequest.getCategory(),
                    productRequest.getDescription(),
                    productRequest.getObservation(),
                    1L);

            Product product = productService.updateProduct(id, productToUpdate);
            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Deleta produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok().body("Produto deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Busca produto por categoria")
    @GetMapping("/{category}")
    public ResponseEntity<Object> findProductByCategory(@PathVariable String category) {
        try {
            return ResponseEntity.ok().body(productService.findProductByCategory(ProductCategory.fromString(category)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
