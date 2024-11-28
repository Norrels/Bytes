package com.bytes.bytes.contexts.kitchen.config;

import com.bytes.bytes.contexts.kitchen.core.application.port.outbound.TokenProviderPort;
import com.bytes.bytes.contexts.kitchen.core.application.port.outbound.UserServicePort;
import com.bytes.bytes.contexts.kitchen.core.application.services.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public UserService userService(UserServicePort saveUserPort, TokenProviderPort tokenProviderPort){
        return new UserService(saveUserPort, tokenProviderPort);
    }

}
