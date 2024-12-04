package com.bytes.bytes.contexts.kitchen.adapters.outbound.persistence.entities;

import com.bytes.bytes.contexts.kitchen.domain.models.ProductCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "product_entity")
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String imgUrl;
    private BigDecimal price;
    private ProductCategory category;
    private String description;
    private Long createdById;

    @ManyToOne
    @JoinColumn(name = "createdById", insertable = false, updatable = false)
    private UserEntity userEntity;
}
