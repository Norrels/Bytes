package com.bytes.bytes.contexts.customer.config;

import com.bytes.bytes.contexts.customer.application.CustomerService;
import com.bytes.bytes.contexts.customer.domain.ports.inbound.CustomerServicePort;
import com.bytes.bytes.contexts.customer.domain.ports.outbound.CustomerRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCustomer {

    @Bean
    public CustomerService customerService(CustomerRepositoryPort CustomerRepositoryPort){
        return new CustomerService(CustomerRepositoryPort);
    }
}
