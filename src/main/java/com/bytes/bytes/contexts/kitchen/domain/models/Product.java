package com.bytes.bytes.contexts.kitchen.domain.models;

import com.bytes.bytes.contexts.kitchen.domain.execeptions.product.ProductInvalidDataException;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private String imgUrl;
    private BigDecimal price;
    private ProductCategory category;
    private String description;
    private Long createdById;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void update(Product product) {
        this.name = product.getName();
        this.imgUrl = product.getImgUrl();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.description = product.getDescription();

        this.validate();
    }

    public Product() {
    }

    public Product(Long id, String name, String imgUrl, BigDecimal price, ProductCategory category, String description, Long createdById) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
        this.category = category;
        this.description = description;
        this.createdById = createdById;
        this.validate();
    }

    public void validate() {
        if(this.name == null || name.trim().isEmpty()){
            throw new ProductInvalidDataException("O nome do produto não pode ser nulo");
        }

        if(this.category == null){
            throw new ProductInvalidDataException("A categoria do produto não pode ser nula");
        }

        if(price.compareTo(new BigDecimal("0")) < 1){
            throw new ProductInvalidDataException("O preço do produto não pode ser inferior 0 reias");
        }

        if(createdById == null){
            throw new ProductInvalidDataException("É preciso informa o id do usuário que está criando o produto");
        }
    }
}
