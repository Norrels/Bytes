package com.bytes.bytes.contexts.kitchen.adapters.inbound.rest;

import com.bytes.bytes.contexts.kitchen.adapters.inbound.dtos.ProductRequest;
import com.bytes.bytes.contexts.kitchen.application.ProductService;
import com.bytes.bytes.contexts.kitchen.application.UserService;
import com.bytes.bytes.contexts.kitchen.domain.models.Product;
import com.bytes.bytes.contexts.kitchen.domain.models.ProductCategory;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.ImageServicePort;
import com.bytes.bytes.contexts.kitchen.utils.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "Product", description = "Operações realizadas pelo estabelicimento em relação aos produtos")
@RequestMapping("/kitchen/product")
public class ProductController {
    private final ProductService productService;

    private final ImageServicePort imageService;

    public ProductController(ProductService productService, ImageServicePort imageService) {
        this.productService = productService;
        this.imageService = imageService;
    }

    @SecurityRequirement(name = "jwt_auth")
    @Operation(summary = "Cria produto")
    @PreAuthorize("hasRole('ADMIN')")
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

    @SecurityRequirement(name = "jwt_auth")
    @Operation(summary = "Atualiza produto")
    @PreAuthorize("hasRole('ADMIN')")
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

    @SecurityRequirement(name = "jwt_auth")
    @Operation(summary = "Deleta produto")
    @PreAuthorize("hasRole('ADMIN')")
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
    @GetMapping("/category/{category}")
    public ResponseEntity<Object> findProductByCategory(@PathVariable String category) {
        try {
            return ResponseEntity.ok().body(productService.findProductByCategory(ProductCategory.fromString(category)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @SecurityRequirement(name = "jwt_auth")
    @PostMapping(value = "{productId}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Upload de imagem")
    public ResponseEntity<Object> uploadImage(@RequestPart("file") MultipartFile file, @PathVariable("productId") Long productId){
        try {
            String imageUrl = imageService.uploadImage(file.getBytes());
            Product product = productService.updateImageUrl(productId, imageUrl);
            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
