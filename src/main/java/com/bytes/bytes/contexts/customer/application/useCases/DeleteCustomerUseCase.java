package com.bytes.bytes.contexts.customer.application.useCases;

import com.bytes.bytes.contexts.customer.domain.CustomerNotFound;
import com.bytes.bytes.contexts.customer.domain.ports.outbound.CustomerRepositoryPort;

public class DeleteCustomerUseCase {
    private final CustomerRepositoryPort customerRepository;

    public DeleteCustomerUseCase(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(Long id) {
        customerRepository.findById(id).orElseThrow(CustomerNotFound::new);
        customerRepository.delete(id);
    }
}