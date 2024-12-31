package com.bytes.bytes.contexts.customer.application.useCases;

import com.bytes.bytes.contexts.customer.domain.CustomerNotFound;
import com.bytes.bytes.contexts.customer.domain.models.Customer;
import com.bytes.bytes.contexts.customer.domain.ports.outbound.CustomerRepositoryPort;

public class UpdateCustomerUseCase {
    private final CustomerRepositoryPort customerRepository;

    public UpdateCustomerUseCase(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Long id, Customer customer) {
        customerRepository.findById(id).orElseThrow(CustomerNotFound::new);
        customer.update(customer.getCpf(), customer.getEmail(), customer.getName(), customer.getPhone());
        return customerRepository.save(customer);
    }
}
