package com.bytes.bytes.contexts.customer.application.useCases;

import com.bytes.bytes.contexts.customer.domain.CustomerNotFound;
import com.bytes.bytes.contexts.customer.domain.models.Customer;
import com.bytes.bytes.contexts.customer.domain.ports.outbound.CustomerRepositoryPort;

public class FindCustomerByCPFUseCase {
    private final CustomerRepositoryPort customerRepository;

    public FindCustomerByCPFUseCase(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(String cpf) {
        return customerRepository.findByCPF(cpf).orElseThrow(CustomerNotFound::new);
    }
}