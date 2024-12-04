package com.bytes.bytes.contexts.kitchen.adapters.inbound.dtos;

import com.bytes.bytes.contexts.kitchen.domain.models.ProductCategory;
import com.bytes.bytes.contexts.kitchen.domain.models.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String name;

    @NotNull(message = "E-mail é obrigatório")
    private BigDecimal price;

    @NotNull(message = "A categoria é obrigatória")
    private ProductCategory category;

    private String description;
}
