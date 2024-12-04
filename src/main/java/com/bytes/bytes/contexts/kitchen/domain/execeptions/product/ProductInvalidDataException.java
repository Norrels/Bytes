package com.bytes.bytes.contexts.kitchen.domain.execeptions.product;

public class ProductInvalidDataException extends RuntimeException{
    public ProductInvalidDataException(String message){
        super(message);
    }
}
