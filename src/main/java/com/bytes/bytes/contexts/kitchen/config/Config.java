package com.bytes.bytes.contexts.kitchen.config;

import com.bytes.bytes.contexts.kitchen.application.services.ProductService;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.ProductRepositoryPort;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.TokenProviderPort;
import com.bytes.bytes.contexts.kitchen.domain.port.outbound.UserRepositoryPort;
import com.bytes.bytes.contexts.kitchen.application.services.UserService;

import com.bytes.bytes.contexts.kitchen.utils.ProductMapper;
import com.bytes.bytes.contexts.kitchen.utils.ProductMapperImpl;
import com.bytes.bytes.contexts.kitchen.utils.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public UserService userService(UserRepositoryPort saveUserPort, TokenProviderPort tokenProviderPort, UserMapper mapper){
        return new UserService(saveUserPort, tokenProviderPort, mapper);
    }


    @Bean
    public ProductService productService(ProductRepositoryPort productRepositoryPort, UserRepositoryPort saveUserPort){
        return new ProductService(productRepositoryPort, saveUserPort);
    }

}
