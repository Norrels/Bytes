package com.bytes.bytes.contexts.order.domain.models;

import com.bytes.bytes.contexts.kitchen.domain.execeptions.product.ProductInvalidDataException;

import java.math.BigDecimal;

public class OrderItem {
    private String name;
    private BigDecimal price;
    private int quantity;
    private String observation;
    private Long originalProductId;

    public void validate(){
        if(this.name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("O nome do produto não pode ser nulo");
        }

        if(this.quantity <= 0) {
            throw new IllegalArgumentException("A quantidade do item deve ser maior que 0");
        }

        if(this.price.compareTo(new BigDecimal("0")) < 1){
            throw new ProductInvalidDataException("O preço do produto não pode ser inferior a 0 reias");
        }

        if(originalProductId == null) {
            throw new ProductInvalidDataException("O id do produto original não pode ser nulo");
        }
    }

    public OrderItem(String name, BigDecimal price, int quantity, String observation, Long originalProductId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.observation = observation;
        this.originalProductId = originalProductId;
    }

    public BigDecimal getTotal(){
        return this.price.multiply(BigDecimal.valueOf(this.quantity));
    }
}
