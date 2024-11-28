package com.bytes.bytes.contexts.kitchen.config;

import com.bytes.bytes.contexts.kitchen.core.application.port.outbound.TokenProviderPort;
import com.bytes.bytes.contexts.kitchen.core.application.port.outbound.UserRepositoryPort;
import com.bytes.bytes.contexts.kitchen.core.application.services.UserService;

import com.bytes.bytes.contexts.kitchen.utils.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public UserService userService(UserRepositoryPort saveUserPort, TokenProviderPort tokenProviderPort, UserMapper mapper){
        return new UserService(saveUserPort, tokenProviderPort, mapper);
    }

}
